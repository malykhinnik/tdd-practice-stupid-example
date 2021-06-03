package com.stringconcat.repositories;

import com.stringconcat.domain.alert.AlertConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AlertConfigurationRepository extends JpaRepository<AlertConfiguration, Long> {
	Set<AlertConfiguration> findAllByCompany_Id(Long companyId);

	AlertConfiguration findByIdAndCompany_Id(Long targetId, Long companyId);
}
