package com.stringconcat.mapping;

import com.stringconcat.domain.alert.AlertConfigurationCondition;
import com.stringconcat.domain.usercampaignview.UserCampaignViewFieldType;
import com.stringconcat.domain.usercampaignview.UserCampaignViewFilterType;
import com.stringconcat.dto.EnumDto;
import com.stringconcat.dto.alert.AlertConfigurationConditionDto;
import com.stringconcat.util.EnumUtils;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;

public class AlertConfigurationCondition2AlertConfigurationConditionDtoMapping extends PropertyMap<AlertConfigurationCondition,AlertConfigurationConditionDto> {
	@Override
	protected void configure() {
		using(new AbstractConverter<UserCampaignViewFieldType,EnumDto>() {
			@Override
			protected EnumDto convert(UserCampaignViewFieldType field) {
				return field == null ? null : EnumUtils.toEnumDto(field);
			}
		}).map(source.getField()).setField(null);
		using(new AbstractConverter<UserCampaignViewFilterType,EnumDto>() {
			@Override
			protected EnumDto convert(UserCampaignViewFilterType conditions) {
				return conditions == null ? null : EnumUtils.toEnumDto(conditions);
			}
		}).map(source.getConditions()).setConditions(null);
	}
}
