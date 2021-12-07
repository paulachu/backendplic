package com.example.domain.service;

import com.example.ConfigurationProperties;
import io.minio.*;
import io.minio.http.Method;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class MinioService implements StorageServiceInterface {
    private MinioClient minioClient;

    MinioService(ConfigurationProperties props)
    {
        minioClient =
                MinioClient.builder()
                        .endpoint(props.endpoint())
                        .credentials(props.username(), props.password())
                        .build();
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(FileType.Mesh.toString().toLowerCase()).build()))
            {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(FileType.Mesh.toString().toLowerCase()).build());
            }
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(FileType.Music.toString().toLowerCase()).build()))
            {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(FileType.Music.toString().toLowerCase()).build());
            }
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(FileType.Texture.toString().toLowerCase()).build()))
            {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(FileType.Texture.toString().toLowerCase()).build());
            }
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(FileType.Skybox.toString().toLowerCase()).build()))
            {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(FileType.Skybox.toString().toLowerCase()).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String storeFile(String filename, File file, FileType fileType) throws Exception
    {
        minioClient.putObject(
                PutObjectArgs.builder().bucket(fileType.toString().toLowerCase()).object(filename)
                    .stream(new FileInputStream(file), file.length(), -1)
                    .build());
        return getUrlFile(filename, fileType);
    }

    public String getUrlFile(String filename, FileType fileType) throws Exception
    {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(fileType.toString().toLowerCase())
                .object(filename)
                .expiry(2, TimeUnit.HOURS)
                .build());
    }

}
