package com.ruraaratech.p4dafrica.firebase.File;

import com.google.firebase.cloud.StorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class FileService {
    private final Logger logger = LoggerFactory.getLogger(FileService.class);

    public URL uploadFile(MultipartFile myFile, String fileName) throws IOException {
        logger.info("converting image....");
        InputStream file =  new BufferedInputStream(myFile.getInputStream());
        logger.info("uploading file....");
        StorageClient.getInstance().bucket()
                .create(fileName, file);
        return signedUrl(fileName);

    }

    public String generateFileName(MultipartFile multiPart) {
        String fileName = multiPart.getOriginalFilename();
        fileName = UUID.randomUUID().toString().concat(getExtension(fileName));
        String name="p4d-documents/"+ fileName;
        return name;

    }

    public URL signedUrl(String fileName) {
       logger.info("getting file....");
        return StorageClient.getInstance().bucket().get(fileName).signUrl(14, TimeUnit.DAYS);

    }

    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
