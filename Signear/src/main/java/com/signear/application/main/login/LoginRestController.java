package com.signear.application.main.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.signear.domain.usersign.UserSign;

@RestController
public class LoginRestController {
	@Autowired
	LoginService loginService;

	@PostMapping("/login/signuser")
	public UserSign LoginForCustomer(@RequestParam("email") String email, @RequestParam("password") String password)
			throws Exception {

		return loginService.loginSign(email, password);
	}

}
