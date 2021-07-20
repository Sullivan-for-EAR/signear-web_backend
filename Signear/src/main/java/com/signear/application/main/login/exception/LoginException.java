package com.signear.application.main.login.exception;

public class LoginException extends RuntimeException {
	private static final long serialVersionUID = -8804657308742213498L;

	public LoginException() {
		super("로그인에 실패하였습니다.");
	}
}
