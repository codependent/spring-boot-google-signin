package com.codependent.googlesignin.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping( value = ["/" , "/home"] )
	def home(){
		logger.info "home hola"
		return "home"
	}
	
	@GetMapping("/secure-home")
	void secureHome(){
		logger.info "secure-home hola"
	}
	
}
