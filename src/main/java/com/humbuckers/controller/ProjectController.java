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
import com.humbuckers.entity.ProjectWbs;
import com.humbuckers.service.ProjectService;
import com.humbuckers.service.ProjectWbsService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/project"})
public class ProjectController {

	@Autowired
	private ProjectService projectService; 
	
	@Autowired
	private ProjectWbsService projectWbsService;
	
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
	
		
	
	@GetMapping(path = {"/fetchProjectById/{key}"})
	public Project fetchProjectById(@PathVariable("key") Long key){
		return projectService.findById(key);
	}
	
	
	
	
	@RequestMapping(value = "/saveProjectWbsList/{projectid}", method = RequestMethod.POST)
	public ProjectWbs createProject(@RequestBody List<ProjectWbs> project,@PathVariable Long projectid)
	{
		if(project!=null && project.size()>0) {
			for (ProjectWbs projectWbs : project) {
				if(null!=projectWbs.getActivityPlannedEndDate() && null!=projectWbs.getActivityPlannedStartDate()) {
					Long days=(projectWbs.getActivityPlannedEndDate().getTime() - projectWbs.getActivityPlannedStartDate().getTime()) / (1000 * 60 * 60 * 24);
					projectWbs.setNoOfDays(days.toString());
				}
				projectWbsService.save(projectWbs);
			}
		}
		
		projectWbsService.updateOtherParameters(projectid);
		
	    return new ProjectWbs();
	}
	
	@GetMapping(value = "/fetchMaxWbsId")
	public Long fetchMaxWbsId() {
		return projectWbsService.fetchMaxWbsId();
		
	}
	
	
	
	@GetMapping(value = "/fetchWbsByParentByProject/{project}")
	public List<ProjectWbs> fetchWbsByParentByProject(@PathVariable Long project)
	{
		List<ProjectWbs> list=projectWbsService.fetchWbsByParentByProject(project);
	    return list;
	}
	
	
	
	@GetMapping(value = "/fetchWbsByParent/{parentId}")
	public List<ProjectWbs> fetchWbsByParent(@PathVariable Long parentId)
	{
		List<ProjectWbs> list=projectWbsService.fetchWbsByParent(parentId);
	    return list;
	}
	
	
	@GetMapping(value = "/deleteWbsByProject/{projectid}")
	public ProjectWbs deleteWbsByProject(@PathVariable Long projectid)
	{
		projectWbsService.deleteWbsByProject(projectid);
	    return new ProjectWbs();
	}
	
	@GetMapping(value = "/fetchAllWbsByProject/{projectid}")
	public List<ProjectWbs> fetchAllWbsByProject(@PathVariable Long parentId)
	{
		List<ProjectWbs> list=projectWbsService.fetchAllWbsByProject(parentId);
	    return list;
	}
	
	
}