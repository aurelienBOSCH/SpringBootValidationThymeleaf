package com.inti.SpringBootValidationThymeleaf.service;

import java.util.List;

import com.inti.SpringBootValidationThymeleaf.model.Role;
import com.inti.SpringBootValidationThymeleaf.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServicempl implements RoleService
{
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> findRolesByUser(String username)
	{
		return roleRepository.findRolesByUser(username);
	}


}
