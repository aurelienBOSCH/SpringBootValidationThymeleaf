package com.inti.SpringBootValidationThymeleaf.service;

import java.util.List;

import com.inti.SpringBootValidationThymeleaf.model.Role;
import com.inti.SpringBootValidationThymeleaf.model.Utilisateur;



public interface UtilisateurService
{

	void save(Utilisateur a);
	
	void updateUtilisateur(Utilisateur a);

	public List<Utilisateur> getUtilisateurs();

	public Utilisateur getUtilisateur(int id);

	public void deleteUtilisateur(int id);
	
	Utilisateur findByUsername(String username);
	
}
