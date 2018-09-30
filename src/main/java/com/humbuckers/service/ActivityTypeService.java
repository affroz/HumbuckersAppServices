package com.humbuckers.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humbuckers.entity.ActivityType;
import com.humbuckers.repository.ActivityTypeRepository;

@Service
public class ActivityTypeService extends GenericService<ActivityType, Long>{
	
private ActivityTypeRepository repository;
	
	@Autowired
    public ActivityTypeService(ActivityTypeRepository repository) {
        super(repository);
        this.repository = repository;
    }
	
	public List<ActivityType> findAll(){
		return repository.findAll();
	}

	public List<ActivityType> findTypeByMainActivity(Long id) {
		return repository.findTypeByMainActivity(id);
	}
	
	
	
}



