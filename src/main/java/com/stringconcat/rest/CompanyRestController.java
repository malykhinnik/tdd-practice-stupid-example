package com.stringconcat.rest;

import com.stringconcat.dto.CompanyDto;
import com.stringconcat.exceptions.ArgumentValidationException;
import com.stringconcat.services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/api/v1/company")
public class CompanyRestController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.POST)
	public CompanyDto addCompany(@RequestBody @Valid CompanyDto companyDto) {
		if (companyService.getByName(companyDto.getName()) != null) {
			throw new ArgumentValidationException(String.format("Company [%s] already exists", companyDto.getName()));
		}
		return modelMapper.map(companyService.save(companyDto), CompanyDto.class);
	}

	@RequestMapping(value = "{id}",method = RequestMethod.PUT)
	public CompanyDto updateCompany(@PathVariable Long id, @RequestBody @Valid CompanyDto companyDto) {
		companyService.validateCompanyId(id);
		companyService.validateCompanyNameUpdate(id, companyDto);
		return modelMapper.map(companyService.update(id, companyDto), CompanyDto.class);
	}

	@RequestMapping(value = "{id}",method = RequestMethod.DELETE)
	public void deleteCompany(@PathVariable Long id) {
		companyService.validateCompanyId(id);
		companyService.delete(id);
	}

	@GetMapping(value = "{id}")
	public CompanyDto getCompany(@PathVariable Long id) {
		return modelMapper.map(companyService.getById(id), CompanyDto.class);
	}

	@GetMapping(value = "all")
	public List<CompanyDto> getAllCompany() {
		return companyService.getAllCompany().stream()
				.map(c -> modelMapper.map(c, CompanyDto.class)).collect(Collectors.toList());
	}
}
