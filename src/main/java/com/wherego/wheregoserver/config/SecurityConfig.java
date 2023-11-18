package com.wherego.wheregoserver.config;

import com.wherego.wheregoserver.constant.UserRole;
import com.wherego.wheregoserver.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
/*
    Use this way:

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(exceptionResolver);
    }

    Or (If use this way, REMOVE 2 lines above on ApplicationConfig):

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
*/
@Autowired
@Qualifier("handlerExceptionResolver")
private HandlerExceptionResolver exceptionResolver;
    @Autowired
    @Qualifier("delegatedAuthenticationEntryPoint")
    AuthenticationEntryPoint authEntryPoint;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(exceptionResolver);
    }
    private static final String[] WHITE_LIST_URL = {
            "/hotel/**",
            "/auth/**",
            "/place/**",
            "/restaurant/**",
            "/search",
            "/article",
            "/article/random",
    };
    private static final String[] TRAVELER_LIST_URL = {
            "/traveler/**",
    };
    private static final String[] WRITER_LIST_URL = {
            "/writer/**",
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers(TRAVELER_LIST_URL).hasRole(UserRole.TRAVELER)
                                .requestMatchers(WRITER_LIST_URL).hasRole(UserRole.WRITER)
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement( (sessionManagement)->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(authEntryPoint)
                )
                .addFilterBefore(jwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
