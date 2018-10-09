package com.humbuckers.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="ACTIVITIES")
public class Activities {
	
	@Id
	@SequenceGenerator(name="seq", sequenceName="activities_activity_id_seq",allocationSize=50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "ACTIVITY_ID")
	private Long activityId;
	
	@Column(name="ACTIVITY_NAME")
	private String activityName;
	
	@Column(name="ACTIVITY_TYPE")
	private String activityType;
			
			
}



