/**
 * 
 */
package com.humbuckers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.humbuckers.entity.Activities;
import com.humbuckers.entity.Users;


/**
 * @author Affroz Khan
 *
 * 
 */
public interface ActivityRepository extends JpaRepository<Activities, Long>, JpaSpecificationExecutor<Users> {

	


	@Query(value = "select act from Activities act where act.activityName =:name")
	public Activities findByActivityName(@Param("name")String name);

	@Query(value = "select act from Activities act where act.activityType =:type ")
	public List<Activities> findAllByType(@Param("type")String type);
	
	
	@Query(value = "select * from ACTIVITIES where ACTIVITY_PARENT_ID =:key ",nativeQuery=true)
	public List<Activities> findByParentKey(@Param("key")Long key);
	
	@Query(value = "select max(ACTIVITY_ID)+1 from ACTIVITIES",nativeQuery=true)
	public Long generateActivityKey();
	
	
}
