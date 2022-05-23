package com.inti.SpringBootValidationThymeleaf.repository;

import com.inti.SpringBootValidationThymeleaf.model.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>
{

	Utilisateur findByUsername(String username);
}
