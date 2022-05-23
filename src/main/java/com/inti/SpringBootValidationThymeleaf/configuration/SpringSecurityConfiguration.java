package com.inti.SpringBootValidationThymeleaf.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.csrf() //IL FAUT CONFIGURER LE CSRF QUI EST ACTIF PAR DEFAUT
	        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	        .and().authorizeRequests()
	        .antMatchers("/hello", "/creerUtilisateur", "/saveUtilisateur").permitAll()
//			.antMatchers("/admin/**").hasRole("ADMIN") //équivalent à la ligne du dessous
//		//	.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//			.antMatchers("/helloUser").hasRole("USER")
			.anyRequest().authenticated() //toutes les autres requêtes sont accessibles si authentifiées 
										  //par défaut avec cette commande
			.and()
			.formLogin()		//page de login de base de Spring Security
//			.loginPage("/login")
//			.failureUrl("/login?error=true")
//			.defaultSuccessUrl("/hello")
			;
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(authProvider());
		
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		
			//UTILISATEURS EN MEMOIRE DE BASE! PAS BESOIN DE LES IMPLEMENTER DANS LA BASE
		
		auth.inMemoryAuthentication()
		.withUser("test").password(passwordEncoder().encode("test")).roles("USER")
		.and()
		.withUser("admin1").password(passwordEncoder().encode("admin1")).roles("ADMIN", "MANAGER");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
