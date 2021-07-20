package com.signear.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
		customAuthenticationFilter.setFilterProcessesUrl("/login");
		customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
		customAuthenticationFilter.afterPropertiesSet();
		return customAuthenticationFilter;
	}

	@Bean
	public CustomLoginSuccessHandler customLoginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("어댑터 탐");
		http.cors().disable() // cors 비활성화
				.csrf().disable() // csrf 비활성화
				.formLogin().disable() // 기본 로그인 페이지 없애기
				.authorizeRequests() // /about 요청에 대해서는 로그인을 요구함
				.antMatchers("/login/userCustomer").authenticated() // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
				.antMatchers("/admin").hasRole("ADMIN") // 나머지 요청에 대해서는 로그인을 요구하지 않음
				.anyRequest().permitAll().and() // 로그인하는 경우에 대해 설정함
				.formLogin() // 로그인 페이지를 제공하는 URL을 설정함
				.loginPage("/") // 로그인 성공 URL을 설정함
				.successForwardUrl("/") // 로그인 실패 URL을 설정함
				.failureForwardUrl("/").permitAll().and()
				.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider(getPasswordEncoder());
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
	}

}