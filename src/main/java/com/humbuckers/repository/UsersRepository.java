/**
 * 
 */
package com.humbuckers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.humbuckers.entity.Users;


/**
 * @author Affroz Khan
 *
 * 
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query(value = "select user from Users user where user.userName =:user_name and user.password =:password")
	public Users validateUser(@Param("user_name")String userName,@Param("password")String password);
}
