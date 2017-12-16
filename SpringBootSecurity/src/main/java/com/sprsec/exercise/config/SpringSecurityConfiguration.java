package com.sprsec.exercise.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
		.withUser("admin").password("admin").roles("admin").
		and().withUser("user").password("user").roles("user");
		
	}

	
	@Override
	protected void configure(HttpSecurity httpsecurity) throws Exception {
		
		httpsecurity.//antMatcher("/rest/**").
		//authorizeRequests().anyRequest().fullyAuthenticated().and().addFilterBefore(customfilter(), BasicAuthenticationFilter.class).httpBasic();
		authorizeRequests().antMatchers("/rest/**").access("hasRole('admin') or hasRole('user')").and().httpBasic();
		//authorizeRequests().antMatchers("/rest/**").access("hasRole('admin') or hasRole('user')").and().addFilterBefore(customfilter(), BasicAuthenticationFilter.class).httpBasic(); 
		//anyRequest().fullyAuthenticated().and().httpBasic();		
		httpsecurity.csrf().disable();
	}

	@Bean
	protected CustomFilter customfilter() {
		// TODO Auto-generated method stub
		return new CustomFilter();
	}

}
