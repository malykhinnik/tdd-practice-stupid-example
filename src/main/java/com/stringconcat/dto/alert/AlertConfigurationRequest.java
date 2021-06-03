package com.stringconcat.dto.alert;

import com.stringconcat.dto.EnumDto;
import com.stringconcat.dto.NamedBaseEntityDto;
import com.stringconcat.dto.NamedEntityDto;

import java.util.List;
import java.util.Objects;

public class AlertConfigurationRequest extends NamedEntityDto {
	private NamedBaseEntityDto company;
	private List<EnumDto> notificationTypes;
	private List<AlertConfigurationConditionDto> conditions;


	public NamedBaseEntityDto getCompany() {
		return company;
	}

	public void setCompany(NamedBaseEntityDto company) {
		this.company = company;
	}

	public List<EnumDto> getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(List<EnumDto> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	public List<AlertConfigurationConditionDto> getConditions() {
		return conditions;
	}

	public void setConditions(List<AlertConfigurationConditionDto> conditions) {
		this.conditions = conditions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		AlertConfigurationRequest that = (AlertConfigurationRequest) o;
		return Objects.equals(company, that.company);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), company);
	}
}
