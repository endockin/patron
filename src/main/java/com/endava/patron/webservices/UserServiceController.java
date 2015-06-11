package com.endava.patron.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.endava.patron.dao.UserDAO;
import com.endava.patron.model.User;

@RestController
@RequestMapping("/service/user/")
public class UserServiceController {

	@Autowired
	private UserDAO userService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUser(@PathVariable int id) {
		User user = new User();
		user.setUserid(1);
		user.setFirstName("John");
		user.setLastName("Doe");
		return user;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public MappingJacksonValue loginUser(@RequestParam String username, @RequestParam String password, @RequestParam String callback) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user = userService.loginUser(user);
		MappingJacksonValue value = new MappingJacksonValue(user);
        value.setJsonpFunction(callback);
        return value;
	}
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public MappingJacksonValue getStatus(@RequestParam String callback) {
		MappingJacksonValue value = new MappingJacksonValue("User rest services are up and running!");
        value.setJsonpFunction(callback);
        return value;
	}

	public UserDAO getUserService() {
		return userService;
	}

	public void setUserService(UserDAO userService) {
		this.userService = userService;
	}
	
}
