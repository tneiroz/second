package me.eun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
@EnableWebSecurity
@Import (value = {SecurityBean.class})
@ComponentScan("me.eun.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	AuthenticationSuccessHandler loginSuccessHandler;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	@Qualifier (value = "bcryptPwEncoder")
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationFailureHandler failureHandler;
	
	@Autowired
	PersistentTokenRepository persistentTokenRepository;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
	       filter.setEncoding("UTF-8");
	       filter.setForceEncoding(true);
	       http.addFilterBefore(filter, CsrfFilter.class);
	       
		http.csrf()
		  .ignoringAntMatchers("/uploadAjaxAction", "/deleteFile","/replies/**");
		http.authorizeRequests()
		.antMatchers("/security/all").permitAll()
		.antMatchers("/security/admin").access("hasRole('ADMIN')")
		.antMatchers("/security/member").access("hasRole('MEMBER')")
		.and()
			.formLogin()
		.usernameParameter("loginId")
		.passwordParameter("loginPw")
		.loginPage("/customLogin")
		.loginProcessingUrl("/member/login")
		.successHandler(loginSuccessHandler)
		.failureHandler(failureHandler);
		
		http.rememberMe().key("project")
		.tokenRepository(persistentTokenRepository)
		.tokenValiditySeconds(604800);
		
		http.logout()
		   .logoutUrl("/customLogout")
		   .invalidateHttpSession(true)
		   .deleteCookies("remember-me" , "JSESSION_ID");
		  
	}


}
	

	
/* http 안쓰고 .and()로 이을 수 있다. 
 * http.authorizeRequests()
		.antMatchers("/security/all").permitAll()
		.antMatchers("/security/admin").access("hasRole('ADMIN')")
		.antMatchers("/security/member").access("hasRole('MEMBER')")
		
		
		.and()   <--- and 로 연결 했을 경우에는 ↑ 윗줄에 ; 없어야 됨 
			.formLogin()

 */




