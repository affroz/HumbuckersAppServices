package com.humbuckers.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.humbuckers.SpringBootWebApplication;
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

	public void updateOtherParameters(Long projectid) {
		List<ProjectWbs>list=repository.findActivites(projectid);
		
		for (ProjectWbs projectWbs : list) {
			updateDateToParent(projectWbs,projectid);
		}
		
		List<ProjectWbs>mainWbs=repository.fetchWbsByParentByProject(projectid);
		for (ProjectWbs projectWbs : mainWbs) {
			String totalnoofDays=projectWbs.getNoOfDays();
			updateWeightage(totalnoofDays,projectWbs);
		}
		
		
		List<ProjectWbs>alllist=repository.findActivites(projectid);
		
		for (ProjectWbs projectWbs : alllist) {
			updatePlanAndActualPercent(projectWbs);
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
			
		if(parent.getParentKey()!=null) {
			updateDateToParent(parent,projectid);
		}
	}

	
	public void updateWeightage(String totalnoofDays, ProjectWbs parent) {
		
		List<ProjectWbs>childlist=repository.fetchWbsByParent(parent.getActivityId());
		if(childlist!=null && childlist.size()>0) {
			for (ProjectWbs child : childlist) {
				float weightage= ((Float.valueOf(child.getNoOfDays())* 100.0f)/(Float.valueOf(totalnoofDays)));
				child.setWeightage(Float.toString(weightage));
				if(child.getActivityCode()==null) {
					updateWeightage(totalnoofDays, child);
				}
			}
		}
	}
	
	
	public void updatePlanAndActualPercent(ProjectWbs entity) {
		Date todaysDate=new Date();
		if(entity.getActivityPlannedEndDate().before(todaysDate) || entity.getActivityPlannedEndDate().equals(todaysDate) ) {
			entity.setPlannedPercent("100");
			entity.setPlannedEarned(entity.getWeightage());
		}
		else if(entity.getActivityPlannedStartDate().after(todaysDate) ) {
			entity.setPlannedPercent("0");
			entity.setPlannedEarned("0");
		}
		else {
			Long diffdays=(entity.getActivityPlannedEndDate().getTime() - todaysDate.getTime()) / (1000 * 60 * 60 * 24);
			float plannedpercent= 100.0f-(Float.valueOf(diffdays)* 100.0f)/(Float.valueOf(entity.getNoOfDays()));
			entity.setPlannedPercent(Float.toString(plannedpercent));
			
			float plannedearned= (Float.valueOf(entity.getWeightage())*plannedpercent)/100.0f;
			entity.setPlannedEarned(Float.toString(plannedearned));
			
			repository.save(entity);
		}
	}

	public List<ProjectWbs> fetchWbsByParentByProject(Long project) {
		return repository.fetchWbsByParentByProject(project);
	}
	
	public List<ProjectWbs> fetchWbsByParent(Long parentId) {
		return repository.fetchWbsByParent(parentId);
	}

	public void deleteWbsByProject(Long projectid) {
		List<ProjectWbs>list=repository.findbyProjectKey(projectid);
		if(list!=null && list.size()>0) {
			for (ProjectWbs projectWbs : list) {
				repository.delete(projectWbs.getActivityId());
			}
		}
		
	}

	public List<ProjectWbs> fetchAllWbsByProject(Long projectid) {
		return repository.findbyProjectKey(projectid);
	}
	
	
	public static void main(String[] args) throws Exception {
		Date todaysDate=new Date();
		if(todaysDate.before(todaysDate)) {
			System.out.println("yes");
		}
	}
}



