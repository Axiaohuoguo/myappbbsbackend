package com.myappbbsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
//@ServletComponentScan
//@ComponentScan(basePackages = "com.myappbbsbackend.swagger.config")
public class MyappbbsbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyappbbsbackendApplication.class, args);
    }

}
