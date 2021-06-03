package com.stringconcat.domain.usercampaignview;

import com.stringconcat.dto.Caption;
import com.stringconcat.util.EnumUtils;

public enum UserCampaignViewFilterType implements Caption {
	EQUALS,
	NOT_EQUALS,
	CONTAINS;

	private final String caption = EnumUtils.getCaption(this);

	public String getCaption() {
		return caption;
	}
}
