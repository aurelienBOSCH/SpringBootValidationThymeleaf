package com.inti.SpringBootValidationThymeleaf.repository;

import java.util.List;

import com.inti.SpringBootValidationThymeleaf.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{

	@Query(value ="Select r.* from Role r, Utilisateur u, Utilisateur_Role ur WHERE r.id = ur.id_role AND u.id = ur.id_utilisateur AND u.username=:username", nativeQuery = true)
	//@Query(value = "select * from role where id in (select id_role from utilisateur_role where id_utilisateur in ( select id from utilisateur where username=:username))", nativeQuery = true)
	List<Role> findRolesByUser(@Param("username") String username);
	
	
}
