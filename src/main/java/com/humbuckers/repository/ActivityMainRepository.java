/**
 * 
 */
package com.humbuckers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.humbuckers.entity.ActivityMain;
import com.humbuckers.entity.Users;


/**
 * @author Affroz Khan
 *
 * 
 */
public interface ActivityMainRepository extends JpaRepository<ActivityMain, Long>, JpaSpecificationExecutor<Users> {

}
