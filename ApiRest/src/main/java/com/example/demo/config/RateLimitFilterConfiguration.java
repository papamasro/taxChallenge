package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.util.RateLimitFilter;

@Configuration
public class RateLimitFilterConfiguration {


        @Bean
        public FilterRegistrationBean<RateLimitFilter> rateLimitFilter() {
            FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
            registrationBean.setFilter(new RateLimitFilter());
            registrationBean.addUrlPatterns("/api/calculateTax"); // Ajusta los endpoints donde aplicar el filtro
            return registrationBean;
        }

}
