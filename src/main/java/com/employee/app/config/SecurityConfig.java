package com.employee.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity https) throws Exception{
        https.authorizeRequests().antMatchers("/registration", "/h2-console/**")
                .permitAll().anyRequest().authenticated();

        https.formLogin().
                loginPage("/login").
                failureForwardUrl("/error").permitAll();

        https
                .headers().frameOptions().disable();

        https
                .csrf().ignoringAntMatchers("/h2-console/**");

        https.csrf().disable();

        https.logout().
                logoutSuccessUrl("/registration").
                permitAll();

        https.rememberMe();
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(
                "/resources/**",
                "/static/**",
                "/css/**",
                "/images/**",
                "/fonts/**",
                "/js/**");
    }
}
