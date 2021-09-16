package com.example.domain.service;

import java.io.File;

public interface StorageServiceInterface {
    String storeFile(String filename, File file, FileType fileType) throws Exception;
    String getUrlFile(String filename, FileType fileType) throws Exception;
}
