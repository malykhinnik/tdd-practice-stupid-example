package com.stringconcat.domain.alert;

import com.stringconcat.dto.Caption;
import com.stringconcat.util.EnumUtils;

public enum AlertConfigurationNotificationType implements Caption {
	SYSTEM,
	EMAIL,
	SMS,
	SLACK;

	private final String caption = EnumUtils.getCaption(this);

	public String getCaption() {
		return caption;
	}
}
