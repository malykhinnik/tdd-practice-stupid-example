package com.stringconcat.dto.alert;

import com.stringconcat.dto.EnumDto;

import java.util.List;
import java.util.Objects;

public class AlertConfigurationConditionDto {
	private EnumDto field;
	private EnumDto conditions;
	private List<String> fieldValues;

	public EnumDto getField() {
		return field;
	}

	public void setField(EnumDto field) {
		this.field = field;
	}

	public EnumDto getConditions() {
		return conditions;
	}

	public void setConditions(EnumDto conditions) {
		this.conditions = conditions;
	}

	public List<String> getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(List<String> fieldValues) {
		this.fieldValues = fieldValues;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AlertConfigurationConditionDto that = (AlertConfigurationConditionDto) o;
		return Objects.equals(field, that.field) && Objects.equals(conditions, that.conditions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(field, conditions);
	}
}
