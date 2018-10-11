package com.humbuckers.service;


import java.util.Date;
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
		
		List<ProjectWbs>mainList=repository.fetchWbsByParentByProject(projectid);
		for (ProjectWbs projectWbs : mainList) {
			projectWbs.setPlannedEarned(updatemainPlanPercentage(projectWbs.getActivityId()));
			save(projectWbs);
		}
		
	}
	
	
	public void updateDateToParent(ProjectWbs child, Long projectid) {
		ProjectWbs parent=findById(child.getParentKey());
		parent.setActivityPlannedStartDate(repository.findplannedStartDate(child.getParentKey(),projectid));
		parent.setActivityPlannedEndDate(repository.findplannedEndDate(child.getParentKey(),projectid));
		parent.setActivityAcutalStartDate(repository.findactualStartDate(child.getParentKey(),projectid));
		parent.setActivityActualEndDate(repository.findactualEndDate(child.getParentKey(),projectid));
		parent.setNoOfDays(updateNumberofDays(parent.getActivityId()));
		save(parent);
			
		if(parent.getParentKey()!=null) {
			updateDateToParent(parent,projectid);
		}
	}

	
	public String updateNumberofDays(Long id) {
		String totalNodays=null;
		List<ProjectWbs>activitiesList= repository.findByMainParent(id);
		int i=0;
		if(activitiesList!=null && activitiesList.size()>0) {
			for (ProjectWbs entity : activitiesList) {
				i = i+Integer.parseInt(entity.getNoOfDays());
			}
			
			totalNodays=String.valueOf(i);
		}
		return totalNodays;
	}
	
	public void updateWeightage(String totalnoofDays, ProjectWbs parent) {
		
		List<ProjectWbs>childlist=repository.findByMainParent(parent.getActivityId());
		if(childlist!=null && childlist.size()>0) {
			for (ProjectWbs child : childlist) {
				
				float weightage= ((Float.valueOf(child.getNoOfDays())* 100.0f)/(Float.valueOf(totalnoofDays)));
				child.setWeightage(Float.toString(weightage));
				repository.save(child);
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
			
		}
		repository.save(entity);
	}

	
	public String updatemainPlanPercentage(Long id) {
		String plannedearned=null;
		List<ProjectWbs>activitiesList= repository.findByMainParent(id);
		float i=0.0f;
		if(activitiesList!=null && activitiesList.size()>0) {
			for (ProjectWbs entity : activitiesList) {
				i = i+Float.parseFloat(entity.getPlannedEarned());
			}
			
			plannedearned=String.valueOf(i);
		}
		return plannedearned;
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
	
}



