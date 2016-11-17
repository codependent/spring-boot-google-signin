package com.codependent.googlesignin.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/login")
	def home(){
		return "login"
	}
	
}
