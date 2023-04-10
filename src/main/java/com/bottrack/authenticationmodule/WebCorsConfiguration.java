package com.bottrack.authenticationmodule;

import com.bottrack.model.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class WebCorsConfiguration  implements WebMvcConfigurer {

    @Autowired
    FileStorageProperties fileStorageProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resourcePath = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize().toString();
        System.out.println("Resource path:--------------: " + resourcePath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + resourcePath);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .exposedHeaders("Access-Control-Allow-Origin", "Authorization")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "HEAD", "OPTIONS")
                .allowCredentials(false);
    }

}
