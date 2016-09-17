package com.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.library.service.UserService;
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	private String url="/static/app/html/";
	private String partialUrl="/static/app/html/partial/";
	
	
	
	@Override
	protected void configure(HttpSecurity http){
		try {
			http
				
				.httpBasic()
				.and()
				.formLogin().loginPage(this.partialUrl+"login.html")
				.and()
				.authorizeRequests()
				.antMatchers(this.partialUrl+"users.html").hasAuthority("ADMIN")
				.antMatchers(this.partialUrl+"authors.html").authenticated()
				.antMatchers(this.url+"index.html", this.partialUrl+"home.html", this.partialUrl+"login.html").permitAll()
				.anyRequest().permitAll()
				
				.and()
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				
				.and()
				.logout().clearAuthentication(true).logoutSuccessUrl("/");
//				.and()
//				.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl(this.partialUrl+"home.html");
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	}
	@Override	
	protected void configure(AuthenticationManagerBuilder auth){
		System.out.println("configure auth");
		try {
			auth
			
				.userDetailsService(new UserServiceDetails(userService));
//				.inMemoryAuthentication()
//					.withUser("kk").password("123").roles("USER");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
