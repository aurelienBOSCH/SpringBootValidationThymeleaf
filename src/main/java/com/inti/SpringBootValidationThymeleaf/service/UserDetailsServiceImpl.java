package com.inti.SpringBootValidationThymeleaf.service;

import java.util.ArrayList;
import java.util.List;

import com.inti.SpringBootValidationThymeleaf.model.Role;
import com.inti.SpringBootValidationThymeleaf.model.Utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{

		Utilisateur user = utilisateurService.findByUsername(username);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("Le username " + username + " n'existe pas en BDD.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getMdp(), getListeGrantedAuthority(user));
	
	}
	
	private List<GrantedAuthority> getListeGrantedAuthority(Utilisateur u)
	{
		List<GrantedAuthority> listeGrantedAuthorities = new ArrayList<GrantedAuthority>();
		
		List<Role> listeRoles = roleService.findRolesByUser(u.getUsername());
	
		for (Role role : listeRoles)
		{
			listeGrantedAuthorities.add(new SimpleGrantedAuthority(role.getNomRole()));
		}
		
		
		return listeGrantedAuthorities;
	}

}
