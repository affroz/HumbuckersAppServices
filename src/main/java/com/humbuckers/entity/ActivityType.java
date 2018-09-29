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
@Table(name="ACTIVITY_TYPE")
public class ActivityType {
	
	@Id
	@Column(name = "ACTIVITY_TYPE_ID")
	private Long activityTypeId;
	
	@Column(name="ACTIVITY_TYPE_NAME")
	private String activityTypeName;

    @ManyToOne(optional = true)
    @JoinColumn(name="ACTIVITY_MAIN_KEY",updatable = false,insertable = false)
    private ActivityMain activityMain;
}



