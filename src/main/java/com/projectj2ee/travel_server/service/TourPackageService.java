package com.projectj2ee.travel_server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectj2ee.travel_server.dto.request.PackageDestinationRequest;
import com.projectj2ee.travel_server.dto.request.PackageFeatureRequest;
import com.projectj2ee.travel_server.dto.request.TourPackageRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.dto.response.PageResponse;
import com.projectj2ee.travel_server.dto.response.TourPackageResponse;
import com.projectj2ee.travel_server.entity.TourPackage;
import com.projectj2ee.travel_server.mapper.TourPackageMapper;
import com.projectj2ee.travel_server.repository.CompanyRepository;
import com.projectj2ee.travel_server.repository.TourPackageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TourPackageService {
    @Autowired
    private final CloudinaryService cloudinaryService;
    private final TourPackageRepository tourPackageRepository;
    private  TourPackageMapper tourPackageMapper;
    private final GoogleDriveService googleDriveService;

    private final CompanyRepository companyRepository;
    private final PackageFeatureService packageFeatureService;
    private final PackageDestinationService packageDestination;

    public PageResponse<TourPackageResponse> getAllTourPackage(int page, int size){
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page-1,size,sort);
        var pageData = tourPackageRepository.findAll(pageable);
        List<TourPackage> data = pageData.getContent();
        List<TourPackageResponse> responses = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (TourPackage t : data){
            TourPackageResponse tmp = tourPackageMapper.toResponse(t);
            try {
                if (t.getImage()!=null) tmp.setImageUrl(objectMapper.readValue(t.getImage(),List.class));
                responses.add(tmp);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return PageResponse.<TourPackageResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(responses)
                .build();
    }

    public ApiResponse<TourPackageResponse> getTourPackageById(int id){
        TourPackage entity = tourPackageRepository.findById(id).orElseThrow(()->new RuntimeException("Tour-package not found"));
        TourPackageResponse result = tourPackageMapper.toResponse(entity);
        ObjectMapper objectMapper = new ObjectMapper();
        if (entity.getImage()!=null){
            try {
                result.setImageUrl(objectMapper.readValue(entity.getImage(),List.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return new ApiResponse<TourPackageResponse>(HttpStatus.OK.value(), "Success",result);
    }

    public PageResponse<TourPackage> getAllTourPackageByDepart(String depart,int page, int size){
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page-1,size,sort);
        var pageData = tourPackageRepository.findByDepart(depart,pageable);

        return PageResponse.<TourPackage>builder()
                .statusCode(HttpStatus.OK.value())
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().toList())
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<TourPackage> addTourPackage(TourPackageRequest tourPackageRequest, MultipartFile[] files, MultipartFile fileTxt) {
        companyRepository.findById( tourPackageRequest.getCompanyId()).orElseThrow(()->new RuntimeException("Company Id not exits"));
        TourPackage entity = tourPackageMapper.toEntity(tourPackageRequest);
        entity.setStatus(true);
        String tourCode = generaTourCode();
        entity.setTourCode(tourCode);


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

        String urlPrice = googleDriveService.uploadFile(fileTxt,entity.getTourCode());
        entity.setPriceDetail(urlPrice);
        tourPackageRepository.save(entity);

        TourPackage update = tourPackageRepository.findByTourCode(tourCode);
        // Thêm packageFeature
        if (!tourPackageRequest.getTourFeatureId().isEmpty()) {
            List<Integer> id = new ArrayList<>(tourPackageRequest.getTourFeatureId());
            for (int i : id){
                packageFeatureService.addPackageFeature(new PackageFeatureRequest(update.getId(),i));
            }
        }
        // Thêm packageDestination
        if (!tourPackageRequest.getDestinationId().isEmpty()){
            List<Integer> id = new ArrayList<>(tourPackageRequest.getDestinationId());
            for (int i : id){
                packageDestination.add(new PackageDestinationRequest(update.getId(),i,1));
            }
        }


        return new ApiResponse<TourPackage>(HttpStatus.CREATED.value(), "Create Success",entity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<TourPackage> editTourPackage(int id,TourPackageRequest tourPackageRequest) {
        Optional<TourPackage> entityOtp = tourPackageRepository.findById(id);
        TourPackage entity;
        if (entityOtp.isPresent()) {
            entity = entityOtp.orElseGet(TourPackage::new);
        }else {
            throw new RuntimeException("TourPackage not found");
        }
        companyRepository.findById( tourPackageRequest.getCompanyId()).orElseThrow(()->new RuntimeException("Company Id not exits"));
        entity = tourPackageMapper.toEntity(tourPackageRequest);
        entity.setId(id);
        entity.setStatus(true);


        tourPackageRepository.save(entity);
        return new ApiResponse<TourPackage>(HttpStatus.OK.value(), "Update Success",entity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<String> addImageTourPackage(MultipartFile[] files, int id){
        Optional<TourPackage> entityOtp = tourPackageRepository.findById(id);
        TourPackage entity;
        if (entityOtp.isPresent()) {
            entity = entityOtp.orElseGet(TourPackage::new);
        }else {
            throw new RuntimeException("TourPackage not found");
        }

        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : files){
            imageUrls.add(cloudinaryService.uploadImage(file));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonImages = objectMapper.writeValueAsString(imageUrls);
            log.info(String.valueOf(jsonImages.length()));
            entity.setImage(jsonImages);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        tourPackageRepository.save(entity);
        return new ApiResponse<String>(HttpStatus.OK.value(), "Update Success");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<TourPackage> deleteTourPackage(int id){
        TourPackage entity = tourPackageRepository.findById(id).orElseThrow(()->new RuntimeException("Tour-package not found"));
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
