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
        https.authorizeRequests().antMatchers("/registration")
                .permitAll().anyRequest().authenticated();

        https.formLogin().
                loginPage("/login").
                failureForwardUrl("/error").permitAll();

        https.logout().
                logoutSuccessUrl("/registration").
                permitAll()
        ;

        https.csrf()
                .disable();


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
