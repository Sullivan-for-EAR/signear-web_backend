package com.signear.application.main.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signear.application.main.login.exception.LoginException;
import com.signear.domain.usercustomer.UserCustomer;
import com.signear.domain.usercustomer.UserCustomerRepository;
import com.signear.domain.users.UsersRepositiory;
import com.signear.domain.usersign.UserSign;
import com.signear.domain.usersign.UserSignRepository;

@Service
public class LoginService {
	@Autowired
	UsersRepositiory usersRepositiory;
	@Autowired
	UserCustomerRepository userCustomerRepository;
	@Autowired
	UserSignRepository userSignRepository;

	public String LoginCustomers(String email, String password) {
		UserCustomer userCustomer = userCustomerRepository.findByEmail(email);
		if (password.equals(userCustomer.getPassword())) {
			return email;
		} else {
			new LoginException();
			return "";
		}
	}

	public String LoginSign(String email, String password) {
		UserSign userSign = userSignRepository.findByEmail(email);
		if (password.equals(userSign.getPassword())) {
			return email;
		} else {
			new LoginException();
			return "";
		}
	}
}
