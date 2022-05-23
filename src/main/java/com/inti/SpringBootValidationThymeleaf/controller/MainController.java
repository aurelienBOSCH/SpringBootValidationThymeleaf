package com.inti.SpringBootValidationThymeleaf.controller;

import javax.validation.Valid;

import com.inti.SpringBootValidationThymeleaf.model.Utilisateur;
import com.inti.SpringBootValidationThymeleaf.repository.RoleRepository;
import com.inti.SpringBootValidationThymeleaf.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController
{
	@Autowired
	UtilisateurRepository ur;
	
	@Autowired
	RoleRepository rr; 
	
	@GetMapping("/hello")
	public String hello()
	{
		return "hello";
	}
	
	@GetMapping("/creerUtilisateur")
	public String creerUtilisateur(Utilisateur utilisateur)
	{
		return "creerUtilisateur";
	}
	
//	@PostMapping("/saveUtilisateur")
//	public String saveUtilisateur(Utilisateur utilisateur)
//	{
//		ur.save(utilisateur);
//		return "redirect:/listeUtilisateur";
//	}
	
	@GetMapping("/listeUtilisateur")
	public String listeUtilisateur()
	{
		return "listeUtilisateur";
	}
	
	 @PostMapping("/saveUtilisateur")
	    public String saveUser(@Valid Utilisateur utilisateur, BindingResult result, Model model)
	    {
	        if (result.hasErrors()) {
	            return "saveUtilisateur";
	        }
	        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
	        utilisateur.setMdp(b.encode(utilisateur.getMdp()));
	        ur.save(utilisateur);
	        //return "redirect:/listeUtilisateur";
	        return "hello";
	    }
}
