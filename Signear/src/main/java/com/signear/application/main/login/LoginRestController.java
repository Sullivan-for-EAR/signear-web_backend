package com.signear.application.main.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.signear.application.exception.ApiException;
import com.signear.application.exception.ExceptionEnum;
import com.signear.domain.usersign.UserSign;

@RestController
public class LoginRestController {
	@Autowired
	LoginService loginService;
	@Autowired
	JwtService jwtService;

	@PostMapping("/login/signuser")
	public Map<String, Object> LoginForCustomer(@RequestParam String email, @RequestParam String password)
			throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		UserSign userSign = loginService.loginSign(email, password);

		if (userSign != null && userSign.getSignid() != null) {
			// JWT 발급
			String jwtToken = jwtService.createToken(userSign.getEmail());
			returnMap.put("userProfile", userSign);
			returnMap.put("access_token", jwtToken);
			return returnMap;

		} else {
			// throw new Exception();
			throw new ApiException(ExceptionEnum.SECURITY_01);
		}
	}

}
