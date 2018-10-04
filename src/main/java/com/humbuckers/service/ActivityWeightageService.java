package com.humbuckers.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humbuckers.entity.ActivityWeightage;
import com.humbuckers.repository.ActivityWeightageRepository;

@Service
public class ActivityWeightageService extends GenericService<ActivityWeightage, Long>{
	
@SuppressWarnings("unused")
private ActivityWeightageRepository repository;
	
	@Autowired
    public ActivityWeightageService(ActivityWeightageRepository repository) {
        super(repository);
        this.repository = repository;
    }
	
	

	
	
}



