package com.employee.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * http://localhost:8080/
 * http://localhost:8080/disable
 * http://localhost:8080/index
 * http://localhost:8080/open-tab
 * http://localhost:8080/reg
 */


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        String[][] mappings = {
                //  mapping     file name in `resources/templates` folder `.html`
                {"/"      , "login" },
                {"/registration"      , "registration" },
                {"/login"      , "login" }
        };

        for (String[] item: mappings) {
            registry.addViewController(item[0]).setViewName(item[1]);
        }
    }

}

