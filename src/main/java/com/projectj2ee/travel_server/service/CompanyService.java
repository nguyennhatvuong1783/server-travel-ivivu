package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.CompanyRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Company;
import com.projectj2ee.travel_server.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {
    @Autowired
    private final CompanyRepository companyRepository;


    public ApiResponse<List<Company>> getAllCompany(){
        return new ApiResponse<List<Company>>(HttpStatus.OK.value(), "Success",companyRepository.findAll());
    }

    public ApiResponse<Company> getCompanyById(String id){
        Company entity = companyRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Company not found"));
        return new ApiResponse<Company>(HttpStatus.OK.value(), "Success",entity);
    }

    public ApiResponse<Company> addCompany(CompanyRequest companyRequest){
        Company entity = new Company();
        entity.setName(companyRequest.getName());
        entity.setDescription(companyRequest.getDescription());
        entity.setStatus(true);
        companyRepository.save(entity);
        return new ApiResponse<Company>(HttpStatus.CREATED.value(), "Create success",entity);
    }

    public ApiResponse<Company> editCompany(String id,CompanyRequest companyRequest){
        Company entity = companyRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Company not found"));
        entity.setName(companyRequest.getName());
        entity.setDescription(companyRequest.getDescription());
        entity.setStatus(true);
        entity.setId(Integer.parseInt(id));
        companyRepository.save(entity);
        return new ApiResponse<Company>(HttpStatus.OK.value(), "Update success",entity);
    }

    public ApiResponse<Company> deleteCompanyd(String id){
        Company entity = companyRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Company not found"));
        entity.setStatus(false);
        return new ApiResponse<Company>(HttpStatus.OK.value(), "Delete success",entity);
    }


}
