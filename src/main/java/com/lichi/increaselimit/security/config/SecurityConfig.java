package com.lichi.increaselimit.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * sercurityCofnig
 * @author majie
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/v2/**","/swagger**", "/druid/**","/swagger-resources/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .csrf().disable();
    }
}
