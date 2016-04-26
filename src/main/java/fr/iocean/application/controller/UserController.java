package fr.iocean.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.model.User;
import fr.iocean.application.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;


@RestController
@RequestMapping("/api/users")
@Transactional
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid User user) {
		userService.save(user);
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public User findById(@PathVariable Long id) {
		User res = userService.findOne(id);
		
		if (res == null)
			throw new NotFoundException();
		
		return res;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User> findAll() {
		return userService.findAll();
	}
	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, @RequestBody @Valid User user) {
		user.setId(id);
		userService.save(user);
	}
	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
	
}




