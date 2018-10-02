/**
 * 
 */
package com.humbuckers.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.humbuckers.entity.ProjectActivities;
import com.humbuckers.entity.Users;


/**
 * @author Affroz Khan
 *
 * 
 */
public interface ProjectActivitiesRepository extends JpaRepository<ProjectActivities, Long>, JpaSpecificationExecutor<Users> {

	@Query(value = "select * from PROJECT_ACTIVITIES where PROJECT_KEY =:key ",nativeQuery=true)
	public List<ProjectActivities> findByProjectId(@Param("key")Long key);

	@Query(value = "select * from PROJECT_ACTIVITIES where PROJECT_KEY =:key and ACTIVTY_TYPE_CODE=0",nativeQuery=true)
	public List<ProjectActivities> fetchMainActivitiesByProject(@Param("key")Long key);

	@Query(value = "select * from PROJECT_ACTIVITIES where PARENT_ACTIVTY_KEY =:key",nativeQuery=true)
	public List<ProjectActivities> fetchSubActivitiesByParent(@Param("key")Long projectid);

	@Query(value = "select count(*) from PROJECT_ACTIVITIES where PROJECT_KEY =:projectKey and ACTIVITY_KEY=:activityKey",nativeQuery=true)
	public int checkexist(@Param("activityKey")Long activityKey,@Param("projectKey") Long projectKey);

	@Query(value = "select MIN(activity_planned_start_date) from project_activities where project_key=:projectKey and parent_activty_key =:parentActvityKey",nativeQuery=true)
	public Date getPlannedStartDate(@Param("projectKey")Long projectKey,@Param("parentActvityKey") Long parentActvityKey);

	
	@Query(value = "select MAX(activity_planned_end_date) from project_activities where project_key=:projectKey and parent_activty_key =:parentActvityKey",nativeQuery=true)
	public Date getPlannedEndDate(@Param("projectKey")Long projectKey,@Param("parentActvityKey") Long parentActvityKey);

	
	@Query(value = "select MIN(activity_actual_start_date) from project_activities where project_key=:projectKey and parent_activty_key =:parentActvityKey",nativeQuery=true)
	public Date getActualStartDate(@Param("projectKey")Long projectKey,@Param("parentActvityKey") Long parentActvityKey);

	
	@Query(value = "select MAX(activity_actual_end_date) from project_activities where project_key=:projectKey and parent_activty_key =:parentActvityKey",nativeQuery=true)
	public Date getActualEndDate(@Param("projectKey")Long projectKey,@Param("parentActvityKey") Long parentActvityKey);

	
	
	
	
	
	
	
	
	
	
	
	
	
	@Query(value = "select * from project_activities where project_key=:projectKey and ACTIVITY_KEY =:activityKey",nativeQuery=true)
	public ProjectActivities findParentActivity(@Param("projectKey")Long projectKey, @Param("activityKey")Long activityKey);

	
}
