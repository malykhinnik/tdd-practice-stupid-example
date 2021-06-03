package com.stringconcat.services;

import com.stringconcat.ApplicationTests;
import com.stringconcat.domain.NamedBaseEntity;
import com.stringconcat.domain.alert.AlertConfiguration;
import com.stringconcat.domain.alert.AlertConfigurationCondition;
import com.stringconcat.domain.alert.AlertConfigurationNotificationType;
import com.stringconcat.domain.usercampaignview.UserCampaignViewFieldType;
import com.stringconcat.domain.usercampaignview.UserCampaignViewFilterType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AlertConfigurationServiceTest extends ApplicationTests {
	@Autowired
	private AlertConfigurationService alertConfigurationService;

	@Test
	public void createAlertConfiguration() {
		AlertConfiguration alertConfiguration = createAlertConfigurationEntity();
		AlertConfiguration result = alertConfigurationService.create(company.getId(), alertConfiguration);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getId());

		Assert.assertNotNull(result.getName());
		Assert.assertEquals(alertConfiguration.getName(), result.getName());

		Assert.assertNotNull(result.getCompany());
		Assert.assertEquals(company.getId(), result.getCompany().getId());

		Assert.assertNotNull(result.getNotificationTypes());
		Assert.assertEquals(1, result.getNotificationTypes().size());
		Assert.assertEquals(AlertConfigurationNotificationType.SYSTEM, result.getNotificationTypes().iterator().next());

		Assert.assertNotNull(result.getConditions());
		Assert.assertEquals(1, result.getConditions().size());
		Assert.assertEquals(UserCampaignViewFieldType.ID, result.getConditions().iterator().next().getField());
		Assert.assertEquals(UserCampaignViewFilterType.EQUALS,
				result.getConditions().iterator().next().getConditions());
		Assert.assertNotNull(result.getConditions().iterator().next().getFieldValues());
		Assert.assertEquals(1, result.getConditions().iterator().next().getFieldValues().size());
		Assert.assertEquals("1", result.getConditions().iterator().next().getFieldValues().iterator().next());
	}

	private AlertConfiguration createAlertConfigurationEntity() {
		AlertConfiguration alertConfiguration = new AlertConfiguration();
		alertConfiguration.setName("testAlertConfiguration");
		alertConfiguration.setCompany(company);
		alertConfiguration.setNotificationTypes(new HashSet<AlertConfigurationNotificationType>() {{
			add(AlertConfigurationNotificationType.SYSTEM);
		}});
		alertConfiguration.setConditions(new HashSet<AlertConfigurationCondition>() {{
			add(new AlertConfigurationCondition() {{
				setField(UserCampaignViewFieldType.ID);
				setConditions(UserCampaignViewFilterType.EQUALS);
				setFieldValues(new HashSet<String>() {{
					add("1");
				}});
			}});
		}});
		return alertConfiguration;
	}

	@Test
	public void updateAlertConfigurationCondition() {
		AlertConfiguration alertConfiguration = createAlertConfigurationEntity();
		AlertConfiguration result = alertConfigurationService.create(company.getId(), alertConfiguration);
		alertConfiguration.setConditions(new HashSet<AlertConfigurationCondition>() {{
			add(new AlertConfigurationCondition() {{
				setField(UserCampaignViewFieldType.NAME);
				setConditions(UserCampaignViewFilterType.CONTAINS);
				setFieldValues(new HashSet<String>() {{
					add("2");
				}});
			}});
		}});
		result = alertConfigurationService.update(company.getId(), result.getId(), alertConfiguration);

		Assert.assertNotNull(result.getConditions());
		Assert.assertEquals(1, result.getConditions().size());
		Assert.assertEquals(UserCampaignViewFieldType.NAME, result.getConditions().iterator().next().getField());
		Assert.assertEquals(UserCampaignViewFilterType.CONTAINS,
				result.getConditions().iterator().next().getConditions());
		Assert.assertNotNull(result.getConditions().iterator().next().getFieldValues());
		Assert.assertEquals(1, result.getConditions().iterator().next().getFieldValues().size());
		Assert.assertEquals("2", result.getConditions().iterator().next().getFieldValues().iterator().next());

		alertConfiguration.getConditions().iterator().next().getFieldValues().add("1");
		result = alertConfigurationService.update(company.getId(), result.getId(), alertConfiguration);

		Assert.assertEquals(2, result.getConditions().iterator().next().getFieldValues().size());
		Assert.assertTrue(result.getConditions().iterator().next().getFieldValues().contains("1"));
		Assert.assertTrue(result.getConditions().iterator().next().getFieldValues().contains("2"));
	}

	@Test
	public void updateAlertConfigurationName() {
		AlertConfiguration alertConfiguration = createAlertConfigurationEntity();
		AlertConfiguration result = alertConfigurationService.create(company.getId(), alertConfiguration);
		alertConfiguration.setName("newTestAlertConfiguration");
		result = alertConfigurationService.update(company.getId(), result.getId(), alertConfiguration);

		Assert.assertNotNull(result.getName());
		Assert.assertEquals(alertConfiguration.getName(), result.getName());
	}

	@Test
	public void updateAlertConfigurationNotificationTypes() {
		AlertConfiguration alertConfiguration = createAlertConfigurationEntity();
		AlertConfiguration result = alertConfigurationService.create(company.getId(), alertConfiguration);
		alertConfiguration.setNotificationTypes(new HashSet<AlertConfigurationNotificationType>() {{
			add(AlertConfigurationNotificationType.EMAIL);
		}});
		result = alertConfigurationService.update(company.getId(), result.getId(), alertConfiguration);

		Assert.assertNotNull(result.getNotificationTypes());
		Assert.assertEquals(1, result.getNotificationTypes().size());
		Assert.assertEquals(AlertConfigurationNotificationType.EMAIL, result.getNotificationTypes().iterator().next());

		alertConfiguration.getNotificationTypes().add(AlertConfigurationNotificationType.SYSTEM);
		result = alertConfigurationService.update(company.getId(), result.getId(), alertConfiguration);

		Assert.assertEquals(2, result.getNotificationTypes().size());
		Assert.assertTrue(result.getNotificationTypes().contains(AlertConfigurationNotificationType.SYSTEM));
		Assert.assertTrue(result.getNotificationTypes().contains(AlertConfigurationNotificationType.EMAIL));
	}

	@Test
	public void getAlertConfigurations() {
		AlertConfiguration alertConfiguration = createAlertConfigurationEntity();
		alertConfiguration.setName("testAlertConfiguration1");
		alertConfigurationService.create(company.getId(), alertConfiguration);
		Set<AlertConfiguration> response = alertConfigurationService.getByCompanyId(company.getId());
		Assert.assertNotNull(response);
		Assert.assertEquals(1, response.size());
		Assert.assertEquals("testAlertConfiguration1", response.iterator().next().getName());

		alertConfiguration = createAlertConfigurationEntity();
		alertConfiguration.setName("testAlertConfiguration2");
		alertConfigurationService.create(company.getId(), alertConfiguration);
		response = alertConfigurationService.getByCompanyId(company.getId());
		Assert.assertNotNull(response);
		Assert.assertEquals(2, response.size());
		Set<String> responseNames = response.stream().map(NamedBaseEntity::getName).collect(Collectors.toSet());
		Assert.assertTrue(responseNames.contains("testAlertConfiguration1"));
		Assert.assertTrue(responseNames.contains("testAlertConfiguration2"));

		alertConfiguration = createAlertConfigurationEntity();
		alertConfiguration.setCompany(otherCompany);
		alertConfigurationService.create(otherCompany.getId(), alertConfiguration);
		response = alertConfigurationService.getByCompanyId(company.getId());

		Assert.assertNotNull(response);
		Assert.assertEquals(2, response.size());
	}

	@Test
	public void deleteAlertConfigurations() {
		AlertConfiguration alertConfiguration = createAlertConfigurationEntity();
		AlertConfiguration response = alertConfigurationService.create(company.getId(), alertConfiguration);
		alertConfigurationService.delete(company.getId(), response.getId());
		Assert.assertTrue(alertConfigurationService.getByCompanyId(company.getId()).isEmpty());

		alertConfiguration = createAlertConfigurationEntity();
		response = alertConfigurationService.create(company.getId(), alertConfiguration);
		alertConfiguration = createAlertConfigurationEntity();
		alertConfiguration.setName("testAlertConfigurationOther");
		alertConfigurationService.create(company.getId(), alertConfiguration);
		alertConfigurationService.delete(company.getId(), response.getId());

		Assert.assertEquals(1, alertConfigurationService.getByCompanyId(company.getId()).size());
		Assert.assertEquals("testAlertConfigurationOther",
				alertConfigurationService.getByCompanyId(company.getId()).iterator().next().getName());
	}
}
