package com.stringconcat.mapping;

import com.stringconcat.domain.alert.AlertConfiguration;
import com.stringconcat.domain.alert.AlertConfigurationNotificationType;
import com.stringconcat.dto.EnumDto;
import com.stringconcat.dto.alert.AlertConfigurationRequest;
import com.stringconcat.dto.alert.AlertConfigurationResponse;
import com.stringconcat.util.EnumUtils;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AlertConfiguration2AlertConfigurationResponseMapping extends PropertyMap<AlertConfiguration, AlertConfigurationResponse> {
	@Override
	protected void configure() {
		using(new AbstractConverter<Set<AlertConfigurationNotificationType>,List<EnumDto>>() {
			@Override
			protected List<EnumDto> convert(Set<AlertConfigurationNotificationType> notificationTypes) {
				return notificationTypes == null ? null : notificationTypes.stream()
						.map(EnumUtils::toEnumDto).collect(Collectors.toList())						;
			}
		}).map(source.getNotificationTypes()).setNotificationTypes(null);
	}
}
