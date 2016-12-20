package com.codependent.googlesignin.security

import static org.springframework.http.HttpMethod.GET

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Configuration
	@Order(1)
	static class OAuth2SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
		private final String LOGIN_URL = "/googleLogin";
		
		@Autowired
		OAuth2ClientContextFilter oAuth2ClientContextFilter
		
		@Bean
		AuthenticationEntryPoint authenticationEntryPoint() {
			new LoginUrlAuthenticationEntryPoint(LOGIN_URL)
		}
		
		@Bean
		OpenIDConnectAuthenticationFilter openIdConnectAuthenticationFilter() {
			new OpenIDConnectAuthenticationFilter(LOGIN_URL)
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.antMatcher("/google*")
				.addFilterAfter(oAuth2ClientContextFilter, AbstractPreAuthenticatedProcessingFilter.class)
				.addFilterAfter(openIdConnectAuthenticationFilter(), OAuth2ClientContextFilter.class)
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
			.and()
				.authorizeRequests()
					.antMatchers(GET, "/googleOAuth2").authenticated()
		}
	}
	
	@Configuration
	@Order(2)
	static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.antMatchers("/images/**", "/", "/home").permitAll()
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/login")
						.permitAll()
					.defaultSuccessUrl("/secure-home")
					.and()
				.logout()
					.logoutSuccessUrl("/home")
		}
	}
}
