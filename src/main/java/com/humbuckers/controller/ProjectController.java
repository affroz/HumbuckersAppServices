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

import com.humbuckers.entity.Project;
import com.humbuckers.entity.ProjectActivities;
import com.humbuckers.service.ProjectActivitiesService;
import com.humbuckers.service.ProjectService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/project"})
public class ProjectController {

	@Autowired
	private ProjectService projectService; 
	
	@Autowired
	private ProjectActivitiesService projectActivitiesService;
	
	@GetMapping(path = {"/fetchAllProjects"})
	public List<Project> fetchAllProjects(){
		return projectService.findAll();
	}
	
	
	@RequestMapping(value = "/saveProject", method = RequestMethod.POST)
	public Project createProject(@RequestBody Project project)
	{
		Project updatedproject=projectService.save(project);
	    return updatedproject;
	}
	
	
	@RequestMapping(value = "/saveProjectActivitiesList", method = RequestMethod.POST)
	public String saveProjectActivities(@RequestBody List<ProjectActivities> projectActivitiesList)
	{
		if(projectActivitiesList!=null && projectActivitiesList.size()>0) {
			for (ProjectActivities projectActivities : projectActivitiesList) {
				projectActivitiesService.save(projectActivities);
			}
		}
		return "";
		
		
	}
	
	@GetMapping(path = {"/fetchProjectById/{key}"})
	public Project fetchProjectById(@PathVariable("key") Long key){
		return projectService.findById(key);
	}
	
	
	@RequestMapping(value = "/fetchProjectActivityByProject/{projectid}", method = RequestMethod.POST)
	public List<ProjectActivities> fetchProjectActivity(@RequestBody Long projectid)
	{
		List<ProjectActivities> list=projectActivitiesService.findByProjectId(projectid);
	    return list;
	}
}