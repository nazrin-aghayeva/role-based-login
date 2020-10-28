package com.employee.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.
               jdbcAuthentication().
                authoritiesByUsernameQuery("select role_id, role from roles_table WHERE role=?").
                usersByUsernameQuery("select email, password, active from user_table WHERE email=?").
                dataSource(dataSource).
                passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity https) throws Exception{
        https.authorizeRequests().
                antMatchers("/registration", "/h2-console/**").permitAll().
                antMatchers("/admin_page").hasRole("ADMIN").
                antMatchers("/user_page").hasRole("USER").
                anyRequest().authenticated();

        https.formLogin().
                loginPage("/login").
                defaultSuccessUrl("/user_page").
                usernameParameter("email").
                passwordParameter("password").
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
