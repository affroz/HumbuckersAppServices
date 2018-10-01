package com.humbuckers.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ACTIVITY_KEY")
	private Activities activities;
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PROJECT_KEY")
	private Project project;
	
	
	
}



