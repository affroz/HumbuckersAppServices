package com.humbuckers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.humbuckers.entity.Users;
import com.humbuckers.service.UsersService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/users"})
public class UsersController {

	@Autowired
	private UsersService userService; 
	
	@GetMapping(path = {"/fetchAllUsers"})
	public List<Users> fetchAllUsers(){
		return userService.findAll();
	}
	
	
	@GetMapping(path = {"/validateUser/{username}/{password}"})
	public  Users getUser(@PathVariable("username") String userName,@PathVariable("password") String password) {
		
		Users user=userService.validateUser(userName, password);
		
		return user!=null? user:new Users();
	}
	
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public Users createEmployee(@RequestBody Users user)
	{
		Users updateuser=userService.save(user);
	    return updateuser;
	}
	
}