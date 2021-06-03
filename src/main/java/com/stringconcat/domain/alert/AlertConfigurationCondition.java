package com.stringconcat.domain.alert;

import com.stringconcat.domain.BaseEntity;
import com.stringconcat.domain.usercampaignview.UserCampaignViewFieldType;
import com.stringconcat.domain.usercampaignview.UserCampaignViewFilterType;
import com.stringconcat.util.StringSetConverter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity

public class AlertConfigurationCondition extends BaseEntity {
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private AlertConfiguration alertConfiguration;
	@NotNull
	private UserCampaignViewFieldType field;
	@NotNull
	private UserCampaignViewFilterType conditions;
	@Convert(converter = StringSetConverter.class)
	@NotEmpty
	private Set<String> fieldValues;

	public AlertConfiguration getAlertConfiguration() {
		return alertConfiguration;
	}

	public void setAlertConfiguration(AlertConfiguration alertConfiguration) {
		this.alertConfiguration = alertConfiguration;
	}

	public UserCampaignViewFieldType getField() {
		return field;
	}

	public void setField(UserCampaignViewFieldType column) {
		this.field = column;
	}

	public UserCampaignViewFilterType getConditions() {
		return conditions;
	}

	public void setConditions(UserCampaignViewFilterType conditions) {
		this.conditions = conditions;
	}

	public Set<String> getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(Set<String> values) {
		this.fieldValues = values;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AlertConfigurationCondition))
			return false;
		if (!super.equals(o))
			return false;

		AlertConfigurationCondition that = (AlertConfigurationCondition) o;

		if (getField() != that.getField())
			return false;
		if (getConditions() != that.getConditions())
			return false;
		return getFieldValues() != null ? getFieldValues().equals(that.getFieldValues()) : that.getFieldValues() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getField() != null ? getField().hashCode() : 0);
		result = 31 * result + (getConditions() != null ? getConditions().hashCode() : 0);
		result = 31 * result + (getFieldValues() != null ? getFieldValues().hashCode() : 0);
		return result;
	}
}
