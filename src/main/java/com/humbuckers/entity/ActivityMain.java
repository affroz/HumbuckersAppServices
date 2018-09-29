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
@Table(name="ACTIVITY_MAIN")
public class ActivityMain {
	
	@Id
	@Column(name = "ACTIVITY_MAIN_ID")
	private Long activityMainId;
	
	@Column(name="ACTIVITY_MAIN_NAME")
	private String activityMainName;
	

}



