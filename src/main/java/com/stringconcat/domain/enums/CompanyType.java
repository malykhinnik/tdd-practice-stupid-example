package com.stringconcat.domain.enums;

public enum CompanyType {
	Publisher("Publisher"),DataProvider("Data Provider");

	private String caption;

	private CompanyType(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}
}
