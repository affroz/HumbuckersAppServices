package com.humbuckers.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humbuckers.entity.ActivitySubType;
import com.humbuckers.repository.ActivitySubTypeRepository;

@Service
public class ActivitySubTypeService extends GenericService<ActivitySubType, Long>{
	
private ActivitySubTypeRepository repository;
	
	@Autowired
    public ActivitySubTypeService(ActivitySubTypeRepository repository) {
        super(repository);
        this.repository = repository;
    }
	
	public List<ActivitySubType> findAll(){
		return repository.findAll();
	}
	
	
}



