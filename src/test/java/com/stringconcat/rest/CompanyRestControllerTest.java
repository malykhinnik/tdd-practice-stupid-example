package com.stringconcat.rest;

import com.stringconcat.PortalApplicationTests;
import com.stringconcat.domain.enums.CompanyType;
import com.stringconcat.dto.CompanyDto;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CompanyRestControllerTest extends PortalApplicationTests {
	private static final String URL_TEMPLATE = "/rest/api/v1/company";

	@Test
	public void createCompanyExternalIdTest() throws Exception {
		CompanyDto companyDto = new CompanyDto();
		companyDto.setCompanyType(CompanyType.Publisher);
		companyDto.setEnabled(true);
		companyDto.setName("COMPANY TEST 123");

		mockMvc.perform(post(URL_TEMPLATE)
				.content(this.json(companyDto))
				.contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk());
		;
	}

	@Test
	public void createMultipleCompanyExternalIdTest() throws Exception {
		for (int i = 0; i < 100; i++) {
			CompanyDto companyDto = new CompanyDto();
			companyDto.setCompanyType(CompanyType.Publisher);
			companyDto.setEnabled(true);
			companyDto.setName("COMPANY TEST 123"+i);

			mockMvc.perform(post(URL_TEMPLATE)
					.content(this.json(companyDto))
					.contentType(contentType))
					.andDo(print())
					.andExpect(status().isOk());
			;
		}
	}
}
