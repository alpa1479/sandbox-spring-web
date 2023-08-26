package edu.sandbox.spring.web.onlinelibrary.controller;

import edu.sandbox.spring.web.onlinelibrary.controller.parameters.PagingParameterRequestParamsResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(new PagingParameterRequestParamsResolver(true));
    }
}
