package br.dev.botecodigital.microblog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilterRegistration() {
		FilterRegistrationBean<JwtFilter> filter = new FilterRegistrationBean<JwtFilter>();
		filter.setFilter(this.jwtFilter);
		filter.addUrlPatterns("/api/users/*", "/api/posts/*");
		return filter;
	}
}
