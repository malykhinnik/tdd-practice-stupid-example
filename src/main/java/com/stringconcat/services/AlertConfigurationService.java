package com.stringconcat.services;

import com.stringconcat.domain.alert.AlertConfiguration;
import com.stringconcat.domain.alert.AlertConfigurationCondition;
import com.stringconcat.repositories.AlertConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlertConfigurationService {
	@Autowired
	private AlertConfigurationRepository alertConfigurationRepository;
	@Autowired
	private CompanyService companyService;

	public AlertConfiguration create(Long companyId, AlertConfiguration source) {
		AlertConfiguration target = new AlertConfiguration();
		target.setCompany(companyService.getById(companyId));
		update(source, target);
		return alertConfigurationRepository.save(target);
	}

	private void update(AlertConfiguration source, AlertConfiguration target) {
		target.setName(source.getName());
		target.setNotificationTypes(source.getNotificationTypes());
		setConditions(source, target);
	}

	private void setConditions(AlertConfiguration source, AlertConfiguration target) {
		if (CollectionUtils.isEmpty(source.getConditions())) {
			Optional.ofNullable(target.getConditions()).ifPresent(Set::clear);
			return;
		}

		if (target.getConditions() == null) {
			target.setConditions(new HashSet<>());
			source.getConditions().forEach(s -> {
				s.setAlertConfiguration(target);
				target.getConditions().add(s);
			});
			return;
		}

		Map<Long, AlertConfigurationCondition> sourceSortByOrder = source.getConditions().stream()
				.collect(Collectors.toMap(AlertConfigurationCondition::getId, v -> v));
		for (Iterator<AlertConfigurationCondition> i = target.getConditions().iterator(); i.hasNext(); ) {
			AlertConfigurationCondition updateTargetSort = i.next();
			AlertConfigurationCondition updateSourceSort = sourceSortByOrder.get(updateTargetSort.getId());
			if (updateSourceSort == null) {
				i.remove();
				continue;
			}

			updateTargetSort.setField(updateSourceSort.getField());
			updateSourceSort.setConditions(updateSourceSort.getConditions());
			updateSourceSort.setFieldValues(updateSourceSort.getFieldValues());
			sourceSortByOrder.remove(updateTargetSort.getId());
		}

		sourceSortByOrder.forEach((sortOrder, newSourceSort) -> target.getConditions().add(new AlertConfigurationCondition() {{
			setAlertConfiguration(target);
			setField(newSourceSort.getField());
			setConditions(newSourceSort.getConditions());
			setFieldValues(newSourceSort.getFieldValues());
		}}));
	}

	public AlertConfiguration update(Long companyId, Long targetId, AlertConfiguration source) {
		AlertConfiguration target = getAlertConfiguration(targetId, companyId);
		update(source, target);
		return alertConfigurationRepository.save(target);
	}

	private AlertConfiguration getAlertConfiguration(Long targetId, Long companyId) {
		AlertConfiguration target = alertConfigurationRepository.findByIdAndCompany_Id(targetId, companyId);
		if (target == null) {
			throw new IllegalArgumentException("Not found");
		}

		return target;
	}

	public Set<AlertConfiguration> getByCompanyId(Long companyId) {
		return alertConfigurationRepository.findAllByCompany_Id(companyId);
	}

	@Transactional
	public void delete(Long companyId, Long alertConfigurationId) {
		AlertConfiguration target = getAlertConfiguration(alertConfigurationId, companyId);
		if (!target.getCompany().getId().equals(companyId)) {
			throw new IllegalArgumentException("No access");
		}

		alertConfigurationRepository.delete(alertConfigurationId);
	}

	public Collection<AlertConfiguration> getAll() {
		return alertConfigurationRepository.findAll();
	}
}
