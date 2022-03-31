package com.employee.app.config;

import com.employee.app.entities.Roles;
import com.employee.app.repos.RoleRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Log4j2
@Configuration
public class AutoRun {
    RoleRepo roleRepo;

    public AutoRun(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

//    @Bean
    public CommandLineRunner addRoles(){
        log.info("Roles are added");
        return args -> roleRepo.saveAll(Arrays.asList(
                new Roles("USER"),
                new Roles("ADMIN")
        ));
    }
}
