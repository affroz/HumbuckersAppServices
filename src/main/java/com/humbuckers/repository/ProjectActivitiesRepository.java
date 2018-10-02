/**
 * 
 */
package com.humbuckers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
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

	
	
}
