package com.humbuckers.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humbuckers.entity.ProjectActivities;
import com.humbuckers.repository.ProjectActivitiesRepository;

@Service
public class ProjectActivitiesService extends GenericService<ProjectActivities, Long>{
	
	private ProjectActivitiesRepository projectActivitiesRepository;
	
	@Autowired
    public ProjectActivitiesService(ProjectActivitiesRepository projectActivitiesRepository) {
        super(projectActivitiesRepository);
        this.projectActivitiesRepository = projectActivitiesRepository;
    }
	
	public List<ProjectActivities> findAll(){
		return projectActivitiesRepository.findAll();
	}

	public List<ProjectActivities> findByProjectId(Long projectid) {
		return projectActivitiesRepository.findByProjectId(projectid);
	}

	public List<ProjectActivities> fetchMainActivitiesByProject(Long projectid) {
		return projectActivitiesRepository.fetchMainActivitiesByProject(projectid);
	}
	
	public List<ProjectActivities> fetchSubActivitiesByParent(Long projectid) {
		return projectActivitiesRepository.fetchSubActivitiesByParent(projectid);
	}

	public boolean checkexist(Long activityKey, Long projectKey) {
		return projectActivitiesRepository.checkexist(activityKey,projectKey) >0 ? true:false;
	}

	public void updateDates(Long projectKey, Long activityKey) {
		Date plannedStartDate=projectActivitiesRepository.getPlannedStartDate(projectKey,activityKey);
		Date plannedEndDate=projectActivitiesRepository.getPlannedEndDate(projectKey,activityKey);
		Date actualStartDate=projectActivitiesRepository.getActualStartDate(projectKey,activityKey);
		Date actualEndDate=projectActivitiesRepository.getActualEndDate(projectKey,activityKey);
		
		
		ProjectActivities act=projectActivitiesRepository.findParentActivity(projectKey,activityKey);
		act.setActivityPlannedStartDate(plannedStartDate);
		act.setActivityPlannedEndDate(plannedEndDate);
		act.setActivityAcutalStartDate(actualStartDate);
		act.setActivityActualEndDate(actualEndDate);
		projectActivitiesRepository.save(act);
		
		
	}
	
	
	
}



