package com.signear.application.main.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signear.domain.usercustomer.UserCustomerRepository;
import com.signear.domain.users.UsersRepositiory;

@Service
public class LoginService {
	@Autowired
	UsersRepositiory usersRepositiory;
	@Autowired
	UserCustomerRepository userCustomerRepository;

	public String LoginUsers(String email, String password) {
		return "";
	}
}
