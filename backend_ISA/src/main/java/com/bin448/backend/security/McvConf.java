package com.bin448.backend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("ALL")
@Configuration
public class McvConf extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/successfulRegisteration").setViewName("successfulRegisteration");
        registry.addViewController("/accountVerified").setViewName("accountVerified");
        registry.addViewController("/user").setViewName("user");


    }
}
