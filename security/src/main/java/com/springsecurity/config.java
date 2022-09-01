package com.springsecurity;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import  org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration. WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories; 
import org.springframework.security.crypto.password.NoOpPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
//@EnableAutoConfiguration 
public class config extends  WebSecurityConfigurerAdapter{

	@Autowired 
	DataSource ds;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(ds).withDefaultSchema().withUser(User.withUsername("sv").password(getPasswordEncoder().encode("sv@123")).roles("analyst"));	
	}

	@Bean 
	PasswordEncoder getPasswordEncoder() { 
		  //return NoOpPasswordEncoder.getInstance(); 
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers("/h2-console/**"); 
		
	}
	
	 


}