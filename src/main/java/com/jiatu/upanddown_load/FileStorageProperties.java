package com.jiatu.upanddown_load;

import org.springframework.boot.context.properties.ConfigurationProperties;
/*
*文件存储路径
 */
//注解让application.yml中以file作为前缀的属性关联到该类的字段上。
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
