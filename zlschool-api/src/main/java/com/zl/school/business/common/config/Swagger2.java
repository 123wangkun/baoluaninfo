package com.zl.school.business.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置swagger  测试工具
 * Created by zhangsl.
 */
@EnableSwagger2
@Configuration
public class Swagger2 extends WebMvcConfigurationSupport {
    @Bean
    public Docket systemApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/system/**")).build().groupName("系统管理")
                .apiInfo(apiInfo("系统管理", "系统管理", "1.0"));
    }
    @Bean
    public Docket courseApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/course/**")).build().groupName("课程管理")
                .apiInfo(apiInfo("课程管理", "课程管理", "1.0"));
    }
    @Bean
    public Docket labelApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/label/**")).build().groupName("标签管理")
                .apiInfo(apiInfo("标签管理", "标签管理", "1.0"));
    }

    @Bean
    public Docket certificateApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/certificate/**")).build().groupName("证书管理")
                .apiInfo(apiInfo("证书管理", "证书管理", "1.0"));
    }

    @Bean
    public Docket checkApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/check/**")).build().groupName("考核管理")
                .apiInfo(apiInfo("考核管理", "考核管理", "1.0"));
    }

    @Bean
    public Docket messageApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/message/**")).build().groupName("发布管理")
                .apiInfo(apiInfo("发布管理", "发布管理", "1.0"));
    }

    @Bean
    public Docket examApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/exam/**")).build().groupName("考试管理")
                .apiInfo(apiInfo("考试管理", "考试管理", "1.0"));
    }

    @Bean
    public Docket trainApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/train/**")).build().groupName("培训管理")
                .apiInfo(apiInfo("培训管理", "培训管理", "1.0"));
    }

    private ApiInfo apiInfo(String name, String description, String version) {
        ApiInfo apiInfo = new ApiInfoBuilder().title(name).description(description).version(version).build();
        return apiInfo;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
