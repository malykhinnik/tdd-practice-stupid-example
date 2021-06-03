package com.stringconcat.domain.usercampaignview;

import com.stringconcat.dto.Caption;
import com.stringconcat.util.EnumUtils;

public enum UserCampaignViewFieldType implements Caption {
	ID,
	TYPE,
	NAME;

	private final String caption = EnumUtils.getCaption(this);

	public String getCaption() {
		return caption;
	}
}
