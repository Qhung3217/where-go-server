package com.wherego.wheregoserver.config;

import com.wherego.wheregoserver.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class ApplicationConfig {
//    @Autowired
//    @Qualifier("handlerExceptionResolver")
//    private HandlerExceptionResolver exceptionResolver;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//        return new JwtAuthenticationFilter(exceptionResolver);
//    }

}
