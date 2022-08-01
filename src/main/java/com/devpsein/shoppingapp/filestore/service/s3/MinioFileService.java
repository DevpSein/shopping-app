package com.devpsein.shoppingapp.filestore.service.s3;

import com.devpsein.shoppingapp.filestore.config.S3ConfigProperties;
import com.devpsein.shoppingapp.filestore.service.s3.FileService;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinioFileService implements FileService {
    private final MinioClient minioClient;
    private final S3ConfigProperties s3ConfigProperties;

    @Override
    public void save(String id, String contentType ,InputStream isFile)  {
       try {
           var object = PutObjectArgs.builder()
                   .object(id)
                   .contentType(contentType)
                   .stream(isFile,isFile.available(),-1)
                   .bucket(s3ConfigProperties.getBucket())
                   .build();
           minioClient.putObject(object);

       }catch (Exception e){
           log.error("File put error",e);
       }
    }
    @Override
    public void delete(String id) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(s3ConfigProperties.getBucket())
                    .object(id)
                    .build());
        }catch (Exception e){
            log.error("File put error",e);
        }

    }

    @Override
    public byte[] get(String id) {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(s3ConfigProperties.getBucket())
                    .object(id)
                    .build()).readAllBytes();
        }catch (Exception e){
            log.error("File put error");
        }
        return new byte[0];
    }
}