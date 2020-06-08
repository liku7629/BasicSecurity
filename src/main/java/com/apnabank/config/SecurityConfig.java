package com.apnabank.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javassist.bytecode.analysis.Analyzer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser(
						User.withUsername("liku")
						    .password("123")
						    .roles("ADMIN")
				)
				.withUser(
						User.withUsername("chiku")
						    .password("234")
						    .roles("DB")
				);
				
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/index").permitAll()
			.antMatchers("/db").hasAnyRole("ADMIN", "DB")
			.antMatchers("/admin").hasRole("ADMIN")
			
			.and()
			.formLogin()
			.permitAll()
			
			.and()
			.logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
			
			
			.and()
			.exceptionHandling()
			.accessDeniedPage("/access_denied");
	}
	
	@Bean
	@ConditionalOnMissingBean
	public PasswordEncoder getNoOpPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
