package com.kaitait.springfox;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.inject.Inject;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private AdminAuthenticationProvider adminAuthenticationProvider;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.anonymous().and().authorizeRequests()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/swagger-ui.html",
                        "/webjars/**")
                .permitAll().and()
                .antMatcher("/foo*")
                .antMatcher("/bar*")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .authenticationProvider(adminAuthenticationProvider);
    }

}
