package com.github.binarywang.demo.wx.mp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.DocumentType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket crateRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.github.binarywang.demo.wx.mp.controller"))
            .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("使用swagger构建的微信公众号的api文档")
            .description("简单优雅的restful风格,https://github.com/yueyue10/SpringBootLearning/tree/master/springboot-swagger，测试网页权限等使用微信开发者工具请求：http://58g0b94ec.nat123.cc/demo/index.html")
            .termsOfServiceUrl("https://github.com/yueyue10/SpringBootLearning/tree/master/springboot-swagger")
            .version("1.0")
            .build();
    }
}
