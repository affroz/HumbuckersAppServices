/**
 * 
 */
package com.humbuckers.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.humbuckers.entity.ProjectWbs;


/**
 * @author Affroz Khan
 *
 * 
 */
public interface ProjectWbsRepository extends JpaRepository<ProjectWbs, Long> {

	@Query(value = "select nextval('projects_wbs_activity_id_seq')",nativeQuery=true)
	public Long fetchMaxWbsId();
	
	@Query(value = "select * from projects_wbs where PROJECT_KEY=:projectkey and activity_code=1 ",nativeQuery=true)
	public List<ProjectWbs> findActivites(@Param("projectkey") Long projectkey);
	
	@Query(value = "select MIN(activity_planned_start_date)  from  projects_wbs where PROJECT_KEY=:projectkey and parent_key=:parentKey",nativeQuery=true)
	public Date findplannedStartDate(@Param("parentKey") Long parentKey,@Param("projectkey") Long projectkey);


	@Query(value = "select MAX(activity_planned_end_date)  from  projects_wbs where PROJECT_KEY=:projectkey and parent_key=:parentKey",nativeQuery=true)
	public Date findplannedEndDate(@Param("parentKey") Long parentKey,@Param("projectkey") Long projectkey);

	@Query(value = "select MIN(activity_actual_start_date)  from  projects_wbs where PROJECT_KEY=:projectkey and parent_key=:parentKey",nativeQuery=true)
	public Date findactualStartDate(@Param("parentKey") Long parentKey,@Param("projectkey") Long projectkey);

	
	@Query(value = "select MAX(activity_actual_end_date)  from  projects_wbs where PROJECT_KEY=:projectkey and parent_key=:parentKey",nativeQuery=true)
	public Date findactualEndDate(@Param("parentKey") Long parentKey,@Param("projectkey") Long projectkey);

	@Query(value = "select * from projects_wbs where parent_key=:parentId ",nativeQuery=true)
	public List<ProjectWbs> fetchWbsByParent(@Param("parentId") Long parentId);
	
	
	@Query(value = "select * from projects_wbs where PROJECT_KEY=:project and parent_key is null",nativeQuery=true)
	public List<ProjectWbs> fetchWbsByParentByProject(@Param("project") Long project);

	@Query(value = "select * from projects_wbs where PROJECT_KEY=:project",nativeQuery=true)
	public List<ProjectWbs> findbyProjectKey(@Param("project") Long project);
	
	
}
