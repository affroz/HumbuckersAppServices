package com.humbuckers.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humbuckers.entity.Project;
import com.humbuckers.repository.ProjectRepository;

@Service
public class ProjectService extends GenericService<Project, Long>{
	
	private ProjectRepository projectRepository;
	
	@Autowired
    public ProjectService(ProjectRepository projectRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
    }
	
	public List<Project> findAll(){
		return projectRepository.findAll();
	}
	
	
}



