package com.itxuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan
@SpringBootApplication
public class TliasManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasManagementApplication.class, args);
    }

}
