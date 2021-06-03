package com.stringconcat.config;

import com.stringconcat.mapping.AlertConfiguration2AlertConfigurationResponseMapping;
import com.stringconcat.mapping.AlertConfigurationCondition2AlertConfigurationConditionDtoMapping;
import com.stringconcat.mapping.AlertConfigurationConditionDto2AlertConfigurationConditionMapping;
import com.stringconcat.mapping.AlertConfigurationRequest2AlertConfigurationMapping;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.addMappings(new AlertConfigurationRequest2AlertConfigurationMapping());
		modelMapper.addMappings(new AlertConfigurationConditionDto2AlertConfigurationConditionMapping());
		modelMapper.addMappings(new AlertConfiguration2AlertConfigurationResponseMapping());
		modelMapper.addMappings(new AlertConfigurationCondition2AlertConfigurationConditionDtoMapping());
		return modelMapper;
	}
}
