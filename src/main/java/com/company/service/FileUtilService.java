package com.company.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtilService {
    private final static String PARENT_URL = "/home/cosmos/Images/";

    public static String saveImage(final MultipartFile multipartFile) throws IOException {
        return PARENT_URL + getImageUrl();
    }

    private static String getImageUrl() {
        return UUID.randomUUID().toString();
    }


}
