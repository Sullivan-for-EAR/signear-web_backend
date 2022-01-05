package com.signear.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailService customUserDetailsService;
//	private AuthenticationEntryPoint jwtAuthenticationEntryPoint;
//	private Filter jwtRequestFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		// 비밀번호 인코더
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/css/**, /static/js/**, *.ico");
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui/index.html", "/webjars/**", "/swagger/**", "/swagger-ui/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// sign role 있는 경우에만 허용

		http.csrf().disable().authorizeRequests().antMatchers("/", "/login/signuser").permitAll().and()
				.authorizeRequests().antMatchers("/management/**", "/reservation/**", "/reserverInfo/**").permitAll()
				// .hasRole("SIGN")
				.anyRequest().authenticated().and().exceptionHandling()
				// .authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and().formLogin().disable().headers().frameOptions().disable();

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.invalidateHttpSession(true);

		http.exceptionHandling().accessDeniedPage("/error.html");
		// http.addFilterBefore(jwtRequestFilter,
		// UsernamePasswordAuthenticationFilter.class);

	}

}