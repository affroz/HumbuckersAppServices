package com.humbuckers.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PROJECTS_WBS")
public class ProjectWbs {
	
	
	@Id
	@Column(name = "activity_id")
	private Long activityId;
	
	@Column(name="activity_name")
	private String activityName;
	
	@Column(name = "parent_key")
	private Long parentKey;
		
	@Column(name="activity_planned_start_date")
	private Date activityPlannedStartDate;
	
	@Column(name="activity_planned_end_date")
	private Date activityPlannedEndDate;
	
	@Column(name="activity_actual_start_date")
	private Date activityAcutalStartDate;
	
	@Column(name="activity_actual_end_date")
	private Date activityActualEndDate;
	
	@Column(name="REMARK")
	private String remark;	
	
	@Column(name="weightage")
	private String weightage;
	
	@Column(name="NO_OF_DAYS")
	private String noOfDays;
	
	@Column(name="PROJECT_KEY")
	private Long projectKey;
	
	
	@Column(name="activity_code")
	private Long activityCode;
}



