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

import com.humbuckers.entity.Activities;
import com.humbuckers.entity.ActivityWeightage;
import com.humbuckers.service.ActivityService;
import com.humbuckers.service.ActivityWeightageService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/activity"})
public class ActivityController {

    @Autowired
	private ActivityService activityService;
    
    @Autowired
    private ActivityWeightageService activityWeightageService;
	
	
	@GetMapping(path = {"/fetchactivity/{key}"})
	public Activities fetchActivity(@PathVariable("key") Long key){
		return activityService.findById(key);
	}
	
	@GetMapping(path = {"/fetchMainActivity"})
	public List<Activities> fetchMainActivity(){
		return activityService.findAllByType();
	}
	
	@GetMapping(path = {"/fetchAllActivity"})
	public List<Activities> fetchAllActivity(){
		return activityService.findAll();
	}
	
	
	
	@RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
	public Activities createEmployee(@RequestBody Activities act)
	{
		Activities updateact=activityService.save(act);
	    return updateact;
	}

	@GetMapping(path = {"/fetchAllActivityWeigth"})
	public List<ActivityWeightage> fetchAllActivityWeightage(){
		return activityWeightageService.findAll();
	}
	
}