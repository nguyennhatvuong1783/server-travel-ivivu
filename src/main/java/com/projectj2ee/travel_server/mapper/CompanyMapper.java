package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.CompanyRequest;
import com.projectj2ee.travel_server.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company companyRequestToCompany(CompanyRequest companyRequest);
}
