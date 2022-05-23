package com.inti.SpringBootValidationThymeleaf.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table
@Data
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
public class Utilisateur
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Min(1) @Max(1500)  private int id;
	@NotEmpty(message = "saisir un nom")
	private @NonNull String nom;
	private String prenom;
	@Column(unique = true)
	private String username;
	private String email;
	@Size(min=4, message = "au moins 4 caractères")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$")
//	    This regex will enforce these rules:
	//
//	        At least one upper case English letter
//	        At least one lower case English letter
//	        At least one digit
//	        At least one special character
//	        Minimum eight in length
	private String mdp;
	private String telephone;
	
	@ManyToMany
	@JoinTable(name="Utilisateur_Role",
				joinColumns = @JoinColumn(name="id_utilisateur"),
				inverseJoinColumns = @JoinColumn(name="id_role"))
	List<Role> listeRoles;
}
