package com.humbuckers.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PROJECTS")
public class Project {
	
	
	
	@Id
	@SequenceGenerator(name="seq", sequenceName="projects_project_id_seq",allocationSize=50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "project_id")
	private Long projectId;
	
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



