package com.humbuckers.service;


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
		return repository.findAllByType("0");
	}
	
}



