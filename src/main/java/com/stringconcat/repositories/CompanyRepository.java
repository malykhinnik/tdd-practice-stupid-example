package com.stringconcat.repositories;

import com.stringconcat.domain.Company;
import com.stringconcat.dto.CompanyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	@Query("SELECT c FROM Company c WHERE c.id = :id")
	Company findById(@Param("id") Long id);

	@Query("SELECT c FROM Company c WHERE c.name = :name")
	Company findByName(@Param("name") String name);
}
