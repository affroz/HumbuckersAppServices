package com.humbuckers.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humbuckers.entity.Activities;
import com.humbuckers.repository.ActivityRepository;

@Service
public class ActivityService extends GenericService<Activities, Long>{
	
private ActivityRepository repository;
	
	@Autowired
    public ActivityService(ActivityRepository repository) {
        super(repository);
        this.repository = repository;
    }
	
	

	public Activities findByActivityName(String name) {
		Activities act=repository.findByActivityName(name);
		return act;
	}
	
	public List<Activities> findAllByType(){
		List<Activities> list=new ArrayList<>();
		List<Activities> allParent= repository.findAllByType("0");
		for (Activities activities : allParent) {
			List<Activities> actlist=fetchChildren(activities);
			activities.setActivityChildList(actlist);
			list.add(activities);
		}
		return list;
	}
	
	
	
	public List<Activities> fetchChildren(Activities activities){
			List<Activities> child=repository.findByParentKey(activities.getActivityId());
			if(child!=null && child.size()>0) {
				activities.setActivityChildList(child);
				for (Activities childentity : child) {
					if(!childentity.getActivityType().equals("2")) {
						List<Activities> childlist=fetchChildren(childentity);
						childentity.setActivityChildList(childlist);
					}
				}
			}
		
		return child;
	}
	
	
}



