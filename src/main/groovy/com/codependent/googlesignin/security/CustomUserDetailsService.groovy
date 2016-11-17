package com.codependent.googlesignin.security

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.openid.OpenIDAuthenticationToken;

class CustomUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
	
	UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
		new User(token.name, "", AuthorityUtils.createAuthorityList("ROLE_USER"))
	}
}