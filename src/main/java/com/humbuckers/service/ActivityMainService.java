package com.humbuckers.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humbuckers.entity.ActivityMain;
import com.humbuckers.repository.ActivityMainRepository;

@Service
public class ActivityMainService extends GenericService<ActivityMain, Long>{
	
	private ActivityMainRepository repository;
	
	@Autowired
    public ActivityMainService(ActivityMainRepository repository) {
        super(repository);
        this.repository = repository;
    }
	
	public List<ActivityMain> findAll(){
		return repository.findAll();
	}
	
	
}



