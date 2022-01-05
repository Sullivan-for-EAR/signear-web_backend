package com.signear.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.signear.application.main.login.JwtService;
import com.signear.domain.usersign.UserSign;
import com.signear.domain.usersign.UserSignRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	UserSignRepository userSignRepository;
	@Autowired
	JwtService jwtService;

	@Override
	public CustomUserDetail loadUserByUsername(String email) throws UsernameNotFoundException {
		// 사용자 정보 조회
		UserSign userSign = userSignRepository.findByEmail(email);
		CustomUserDetail customUserDetails = new CustomUserDetail();
		if (userSign != null) {
			customUserDetails.setUsername(userSign.getEmail());
			customUserDetails.setPassword(userSign.getPassword());
			customUserDetails.setUserSign(userSign);
			// 권한 추가
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_SIGN"));
			customUserDetails.setAuthorities(authorities);
			customUserDetails.setEnabled(true);
			customUserDetails.setAccountNonExpired(true);
			customUserDetails.setAccountNonLocked(true);
			customUserDetails.setCredentialsNonExpired(true);
			customUserDetails.setEnabled(true);
		}
		return customUserDetails;
	}

}
