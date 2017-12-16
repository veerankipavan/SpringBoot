package com.sprsec.exercise.config;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
@Component
public class CustomFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("destroy called");

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("filter called");
		HttpServletRequest httpServletRequest =	(HttpServletRequest)servletRequest;
		Principal userPrincipal = httpServletRequest.getUserPrincipal();
		System.out.println("Name of the Principal :"+userPrincipal);
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init called");

	}

}
