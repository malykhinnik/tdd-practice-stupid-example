package com.stringconcat.util;

import com.stringconcat.dto.Caption;
import com.stringconcat.dto.EnumDto;
import org.springframework.util.StringUtils;

public class EnumUtils {
	public static String getCaption(Enum<?> source) {
		if (source == null) {
			return null;
		}

		return StringUtils.capitalize(source.name().replace("_", " ").toLowerCase());
	}

	public static EnumDto toEnumDto(Enum<?> source) {
		if (source == null) {
			return null;
		}

		EnumDto target = new EnumDto();
		target.setName(source.name());
		if (source instanceof Caption) {
			target.setCaption(((Caption) source).getCaption());
		}

		return target;
	}
}
