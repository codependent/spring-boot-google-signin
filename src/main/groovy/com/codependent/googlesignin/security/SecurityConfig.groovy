package com.codependent.googlesignin.security

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/home").hasRole("USER")
			.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login-error")
			.and()
			
			.openidLogin()
				.loginPage("/login")
				.permitAll()
				.authenticationUserDetailsService(new CustomUserDetailsService())
				.attributeExchange("https://www.google.com/.*")
					.attribute("email")
						.type("http://axschema.org/contact/email")
						.required(true)
						.and()
					.attribute("firstname")
						.type("http://axschema.org/namePerson/first")
						.required(true)
						.and()
					.attribute("lastname")
						.type("http://axschema.org/namePerson/last")
						.required(true)
		/*
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.and()
		.httpBasic()*/
	}
	
}
