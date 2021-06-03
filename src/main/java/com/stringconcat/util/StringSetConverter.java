package com.stringconcat.util;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {
	@Override
	public String convertToDatabaseColumn(Set<String> set) {
		return convertToString(set);
	}

	public static String convertToString(Set<String> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		return String.join(",", list);
	}

	@Override
	public Set<String> convertToEntityAttribute(String joined) {
		return convertToSet(joined);
	}

	public static Set<String> convertToSet(String joined) {
		if (StringUtils.isEmpty(joined)) {
			return Collections.emptySet();
		}

		return new HashSet<>(Arrays.asList(joined.split(",")));
	}
}
