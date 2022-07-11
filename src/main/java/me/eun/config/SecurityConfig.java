package me.eun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@Import (value = {SecurityBean.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationSuccessHandler loginSuccessHandler;
	
	@Autowired
	@Qualifier (value = "bcryptPwEncoder")
	PasswordEncoder passwordEncoder;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/security/all").permitAll()
		.antMatchers("/security/admin").access("hasRole('ADMIN')")
		.antMatchers("/security/member").access("hasRole('MEMBER')");
	
		http.formLogin()
		.usernameParameter("loginId")
		.passwordParameter("loginPw")
		.loginPage("/customLogin")
		.loginProcessingUrl("/member/login")
		.successHandler(loginSuccessHandler);
		
		http.logout()
		   .logoutUrl("/customLogout")
		   .invalidateHttpSession(true)
		   .deleteCookies("remember-me" , "JSESSION_ID");
		  
	}
	
	

}
