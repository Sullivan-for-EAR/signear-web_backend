package com.signear.application.main.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.signear.application.exception.ApiException;
import com.signear.application.exception.ExceptionEnum;
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
	@Autowired
	PasswordEncoder passwordEncoder;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 로그인
	 *
	 * @param email, password
	 * @return UserSign
	 */
	public UserSign loginSign(String email, String password) {

		UserSign newuser = new UserSign();
		// 회원아이디 체크
		UserSign currentuser = userSignRepository.findByEmail(email);

		// 아이디가 틀렸을 때
		if (currentuser == null) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new ApiException(ExceptionEnum.SECURITY_01);
		}

		// parameter1 : rawPassword, parameter2 : encodePassword
		boolean check = passwordEncoder.matches(password, currentuser.getPassword());
		// 로그인 성공
		if (!check) {
//				return new DefaultRes(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS);
			throw new ApiException(ExceptionEnum.SECURITY_01);
		}
		newuser = currentuser;
		return newuser;

	}
}
