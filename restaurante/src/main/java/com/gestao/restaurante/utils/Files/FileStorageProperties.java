package com.gestao.restaurante.utils.Files;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="file")
public class FileStorageProperties {

    private String uploadDir = "uploads";

    public String uploadDir(){
        return uploadDir;
    }
    
    public void uploadDir(String uploadDir){
        this.uploadDir = uploadDir;
    }
}