package com.projectj2ee.travel_server.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file){
        assert file.getOriginalFilename() != null;
        String publicValue = generatePublicValue(file.getOriginalFilename());
        String extension = getFileName(file.getOriginalFilename())[1];
        if (!extension.equalsIgnoreCase("jpg")){
            throw new IllegalArgumentException("Only .jpg files are allowed");
        }
        File fileUpload = null;
        try {
            fileUpload = convert(file);
            cloudinary.uploader().upload(fileUpload, ObjectUtils.asMap("public_id", publicValue));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        cleanDisk(fileUpload);
        return  cloudinary.url().generate(StringUtils.join(publicValue,".",extension));
    }


    private File convert(MultipartFile file) throws IOException{
        assert file.getOriginalFilename() != null;
        File convFile = new File(StringUtils.join(generatePublicValue(file.getOriginalFilename()), getFileName(file.getOriginalFilename())[1]));
        try(InputStream is = file.getInputStream()){
            Files.copy(is, convFile.toPath());
        }
        return convFile;
    }

    private void cleanDisk(File file){
        try{
            Path filePath = file.toPath();
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generatePublicValue(String originalName){
        String filename = getFileName(originalName)[0];
        return StringUtils.join(UUID.randomUUID().toString(),"-",filename);

    }

    public String[] getFileName(String originalName){
        return originalName.split("\\.");
    }


}
