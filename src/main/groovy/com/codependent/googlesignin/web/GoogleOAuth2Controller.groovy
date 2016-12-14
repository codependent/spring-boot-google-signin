package com.codependent.googlesignin.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class GoogleOAuth2Controller {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/googleOAuth2")
	def googleOAuth2(){
		logger.info "googleOAuth2"
		return "redirect:secure-home"
	}
	
}
