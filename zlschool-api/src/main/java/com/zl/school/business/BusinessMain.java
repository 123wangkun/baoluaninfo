package com.zl.school.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.zl.school.business.dao")
@ComponentScan("com.zl.school.business")
public class BusinessMain {

    public static void main(String[] args) {
        SpringApplication.run(BusinessMain.class, args);
    }

}
