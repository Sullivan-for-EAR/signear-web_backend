package com.signear.application.main.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signear.config.SecurityConfig;
import com.signear.domain.usercustomer.UserCustomerRepository;
import com.signear.domain.users.UsersRepositiory;
import com.signear.domain.usersign.UserSignRepository;

@Service
public class LoginService {
	@Autowired
	UsersRepositiory usersRepositiory;
	@Autowired
	UserCustomerRepository userCustomerRepository;
	@Autowired
	UserSignRepository userSignRepository;
	@Autowired
	SecurityConfig securityConfig;

	public String loginSign(String email, String password) {
		userSignRepository.findByEmail(email);
		return email;
//		if (userSign != null && securityConfig.passwordEncoder().matches(password, userSign.getPassword())) {
//		return email;
//		} else {
//			throw new LoginException();
//		}
	}
}
