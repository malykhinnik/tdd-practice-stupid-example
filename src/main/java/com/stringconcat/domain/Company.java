package com.stringconcat.domain;

import com.stringconcat.domain.enums.CompanyType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Company extends BaseEntity {
	private static final long serialVersionUID = 3460402475609470036L;
	@Column(length = 100)
	@NotNull
	private String name;
	@NotNull
	private boolean enabled;
	@Column(length = 50)
	@NotNull
	@Enumerated(EnumType.STRING)
	private CompanyType companyType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Company company = (Company) o;
		return Objects.equals(name, company.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name);
	}
}
