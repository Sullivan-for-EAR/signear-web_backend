package com.signear.application.main.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {
	@Autowired
	LoginService loginService;

	@RequestMapping("/login/users")
	public String LoginForCustomer(@RequestParam("email") String email, @RequestParam("password") String password) {
		return loginService.LoginUsers(email, password);
	}

}
