package com.stringconcat.rest;

import com.stringconcat.domain.alert.AlertConfiguration;
import com.stringconcat.dto.alert.AlertConfigurationRequest;
import com.stringconcat.dto.alert.AlertConfigurationResponse;
import com.stringconcat.services.AlertConfigurationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AlertsConfigurationsController.URL)
public class AlertsConfigurationsController {
	public static final String URL = "/rest/api/v1/alerts";

	@Autowired
	private AlertConfigurationService alertConfigurationService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/configurations")
	public List<AlertConfigurationResponse> getAlertsConfigurations() {
		return alertConfigurationService.getAll().stream()
				.map(ac -> modelMapper.map(ac, AlertConfigurationResponse.class)).collect(Collectors.toList());
	}

	@GetMapping("{companyId}/configurations")
	public List<AlertConfigurationResponse> getAlertsConfigurationsByCompanyId(@PathVariable Long companyId) {
		return alertConfigurationService.getByCompanyId(companyId).stream()
				.map(ac -> modelMapper.map(ac, AlertConfigurationResponse.class)).collect(Collectors.toList());
	}

	@PostMapping("{companyId}/configurations")
	public AlertConfigurationResponse postAlertsConfigurations(@PathVariable Long companyId,
			@RequestBody @Valid AlertConfigurationRequest request) {
		return modelMapper
				.map(alertConfigurationService.create(companyId, modelMapper.map(request, AlertConfiguration.class)),
						AlertConfigurationResponse.class);
	}

	@PutMapping("{companyId}/configurations/{id}")
	public AlertConfigurationResponse putAlertsConfigurations(@PathVariable Long companyId, @PathVariable Long id,
			@RequestBody @Valid AlertConfigurationRequest request) {
		return modelMapper.map(alertConfigurationService
						.update(companyId, id, modelMapper.map(request, AlertConfiguration.class)),
				AlertConfigurationResponse.class);
	}

	@DeleteMapping("{companyId}/configurations/{id}")
	public void deleteAlertsConfigurations(@PathVariable Long companyId, @PathVariable Long id) {
		alertConfigurationService.delete(companyId, id);
	}
}
