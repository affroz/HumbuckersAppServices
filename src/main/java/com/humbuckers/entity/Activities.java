package com.humbuckers.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="ACTIVITIES")
public class Activities {
	
	
	@Id
	@Column(name = "ACTIVITY_ID")
	private Long activityId;
	
	@Column(name="ACTIVITY_NAME")
	private String activityName;
	
	//0 for main having only child, 1 for having both parent and child,2 for having only parent
	@Column(name="ACTIVITY_TYPE")
	private String activityType;
			
	@Transient
	private List<Activities> activityChildList;
	/*@ManyToOne(optional = true)
	@JoinColumn(name="ACTIVITY_PARENT_ID",updatable = false,insertable = false)
	private Activities activityParentId;
	*/
		
	/*@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "ACTIVITIES", joinColumns = { @JoinColumn(name = "ACTIVITY_PARENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "ACTIVITY_ID") })
	private List<Activities> activityChildList;*/
	
}



