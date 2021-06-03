package com.stringconcat.dto;

import com.stringconcat.domain.enums.CompanyType;

public class CompanyDto extends BaseCompanyDto{
	private boolean enabled;
	private CompanyType companyType;

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
}
