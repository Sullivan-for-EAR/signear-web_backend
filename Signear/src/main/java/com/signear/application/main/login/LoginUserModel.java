package com.signear.application.main.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.signear.domain.usersign.UserSign;

public class LoginUserModel extends User {

	private static final long serialVersionUID = -5603588930369924524L;

	public LoginUserModel(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}

	private UserSign userSign;
	private String userType;// C:청각장애인, S:수화통역사, A: admin

	public UserSign getUserSign() {
		return userSign;
	}

	public void setUserSign(UserSign userSign) {
		this.userSign = userSign;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
