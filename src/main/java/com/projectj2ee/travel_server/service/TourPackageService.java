package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.TourPackageRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.TourPackage;
import com.projectj2ee.travel_server.mapper.TourPackageMapper;
import com.projectj2ee.travel_server.repository.CompanyRepository;
import com.projectj2ee.travel_server.repository.PriceDetailRepository;
import com.projectj2ee.travel_server.repository.TourPackageRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TourPackageService {
    @Autowired
    private final TourPackageRepository tourPackageRepository;

    @Autowired
    private TourPackageMapper tourPackageMapper;

    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private final PriceDetailRepository priceDetailRepository;

    public ApiResponse<List<TourPackage>> getAllTourPackage(){
        return new ApiResponse<List<TourPackage>>(HttpStatus.OK.value(), "Success",tourPackageRepository.findAll());
    }

    public ApiResponse<TourPackage> getTourPackageById(String id){
        TourPackage entity = tourPackageRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Tour-package not found"));
        return new ApiResponse<TourPackage>(HttpStatus.OK.value(), "Success",entity);
    }

    public ApiResponse<TourPackage> addTourPackge(TourPackageRequest tourPackageRequest){
        priceDetailRepository.findById((long) tourPackageRequest.getPriceDetailId()).orElseThrow(()-> new RuntimeException("Price id not exits"));
        companyRepository.findById((long) tourPackageRequest.getCompanyId()).orElseThrow(()->new RuntimeException("Company Id not exits"));
        TourPackage entity = tourPackageMapper.toEntity(tourPackageRequest);
        entity.setStatus(true);
        tourPackageRepository.save(entity);
        return new ApiResponse<TourPackage>(HttpStatus.CREATED.value(), "Create Success",entity);
    }

    public ApiResponse<TourPackage> editTourPackage(String id,TourPackageRequest tourPackageRequest){
        TourPackage entity = tourPackageRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Tour-package not found"));
        priceDetailRepository.findById((long) tourPackageRequest.getPriceDetailId()).orElseThrow(()-> new RuntimeException("Price id not exits"));
        companyRepository.findById((long) tourPackageRequest.getCompanyId()).orElseThrow(()->new RuntimeException("Company Id not exits"));
        entity = tourPackageMapper.toEntity(tourPackageRequest);
        entity.setId(Integer.parseInt(id));
        entity.setStatus(true);
        tourPackageRepository.save(entity);
        return new ApiResponse<TourPackage>(HttpStatus.OK.value(), "Update Success",entity);
    }

    public ApiResponse<TourPackage> deleteTourPackage(String id){
        TourPackage entity = tourPackageRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Tour-package not found"));
        entity.setStatus(false);
        tourPackageRepository.save(entity);
        return new ApiResponse<TourPackage>(HttpStatus.OK.value(), "Delete Success",entity);
    }
}
