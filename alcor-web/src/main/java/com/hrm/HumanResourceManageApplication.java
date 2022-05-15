package com.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan("com.hrm.mapper")
public class HumanResourceManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanResourceManageApplication.class, args);
    }

}
