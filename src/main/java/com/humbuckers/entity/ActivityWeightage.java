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
@Table(name="activities_weightage")
public class ActivityWeightage {
	
	@Id
	@SequenceGenerator(name="seq", sequenceName="activities_weightage_activities_weightage_id_seq",allocationSize=50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "activities_weightage_id")
	private Long activitiesWeightageId;
	
	@Column(name="weightage_percentage")
	private String weightagePercentage;
	
	@Column(name="activity_id")
	private Long activityId;
	
	
	
}



