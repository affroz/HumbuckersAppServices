package com.humbuckers.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PROJECTS")
public class Project {
	
	@org.hibernate.annotations.GenericGenerator(name = "incrementGenerator", strategy = "org.hibernate.id.IncrementGenerator")
	@GeneratedValue(generator="incrementGenerator")
	@Id
	@Column(name = "project_id")
	private Long activityMainId;
	
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="project_client")
	private String projectClient;
	
	@Column(name="project_main_contractor")
	private String projectMainContractor;
	
	@Column(name="project_sub_contractor")
	private String projectSubContractor;
	
	@Column(name="project_mep_sub_contractor")
	private String projectMepSubContractor;
	
	
	@Column(name="project_interior_designer")
	private String projectInteriorDesigner;
	
	@Column(name="project_actual_end_date")
	private Date projectActualEndDate;
	
	
	@Column(name="project_planned_start_date")
	private Date projectPlannedStartDate;
	
	
	
	@Column(name="project_planned_end_date")
	private Date projectPlannedEndDate;
	
	
	@Column(name="project_actual_start_date")
	private Date projectAcutalStartDate;
	
}



