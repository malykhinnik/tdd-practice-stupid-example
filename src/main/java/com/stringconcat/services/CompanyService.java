package com.stringconcat.services;

import com.stringconcat.domain.Company;
import com.stringconcat.dto.CompanyDto;
import com.stringconcat.exceptions.ArgumentValidationException;
import com.stringconcat.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyService {
	public static final String CONSTANT_COMPANY_NAME = "STRINGCONCAT";
	@Autowired
	private CompanyRepository companyRepository;

	public Company getById(Long id) {
		return id == null ? null : companyRepository.findById(id);
	}

	public Company save(CompanyDto companyDto) {
		String name = companyDto.getName();
		if (name.equals(CONSTANT_COMPANY_NAME)) {
			return null;
		}
		Company company = new Company();
		fillCompany(company, companyDto);
		company = companyRepository.save(company);
		return company;
	}

	private void fillCompany(Company company, CompanyDto companyDto) {
		company.setName(companyDto.getName());
		company.setEnabled(companyDto.isEnabled());
		company.setCompanyType(companyDto.getCompanyType());
	}

	@Transactional
	public Company update(Long id, CompanyDto companyDto) {
		Company company = getById(id);
		String name = companyDto.getName();
		if (name.equals(CONSTANT_COMPANY_NAME)) {
			return null;
		}
		fillCompany(company, companyDto);
		company = companyRepository.save(company);
		return company;
	}

	public void delete(Long id) {
		String name = companyRepository.findById(id).getName();
		if (name.equals(CONSTANT_COMPANY_NAME)) {
			return;
		}
		companyRepository.delete(getById(id));
	}

	public Company getByName(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}

		return companyRepository.findByName(name);
	}

	public void validateCompanyId(Long id) {
		if (getById(id) == null) {
			throw new ArgumentValidationException(String.format("There is no company with id [%s]",id));
		}
		String name = getById(id).getName();
		if (name.equals(CompanyService.CONSTANT_COMPANY_NAME)) {
			throw new ArgumentValidationException(String.format("Company [%s] cant be changed", name));
		}
	}

	public void validateCompanyNameUpdate(Long id, CompanyDto dto) {
		Company company = getById(id);
		if (!company.getName().equals(dto.getName())) {
			String newName = dto.getName();
			if (getByName(newName) != null) {
				throw new ArgumentValidationException(String.format("Company with name [%s] already exists", newName));
			}
		}
	}

	public List<Company> getAllCompany() {
		return companyRepository.findAll();
	}
}
