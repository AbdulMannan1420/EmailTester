package nl.hu.fronttester;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
    public class WebMvcConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*"); // Allow requests from any origin
//                    .allowedMethods("*") // Allow all HTTP methods
//                    .allowedHeaders("*"); // Allow all headers
        }

}

