package com.signear.application.main.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {
	@Autowired
	LoginService loginService;

	@PostMapping("/login/signuser")
	public String LoginForCustomer(@RequestParam("email") String email, @RequestParam("password") String password)
			throws Exception {

		return loginService.loginSign(email, password);
	}

}
