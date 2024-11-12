package com.projectj2ee.travel_server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectj2ee.travel_server.dto.request.TourPackageRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.dto.response.PageResponse;
import com.projectj2ee.travel_server.entity.TourPackage;
import com.projectj2ee.travel_server.mapper.TourPackageMapper;
import com.projectj2ee.travel_server.repository.CompanyRepository;
import com.projectj2ee.travel_server.repository.TourPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TourPackageService {
    @Autowired
    private final CloudinaryService cloudinaryService;
    private final TourPackageRepository tourPackageRepository;
    private  TourPackageMapper tourPackageMapper;

    private final CompanyRepository companyRepository;



    public PageResponse<TourPackage> getAllTourPackage(int page, int size){
        Sort sort = Sort.by("package_id").descending();
        Pageable pageable = PageRequest.of(page-1,size,sort);
        var pageData = tourPackageRepository.findAll(pageable);

        return PageResponse.<TourPackage>builder()
                .statusCode(HttpStatus.OK.value())
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().toList())
                .build();
    }

    public ApiResponse<TourPackage> getTourPackageById(String id){
        TourPackage entity = tourPackageRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Tour-package not found"));
        return new ApiResponse<TourPackage>(HttpStatus.OK.value(), "Success",entity);
    }

    public ApiResponse<TourPackage> addTourPackage(TourPackageRequest tourPackageRequest, MultipartFile[] files) {
        companyRepository.findById((long) tourPackageRequest.getCompanyId()).orElseThrow(()->new RuntimeException("Company Id not exits"));
        TourPackage entity = tourPackageMapper.toEntity(tourPackageRequest);
        entity.setStatus(true);
        entity.setTourCode(generaTourCode());

        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : files){
            imageUrls.add(cloudinaryService.uploadImage(file));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonImages = objectMapper.writeValueAsString(imageUrls);
            entity.setImage(jsonImages);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        tourPackageRepository.save(entity);
        return new ApiResponse<TourPackage>(HttpStatus.CREATED.value(), "Create Success",entity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<TourPackage> editTourPackage(String id,TourPackageRequest tourPackageRequest,MultipartFile[] files) {
        TourPackage entity = tourPackageRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Tour-package not found"));
        companyRepository.findById((long) tourPackageRequest.getCompanyId()).orElseThrow(()->new RuntimeException("Company Id not exits"));
        entity = tourPackageMapper.toEntity(tourPackageRequest);
        entity.setId(Integer.parseInt(id));
        entity.setStatus(true);

        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : files){
            imageUrls.add(cloudinaryService.uploadImage(file));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonImages = objectMapper.writeValueAsString(imageUrls);
            entity.setImage(jsonImages);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        tourPackageRepository.save(entity);
        return new ApiResponse<TourPackage>(HttpStatus.OK.value(), "Update Success",entity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<TourPackage> deleteTourPackage(String id){
        TourPackage entity = tourPackageRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Tour-package not found"));
        entity.setStatus(false);
        tourPackageRepository.save(entity);
        return new ApiResponse<TourPackage>(HttpStatus.OK.value(), "Delete Success",entity);
    }

    private String generaTourCode(){
        Optional<TourPackage> lastTour = tourPackageRepository.findFirstByOrderByTourCodeDesc();
        String newTourCode;

        if (lastTour.isPresent()){
            String lastCode = lastTour.get().getTourCode();

            String prefix = lastCode.substring(0,2); // TO
            int numberPart = Integer.parseInt(lastCode.substring(2)); // 4235

            newTourCode = prefix + String.format("%04d", numberPart + 1);
        }else{
            newTourCode = "TO0001";
        }

        return newTourCode;

    }
}
