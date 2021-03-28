package org.ra.service;

import javax.servlet.http.HttpSession;

import org.ra.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatonService {

	public static final Logger LOG = LoggerFactory.getLogger(AuthenticatonService.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	
	public String authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken, HttpSession session) throws Exception {
		String token = null;
		
		final String username = usernamePasswordAuthenticationToken.getName();
		final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		if(authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);

			SecurityContext securityContextHolder = SecurityContextHolder.getContext();
			LOG.info("Name = {}", securityContextHolder.getAuthentication().getName());
			LOG.info("Credentials = {}", securityContextHolder.getAuthentication().getCredentials());
			LOG.info("Authorities = {}", securityContextHolder.getAuthentication().getAuthorities());
			LOG.info("Principal = {}", securityContextHolder.getAuthentication().getPrincipal());
			LOG.info("Details = {}", securityContextHolder.getAuthentication().getDetails());
			
			final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			token = this.jwtTokenUtil.generateToken(userDetails);
			session.setAttribute("username", userDetails.getUsername());
		} 
		
		return token;
	}
	
	public String refreshToken(String jwtTokenToRefresh) {
		String refreshedToken = null;
		
		if (jwtTokenUtil.canTokenBeRefreshed(jwtTokenToRefresh)) {
			refreshedToken = this.jwtTokenUtil.refreshToken(jwtTokenToRefresh);
		}
		
		return refreshedToken;
		
	}
}