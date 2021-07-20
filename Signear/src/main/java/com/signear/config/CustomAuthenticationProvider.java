package com.signear.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.signear.application.main.login.LoginService;
import com.signear.domain.usercustomer.UserCustomer;
import com.signear.domain.usercustomer.UserCustomerRepository;
import com.signear.domain.users.UsersRepositiory;
import com.signear.domain.usersign.UserSign;
import com.signear.domain.usersign.UserSignRepository;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	UsersRepositiory usersRepositiory;
	@Autowired
	UserCustomerRepository userCustomerRepository;
	@Autowired
	UserSignRepository userSignRepository;
	@Autowired
	LoginService loginService;

	public CustomAuthenticationProvider(BCryptPasswordEncoder passwordEncoder) {
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String email = token.getName();
		String userPw = (String) token.getCredentials(); // UserDetailsService를 통해 DB에서 아이디로 사용자 조회
		UserCustomer userCustomer;
		UserSign userSign;
		if (email.equals(loginService.LoginCustomers(email, userPw))) {
			// 권한 조회
			// login 한 userModel
			userCustomer = userCustomerRepository.findByEmail(email);
			userSign = userSignRepository.findByEmail(email);
		} else {
			throw new BadCredentialsException("로그인에 실패했습니다.");
		}
		if (userSign != null && userSign.getEmail().equals(email)) {
			return new UsernamePasswordAuthenticationToken(userSign, userPw);
		} else {
			return new UsernamePasswordAuthenticationToken(userCustomer, userPw);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
