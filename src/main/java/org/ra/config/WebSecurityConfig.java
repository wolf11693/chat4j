package org.ra.config;

import org.ra.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
					.csrf().disable()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
					.cors()
						.and()
					.addFilterBefore(this.authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests().antMatchers("/home", "/authenticate", "/register").permitAll().anyRequest().authenticated();

		httpSecurity
					.headers().cacheControl();
	}
	
	@Bean
  	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
	  return new JwtAuthenticationTokenFilter();
	}

	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		 authenticationManagerBuilder
         							 .userDetailsService(this.userDetailsService)
         							 .passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	 }
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

}