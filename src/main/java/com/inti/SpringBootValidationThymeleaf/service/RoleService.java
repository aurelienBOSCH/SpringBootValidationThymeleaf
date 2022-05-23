package com.inti.SpringBootValidationThymeleaf.service;

import java.util.List;

import com.inti.SpringBootValidationThymeleaf.model.Role;

import org.springframework.data.repository.query.Param;

public interface RoleService
{

	List<Role> findRolesByUser(@Param(" username") String username);
}
