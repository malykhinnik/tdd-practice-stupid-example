package com.stringconcat.services;

import com.stringconcat.ApplicationTests;
import com.stringconcat.domain.enums.CompanyType;
import com.stringconcat.dto.CompanyDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class CompanyServiceTest extends ApplicationTests {
	@Autowired
	private CompanyService companyService;

	@Test
	public void companyFlowTest() {
		String name = "TEST_COMP";
		assertEquals(null, companyRepository.findByName(name));

		CompanyDto dto = new CompanyDto();
		dto.setName(name);
		dto.setCompanyType(CompanyType.Publisher);

		companyService.save(dto);
		assertEquals(name, companyRepository.findByName(name).getName());
		assertEquals(CompanyType.Publisher, companyRepository.findByName(name).getCompanyType());

		Long id = companyRepository.findByName(name).getId();

		dto.setCompanyType(CompanyType.DataProvider);
		companyService.update(id, dto);
		assertEquals(CompanyType.DataProvider, companyRepository.findByName(name).getCompanyType());

		companyService.delete(id);
		assertEquals(null, companyRepository.findByName(name));
	}
}
