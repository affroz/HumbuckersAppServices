package com.humbuckers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humbuckers.entity.Activities;
import com.humbuckers.service.ActivityService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/activity"})
public class ActivityController {

@Autowired
	private ActivityService activityService;
	
	
	@GetMapping(path = {"/fetchactivity/{activity}"})
	public Activities fetchActivity(@PathVariable("activity") String name){
		return activityService.findByActivityName(name);
	}
	
	@GetMapping(path = {"/fetchAllActivity"})
	public List<Activities> fetchAllActivity(){
		return activityService.findAllByType();
	}
	
	
	
}