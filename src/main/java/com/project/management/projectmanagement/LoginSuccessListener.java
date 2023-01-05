package com.project.management.projectmanagement;

import javax.servlet.http.HttpSession;

import com.project.management.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
        User user = (User) event.getAuthentication().getPrincipal();
        String displayName = userService.getByUsername( user.getUsername() ).getDisplayName();
        String userRole = userService.getByUsername( user.getUsername() ).getRole();
        String getUsername = userService.getByUsername( user.getUsername() ).getUsername();
        httpSession.setAttribute("loggedInUserName", displayName);
        httpSession.setAttribute("loggedInUserRole", userRole);
        httpSession.setAttribute("username", getUsername);
       // httpSession.setAttribute("loggedInUserRole", displayName);
	}
	
}