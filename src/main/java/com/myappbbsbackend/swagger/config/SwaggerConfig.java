package com.myappbbsbackend.swagger.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/19 15:29
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig  {
//    http://localhost:8080/swagger-ui.html
@Bean
public Docket api() {
    return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.myappbbsbackend.api.controller"))
            .paths(PathSelectors.any())
            .build();
}

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基于Swagger3.0.0的接口文档")
                .description("api信息列表")
                .version("2.0")
                .contact(new Contact("小火锅","www.baidu.com","1757472335@qq.com"))
                .build();
    }

}
