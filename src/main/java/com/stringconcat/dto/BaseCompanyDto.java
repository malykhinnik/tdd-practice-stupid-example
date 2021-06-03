package com.stringconcat.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class BaseCompanyDto {
	long id;
	@NotBlank
	@Size(max = 50)
	String name;

	public BaseCompanyDto(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public BaseCompanyDto() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
