package com.inti.SpringBootValidationThymeleaf.service;

import java.util.List;

import com.inti.SpringBootValidationThymeleaf.model.Role;
import com.inti.SpringBootValidationThymeleaf.model.Utilisateur;
import com.inti.SpringBootValidationThymeleaf.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService
{

	@Autowired
	UtilisateurRepository ur;

	@Override
	public void save(Utilisateur a)
	{
		
		ur.save(a);
		
	}

	
	@Override
	public void updateUtilisateur(Utilisateur a)
	{

		ur.save(a);
		
	}

	@Override
	public List<Utilisateur> getUtilisateurs()
	{
		return ur.findAll();
	}

	@Override
	public Utilisateur getUtilisateur(int id)
	{
		return ur.getById(id);
	}

	@Override
	public void deleteUtilisateur(int id)
	{
		ur.deleteById(id);
		
	}



	@Override
	public Utilisateur findByUsername(String username)
	{
		
		return ur.findByUsername(username);
	}



}
