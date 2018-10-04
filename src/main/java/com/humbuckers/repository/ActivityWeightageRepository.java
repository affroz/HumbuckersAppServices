/**
 * 
 */
package com.humbuckers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.humbuckers.entity.ActivityWeightage;
import com.humbuckers.entity.Users;


/**
 * @author Affroz Kha
 *
 * 
 */
public interface ActivityWeightageRepository extends JpaRepository<ActivityWeightage, Long>, JpaSpecificationExecutor<Users> {

	


	
	
}
