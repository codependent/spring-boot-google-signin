package com.codependent.googlesignin.web

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HomeController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/home")
	void home(){
		logger.info "hola"
	}
	
}
