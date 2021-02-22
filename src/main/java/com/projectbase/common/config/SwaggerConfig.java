package com.projectbase.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private Boolean enable;

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                //设置是否开始Swagger
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wzm.projectbase.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建API基本信息
     * @return
     */
    private ApiInfo apiInfo(){
        Contact contact = new Contact("Swagger2 demo", "http://localhost", "邮箱");
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTFUL API")
                .description("集成Swagger2构建RESTful APIs")
                .termsOfServiceUrl("http://localhost")
                .version("1.0")
                .contact(contact)
                .build();
    }
}
