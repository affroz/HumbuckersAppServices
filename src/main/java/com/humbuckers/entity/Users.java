package com.humbuckers.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="USERS")
public class Users {
	
	@Id
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="FULL_NAME")
	private String FullName;
	
	@Column(name="PASSWORD")
	private String password;
	

    @ManyToOne(optional = true)
    @JoinColumn(name="ROLE_KEY",updatable = false,insertable = false)
    private UserRole userRole;
}



