package com.humbuckers.service;


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
	
	
}



