package com.example.bookstore.config;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    /**
     * Настройки swagger-ui
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .tags(new Tag(ApiTags.AUTHORS, "Работа с авторами книг"),
                        new Tag(ApiTags.BASKET, "Работа с карзиной пользователя"),
                        new Tag(ApiTags.BOOKS, "Работа с книгами"),
                        new Tag(ApiTags.ORDERS, "Работа с заказами пользователя"),
                        new Tag(ApiTags.SECURITY, "Безопасность"))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.bookstore.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Информация о API
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Книжный магазин")
                .description("Книжный магазин, как пример приложения на стэке технологий Spring")
                .contact(new Contact("Борис Тарелкин", "http://www.csu.ru/Lists/List4/sotrudnik.aspx?ID=370", "tarelkinba@gmail.com"))
                .version("1.0.0")
                .build();
    }

    /**
     * материалы про CORS:
     * <link>https://ru.wikipedia.org/wiki/Cross-origin_resource_sharing</link>
     * <link>http://spring-projects.ru/understanding/cors/</link>
     */
    @Bean
    public GenericFilterBean swaggerCrossOriginFilter() {
        return new GenericFilterBean() {
            
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                HttpServletRequest httpReq = (HttpServletRequest) request;
                if (httpReq.getServletPath().startsWith("/v2/api-docs")) {
                    HttpServletResponse httpResp = (HttpServletResponse) response;
                    httpResp.setHeader("Access-Control-Allow-Origin", "*");
                    httpResp.setHeader("Access-Control-Allow-Credentials", "true");
                    httpResp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
                    httpResp.setHeader("Access-Control-Max-Age", "0");
                    httpResp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
                }
                chain.doFilter(request, response);
            }
        };
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "PUT", "DELETE", "POST")
            .allowedHeaders("Content-Type", "Accept", "Authorization", "Content-Disposition")
            .exposedHeaders("Content-Type", "Accept", "Authorization", "Content-Disposition")
            .allowCredentials(false).maxAge(3600);
    }

}
