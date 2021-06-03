package com.stringconcat.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass

public class NamedBaseEntity extends BaseEntity{
	protected String name;

	public NamedBaseEntity() {
	}

	public NamedBaseEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof NamedBaseEntity))
			return false;
		if (!super.equals(o))
			return false;

		NamedBaseEntity that = (NamedBaseEntity) o;

		return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		return result;
	}
}
