package com.forge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForgePetApplication {
    private static final Logger log = LoggerFactory.getLogger(ForgePetApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ForgePetApplication.class, args);
        log.info("宠物医院管理系统启动");
        log.info("http://localhost:8080/login.html");
    }
}
