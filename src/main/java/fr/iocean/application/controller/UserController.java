package fr.iocean.application.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;


@RestController
@RequestMapping("/api/login")
@Transactional
public class UserController {
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<GrantedAuthority> getCredentials() {
		org.springframework.security.core.userdetails.User userSpring = 
			(org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return userSpring.getAuthorities();
	}
	
	
}




