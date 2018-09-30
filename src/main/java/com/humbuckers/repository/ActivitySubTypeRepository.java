/**
 * 
 */
package com.humbuckers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.humbuckers.entity.ActivitySubType;
import com.humbuckers.entity.Users;


/**
 * @author Affroz Khan
 *
 * 
 */
public interface ActivitySubTypeRepository extends JpaRepository<ActivitySubType, Long>, JpaSpecificationExecutor<Users> {

}
