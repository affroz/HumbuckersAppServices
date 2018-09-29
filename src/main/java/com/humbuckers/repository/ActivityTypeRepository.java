/**
 * 
 */
package com.humbuckers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.humbuckers.entity.ActivitySubType;
import com.humbuckers.entity.ActivityType;
import com.humbuckers.entity.Project;
import com.humbuckers.entity.Users;


/**
 * @author Affroz Khan
 *
 * 
 */
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long>, JpaSpecificationExecutor<Users> {

	@Query(value = "select act from ActivityType act where act.activityMain.activityMainId =:id")
	public List<ActivityType> findTypeByMainActivity(@Param("id")Long id);


}
