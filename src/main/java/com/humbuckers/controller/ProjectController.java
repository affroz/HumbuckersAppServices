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
import com.humbuckers.entity.ProjectWbs;
import com.humbuckers.service.ProjectActivitiesService;
import com.humbuckers.service.ProjectService;
import com.humbuckers.service.ProjectWbsService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/project"})
public class ProjectController {

	@Autowired
	private ProjectService projectService; 
	
	@Autowired
	private ProjectActivitiesService projectActivitiesService;
	
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
	
	
	@RequestMapping(value = "/saveProjectActivitiesList", method = RequestMethod.POST)
	public ProjectActivities saveProjectActivities(@RequestBody List<ProjectActivities> projectActivitiesList)
	{
		if(projectActivitiesList!=null && projectActivitiesList.size()>0) {
			for (ProjectActivities projectActivities : projectActivitiesList) {
				boolean exist=projectActivitiesService.checkexist(projectActivities.getActivityKey(),projectActivities.getProjectKey());
				if(!exist)
				projectActivitiesService.save(projectActivities);
			}
		}
		if(projectActivitiesList!=null && projectActivitiesList.size()>0) {
			for (ProjectActivities projectActivities : projectActivitiesList) {
				if(projectActivities.getActivityTypeCode()!=2) {
					projectActivitiesService.updateDates(projectActivities.getProjectKey(),projectActivities.getActivityKey());
				}
			}
		}
		
		if(projectActivitiesList!=null && projectActivitiesList.size()>0) {
			List<ProjectActivities> list=projectActivitiesService.findByProjectId(projectActivitiesList.get(0).getProjectKey());
			for (ProjectActivities projectActivities : list) {
				if(projectActivities.getActivityTypeCode()!=2) {
					List<ProjectActivities> childlist=projectActivitiesService.fetchActivitiesByProjectAndParent(projectActivities.getProjectKey(),projectActivities.getActivityKey());
				
				     if(childlist!=null && childlist.size()>0) {
				    	 for (ProjectActivities act : childlist) {
				    		 Long weight=((Long.valueOf(act.getNoOfDays()))*100)/(Long.valueOf(projectActivities.getNoOfDays()));
				    		 act.setWeightage(weight.toString());
				    		 projectActivitiesService.save(act);
						}
				     }
				
				}
			}
		}
		return new ProjectActivities();
		
		
	}
	
	@GetMapping(path = {"/fetchProjectById/{key}"})
	public Project fetchProjectById(@PathVariable("key") Long key){
		return projectService.findById(key);
	}
	
	
	@GetMapping(value = "/fetchProjectActivityByProject/{projectid}")
	public List<ProjectActivities> fetchProjectActivity(@PathVariable Long projectid)
	{
		List<ProjectActivities> list=projectActivitiesService.findByProjectId(projectid);
	    return list;
	}
	
	
	@GetMapping(value = "/deleteProjectActivityByProject/{projectid}")
	public ProjectActivities deleteProjectActivity(@PathVariable Long projectid)
	{
		List<ProjectActivities> list=projectActivitiesService.findByProjectId(projectid);
		if(list!=null && list.size()>0) {
			for (ProjectActivities projectActivities : list) {
				projectActivitiesService.delete(projectActivities);

			}
		}
		
	    return new ProjectActivities();
	}
	
	
	@GetMapping(value = "/fetchMainActivitiesByProject/{projectid}")
	public List<ProjectActivities> fetchMainActivitiesByProject(@PathVariable Long projectid)
	{
		List<ProjectActivities> list=projectActivitiesService.fetchMainActivitiesByProject(projectid);
	    return list;
	}
	
	
	@GetMapping(value = "/fetchSubActivitiesByParent/{parentId}")
	public List<ProjectActivities> fetchSubActivitiesByParent(@PathVariable Long parentId)
	{
		List<ProjectActivities> list=projectActivitiesService.fetchSubActivitiesByParent(parentId);
	    return list;
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
		
		projectWbsService.updateDates(projectid);
		
	    return new ProjectWbs();
	}
	
	@GetMapping(value = "/fetchMaxWbsId")
	public Long fetchMaxWbsId() {
		return projectWbsService.fetchMaxWbsId();
		
	}
}