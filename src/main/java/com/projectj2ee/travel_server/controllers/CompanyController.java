package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.CompanyRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Company;
import com.projectj2ee.travel_server.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/")
    public ApiResponse<List<Company>> getAllCompany(){
        return companyService.getAllCompany();
    }

    @GetMapping("/{id}")
    public ApiResponse<Company> getCompanyById(@PathVariable("id") String id){
        return companyService.getCompanyById(id);
    }

    @PostMapping("/create")
    public ApiResponse<Company> createCompany(@RequestBody CompanyRequest companyRequest){
        return companyService.addCompany(companyRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<Company> editCompany(@PathVariable("id") String id, @RequestBody CompanyRequest companyRequest){
        return companyService.editCompany(id, companyRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Company> deleteCompany(@PathVariable("id") String id){
        return companyService.deleteCompanyd(id);
    }
}
