package com.humbuckers.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="USER_ROLE")
public class UserRole {
	
	@Id
	@Column(name = "USER_ROLE_ID")
	private Long roleId;
	
	@Column(name="ROLE_NAME")
	private String name;
		
}



