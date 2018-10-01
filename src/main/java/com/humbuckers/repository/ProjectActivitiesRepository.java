/**
 * 
 */
package com.humbuckers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.humbuckers.entity.ProjectActivities;
import com.humbuckers.entity.Users;


/**
 * @author Affroz Khan
 *
 * 
 */
public interface ProjectActivitiesRepository extends JpaRepository<ProjectActivities, Long>, JpaSpecificationExecutor<Users> {

}
