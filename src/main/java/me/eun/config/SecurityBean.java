package me.eun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import me.eun.security.CustomLoginSuccessHandler;
import me.eun.security.CustomNoopPasswordEncoder;
import me.eun.security.CustomUserDetaliService;

@Configuration
public class SecurityBean {

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetaliService();
	}
	@Bean
	public  PasswordEncoder bcryptPwEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	public PasswordEncoder nooPencoder() {
		return new CustomNoopPasswordEncoder();
	}
}
