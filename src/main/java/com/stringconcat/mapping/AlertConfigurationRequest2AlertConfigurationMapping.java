package com.stringconcat.mapping;

import com.stringconcat.domain.alert.AlertConfiguration;
import com.stringconcat.domain.alert.AlertConfigurationCondition;
import com.stringconcat.domain.alert.AlertConfigurationNotificationType;
import com.stringconcat.dto.EnumDto;
import com.stringconcat.dto.alert.AlertConfigurationConditionDto;
import com.stringconcat.dto.alert.AlertConfigurationRequest;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AlertConfigurationRequest2AlertConfigurationMapping extends PropertyMap<AlertConfigurationRequest, AlertConfiguration> {
	@Override
	protected void configure() {
		using(new AbstractConverter<List<EnumDto>, Set<AlertConfigurationNotificationType>>() {
			@Override
			protected Set<AlertConfigurationNotificationType> convert(List<EnumDto> condition) {
				return condition == null ? null : condition.stream()
						.map(c -> AlertConfigurationNotificationType.valueOf(c.getName())).collect(Collectors.toSet())						;
			}
		}).map(source.getNotificationTypes()).setNotificationTypes(null);
	}
}
