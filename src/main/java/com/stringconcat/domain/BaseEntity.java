package com.stringconcat.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.core.Ordered;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@MappedSuperclass
public class BaseEntity implements Serializable, Ordered {
	private static final long serialVersionUID = 8882549236617919685L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updateDate;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@PrePersist
	public void onPersist() {
		DateTime nowDt = new DateTime(DateTimeZone.UTC);
		Date current = new Date(nowDt.getMillis());
		setCreateDate(current);
		setUpdateDate(current);
	}

	@PreUpdate
	public void onUpdate() {
		setUpdateDate(new Date(new DateTime(DateTimeZone.UTC).getMillis()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;

		BaseEntity that = (BaseEntity) o;
		return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
	}

	public static <T extends BaseEntity> Set<T> sort(Set<T> set) {
		if (set == null) {
			return null;
		}

		return set.stream().sorted(BaseEntity::sort).collect(Collectors.toCollection(LinkedHashSet::new));
	}

	private static <T extends Ordered> int sort(T v1, T v2) {
		return Integer.compare(v1.getOrder(), v2.getOrder());
	}

	@Override
	public int getOrder() {
		return Optional.ofNullable(id).map(Long::intValue).orElse(0);
	}
}
