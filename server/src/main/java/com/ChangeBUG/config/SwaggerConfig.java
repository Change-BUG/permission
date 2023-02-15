package com.ChangeBUG.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 是否开启 swagger
    @Value("${swagger.enable}")
    private boolean enableSwagger;

    // 项目名称
    @Value("${project.name}")
    private String name;

    // 项目作者
    @Value("${project.author}")
    private String author;

    // 项目版本
    @Value("${project.version}")
    private String version;

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     */
    @Bean
    public Docket createRestApi01() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ChangeBUG.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("集合")
                .securitySchemes(securitySchemes());
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                .title(name + " 的接口文档")
                .description("这是 " + name + " 接口文档")
                .termsOfServiceUrl("127.0.0.1")
                .contact(new Contact(author, "", ""))
                .version(version)
                .build();
    }

    //  Authorization
    private List<ApiKey> securitySchemes() {
        // 设置 请求头信息
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("设置 请求头信息", "Authorization", "Header");
        result.add(apiKey);
        return result;
    }

}