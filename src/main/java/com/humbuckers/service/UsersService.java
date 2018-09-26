package com.humbuckers.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humbuckers.entity.Users;
import com.humbuckers.repository.UsersRepository;

@Service
public class UsersService extends GenericService<Users, Long>{
	
	private UsersRepository usersRepository;
	
	@Autowired
    public UsersService(UsersRepository usersRepository) {
        super(usersRepository);
        this.usersRepository = usersRepository;
    }
	
	public List<Users> findAll(){
		return usersRepository.findAll();
	}
	
	public Users validateUser(String userName,String password){
		return usersRepository.validateUser(userName,password);
	}
}



