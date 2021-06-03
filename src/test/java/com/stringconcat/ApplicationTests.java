package com.stringconcat;

import com.stringconcat.domain.Company;
import com.stringconcat.domain.enums.CompanyType;
import com.stringconcat.repositories.AlertConfigurationRepository;
import com.stringconcat.repositories.CompanyRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("ci")
public abstract class ApplicationTests {
	@Autowired
	public CompanyRepository companyRepository;
	@Autowired
	AlertConfigurationRepository alertConfigurationRepository;
	public Company company;
	public Company otherCompany;
	@Before
	public void setUp() throws Exception {
		company = companyRepository.save(createCompany());
		Assert.assertNotNull(company);
		otherCompany = createCompany();
		otherCompany.setName("otherCompany");
		otherCompany = companyRepository.save(otherCompany);
		Assert.assertNotNull(otherCompany);
	}

	private Company createCompany() {
		Company company = new Company();
		company.setName("test-name-123");
		company.setCompanyType(CompanyType.Publisher);
		company.setEnabled(true);
		return company;
	}

	@After
	public void clean() {
		alertConfigurationRepository.deleteAll();
		companyRepository.deleteAll();
	}
}
