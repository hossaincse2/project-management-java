package com.project.management.projectmanagement;

import com.project.management.projectmanagement.common.Constants;
import com.project.management.projectmanagement.model.User;
import com.project.management.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        initDatabaseEntities();
    }

    @Bean
    private void initDatabaseEntities() {

        if( userService.getAllUsers().size() == 0) {
            userService.addNew(new User("Mr. Admin", "admin", "admin", Constants.ROLE_ADMIN));
            userService.addNew(new User("Mr. Manager", "manager", "manager", Constants.ROLE_MANAGER));
            userService.addNew(new User("Mr. Employee", "employee", "employee", Constants.ROLE_EMPLOYEE));
            userService.addNew(new User("Mr. Hossain", "hossain", "hossain", Constants.ROLE_EMPLOYEE));
            userService.addNew(new User("Mr. Rashed", "rashed", "rashed", Constants.ROLE_EMPLOYEE));
        }

    }
}