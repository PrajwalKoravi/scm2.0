package com.scm.services;

import org.springframework.web.multipart.MultipartFile;


public interface ImageService {

    public String uploadImage(MultipartFile contactImage, String filename);

    String getUrlFromPublicId(String publicId);

}
