package com.stringconcat.domain.alert;

import com.stringconcat.domain.Company;
import com.stringconcat.domain.NamedBaseEntity;
import com.stringconcat.util.AlertConfigurationNotificationTypeSetConverter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class AlertConfiguration extends NamedBaseEntity {
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;
	@NotEmpty
	@Convert(converter = AlertConfigurationNotificationTypeSetConverter.class)
	private Set<AlertConfigurationNotificationType> notificationTypes;
	@NotEmpty
	@OneToMany(mappedBy = "alertConfiguration", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AlertConfigurationCondition> conditions;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<AlertConfigurationNotificationType> getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(Set<AlertConfigurationNotificationType> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	public Set<AlertConfigurationCondition> getConditions() {
		return conditions;
	}

	public void setConditions(Set<AlertConfigurationCondition> conditions) {
		this.conditions = conditions;
	}
}
