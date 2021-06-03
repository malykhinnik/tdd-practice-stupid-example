package com.stringconcat.util;

import com.stringconcat.domain.alert.AlertConfigurationNotificationType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class AlertConfigurationNotificationTypeSetConverter
		implements AttributeConverter<Set<AlertConfigurationNotificationType>, String> {
	@Override
	public String convertToDatabaseColumn(Set<AlertConfigurationNotificationType> set) {
		if (CollectionUtils.isEmpty(set)) {
			return null;
		}

		return set.stream().map(Enum::name).collect(Collectors.joining(","));
	}

	@Override
	public Set<AlertConfigurationNotificationType> convertToEntityAttribute(String set) {
		if (StringUtils.isEmpty(set)) {
			return Collections.emptySet();
		}

		return Arrays.stream(set.split(",")).map(AlertConfigurationNotificationType::valueOf)
				.collect(Collectors.toSet());
	}
}
