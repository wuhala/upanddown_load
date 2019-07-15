package com.jiatu.upanddown_load;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class UpanddownLoadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpanddownLoadApplication.class, args);
    }

}
