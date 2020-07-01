package com.jiatu.upanddown_load.service;

import com.jiatu.upanddown_load.FileStorageException;
import com.jiatu.upanddown_load.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    // 文件在本地存储的地址
    private final Path fileStorageLocation;

    /*
    *获取文件存储路径、创建
    * 存储文件到系统
     */
    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception ex){
            throw  new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /*
    * 加载文件
     */
    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());//获取文件名
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ignore) {

        }
        return fileName;
    }

    /*
    *加载文件为资源
     */
    public Resource loadFileAsResource(String fileName ){
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
           return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
