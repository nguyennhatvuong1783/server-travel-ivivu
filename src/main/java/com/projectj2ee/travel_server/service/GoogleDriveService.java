package com.projectj2ee.travel_server.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
@Slf4j
public class GoogleDriveService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static  final String SERVICE_ACCOUNT_KEY_PATH = getPathToGoogleCredentials();

    private static String getPathToGoogleCredentials() {
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "cred.json");
        return filePath.toString();
    }

    public String uploadFile(MultipartFile file, String tourCode)  {
        File fileupload = null;
        try {
            fileupload = convert(file,tourCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try{
            String folderId = "1BefYj102tYApKTKP5Vz3u_1N9uI0a_Z8";
            Drive drive = createDrive();
            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(fileupload.getName());
            fileMetaData.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("text/plain",fileupload);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id").execute();
            String fileUrl = "https://drive.google.com/uc?export=download&id="+uploadedFile.getId();
            log.info(fileUrl);
            cleanDisk(fileupload);
            return fileUrl;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    private void cleanDisk(File file){
        try{
            Path filePath = file.toPath();
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Drive createDrive() throws IOException, GeneralSecurityException {
        GoogleCredential credentials = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));
        return new Drive.Builder(
                (HttpTransport) GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credentials)
                .build();
    }

    private File convert(MultipartFile file, String tourCode) throws IOException{
        assert file.getOriginalFilename() != null;
        File convFile = new File(StringUtils.join(tourCode, getFileName(file.getOriginalFilename())[1]));
        try(InputStream is = file.getInputStream()){
            Files.copy(is, convFile.toPath());
        }
        return convFile;
    }

    public String[] getFileName(String originalName){
        return originalName.split("\\.");
    }

}
