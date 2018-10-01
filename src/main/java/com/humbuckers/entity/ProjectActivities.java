package com.humbuckers.entity;


import java.util.Date;

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
@Table(name="PROJECT_ACTIVITIES")
public class ProjectActivities {
	
	
	
	@Id
	@SequenceGenerator(name="seq", sequenceName="project_activities_project_activity_id_seq",allocationSize=50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "PROJECT_ACTIVITY_ID")
	private Long projectActivityId;
	
	@Column(name="ACTIVITY_KEY")
	private Long activityKey;
	
	@Column(name="PROJECT_KEY")
	private Long projectKey;
	
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
}



