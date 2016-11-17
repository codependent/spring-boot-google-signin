package com.codependent.googlesignin.security

import static org.springframework.http.HttpMethod.GET

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final String LOGIN_URL = "/login";
	
	@Autowired
	OAuth2ClientContextFilter oAuth2ClientContextFilter
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint(LOGIN_URL)
	}

	@Bean
	public OpenIDConnectAuthenticationFilter openIdConnectAuthenticationFilter() {
		return new OpenIDConnectAuthenticationFilter(LOGIN_URL)
	}

	/*
	@Bean
	public OAuth2ClientContextFilter oAuth2ClientContextFilter() {
		return new OAuth2ClientContextFilter()
	}
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		AbstractPreAuthenticatedProcessingFilter.class
		http.addFilterAfter(oAuth2ClientContextFilter/*()*/, AbstractPreAuthenticatedProcessingFilter.class)
			.addFilterAfter(openIdConnectAuthenticationFilter(), OAuth2ClientContextFilter.class)
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
			.and().authorizeRequests()
			.antMatchers(GET, "/").permitAll()
			.antMatchers(GET, "/home").authenticated()
		
		/** I
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
		/*  II
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.and()
		.httpBasic()*/
	}
	
}
