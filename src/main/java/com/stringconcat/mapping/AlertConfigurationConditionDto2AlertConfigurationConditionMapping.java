package com.stringconcat.mapping;

import com.stringconcat.domain.alert.AlertConfigurationCondition;
import com.stringconcat.domain.usercampaignview.UserCampaignViewFieldType;
import com.stringconcat.domain.usercampaignview.UserCampaignViewFilterType;
import com.stringconcat.dto.EnumDto;
import com.stringconcat.dto.alert.AlertConfigurationConditionDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;

public class AlertConfigurationConditionDto2AlertConfigurationConditionMapping extends PropertyMap<AlertConfigurationConditionDto, AlertConfigurationCondition> {
	@Override
	protected void configure() {
		using(new AbstractConverter<EnumDto, UserCampaignViewFieldType>() {
			@Override
			protected UserCampaignViewFieldType convert(EnumDto field) {
				return field == null ? null : UserCampaignViewFieldType.valueOf(field.getName());
			}
		}).map(source.getField()).setField(null);
		using(new AbstractConverter<EnumDto, UserCampaignViewFilterType>() {
			@Override
			protected UserCampaignViewFilterType convert(EnumDto conditions) {
				return conditions == null ? null : UserCampaignViewFilterType.valueOf(conditions.getName());
			}
		}).map(source.getConditions()).setConditions(null);
	}
}
