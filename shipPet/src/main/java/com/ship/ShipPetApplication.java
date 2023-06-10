package com.ship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShipPetApplication {
    private static final Logger log = LoggerFactory.getLogger(ShipPetApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ShipPetApplication.class, args);
        log.info("宠物医院管理系统2.0启动");
        log.info("http://localhost:8085/login.html");
    }
}
