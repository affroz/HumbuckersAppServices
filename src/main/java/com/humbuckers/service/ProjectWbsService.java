package com.humbuckers.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humbuckers.entity.ProjectWbs;
import com.humbuckers.repository.ProjectWbsRepository;

@Service
public class ProjectWbsService extends GenericService<ProjectWbs, Long>{
	
private ProjectWbsRepository repository;
	
	@Autowired
    public ProjectWbsService(ProjectWbsRepository repository) {
        super(repository);
        this.repository = repository;
    }

	public Long fetchMaxWbsId() {
		return repository.fetchMaxWbsId();
	}

	public void updateDates(Long projectid) {
		List<ProjectWbs>list=repository.findActivites(projectid);
		for (ProjectWbs projectWbs : list) {
			updateDateToParent(projectWbs,projectid);
		}
		
		List<ProjectWbs>weightlist=repository.findActivites(projectid);
		for (ProjectWbs projectWbs : weightlist) {
			updateWeightage(projectWbs);
		}
		
	}
	
	
	public void updateDateToParent(ProjectWbs child, Long projectid) {
		ProjectWbs parent=findById(child.getParentKey());
		parent.setActivityPlannedStartDate(repository.findplannedStartDate(child.getParentKey(),projectid));
		parent.setActivityPlannedEndDate(repository.findplannedEndDate(child.getParentKey(),projectid));
		parent.setActivityAcutalStartDate(repository.findactualStartDate(child.getParentKey(),projectid));
		parent.setActivityActualEndDate(repository.findactualEndDate(child.getParentKey(),projectid));
		Long days=(parent.getActivityPlannedEndDate().getTime() - parent.getActivityPlannedStartDate().getTime()) / (1000 * 60 * 60 * 24);
		parent.setNoOfDays(days.toString());
		save(parent);
		
		float weightage= ((Float.valueOf(child.getNoOfDays())* 100.0f)/(Float.valueOf(parent.getNoOfDays())));
		child.setWeightage(Float.toString(weightage));
		save(child);
		
		if(parent.getParentKey()!=null) {
			updateDateToParent(parent,projectid);
		}
	}

	
	public void updateWeightage(ProjectWbs child) {
		ProjectWbs parent=findById(child.getParentKey());
		float weightage= ((Float.valueOf(child.getNoOfDays())* 100.0f)/(Float.valueOf(parent.getNoOfDays())));
		child.setWeightage(Float.toString(weightage));
		save(child);
		if(parent.getParentKey()!=null) {
			updateWeightage(parent);
		}
	}
	
	
}



