package org.ra.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ra.listner.MySessionListner;
import org.ra.manager.SessionManager;
import org.ra.util.JwtTokenUtil;
import org.ra.util.JwtUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	private static final String[] PERMITTED_PATHS = { "/authenticate", "/login", "/home", "/" };

	private final String USERNAME_USERS_IN_SESSION = "usernameUserList";
	
	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private MySessionListner sessionListner;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		LOG.info("doFilterInternal - START");

		try {
			String requestPath = request.getServletPath();
			String authenticationToken = this.getTokenFromRequest(request);
			JwtUser userDetails = this.jwtTokenUtil.getUserDetailsFromToken(authenticationToken);

			if (this.isPermittedPathWithoutAuthenticationAndAuthorization(requestPath)) {
				LOG.info("doFilterInternal - END");
				filterChain.doFilter(request, response);
			} else

			if (!this.isAuthenticated(request)) {
				LOG.info("doFilterInternal - END");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN); // HTTP 403
				response.getOutputStream().print("User is not authenticated!");
				return;
			} else if (!this.isAuthorized(request)) {
				LOG.info("doFilterInternal - END");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401
				response.getOutputStream().print("User is not authorized!");
				return;
			} else {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);

				LOG.info("doFilterInternal - END");
				filterChain.doFilter(request, response);
			}
		} catch (Exception ex) {
			response.getOutputStream().print(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private boolean isAuthenticated(HttpServletRequest request) throws Exception {
		if (!this.hasAuthorizationHeader(request)) {
			return false;
		}

		String authenticationToken = this.getTokenFromRequest(request);
		if (authenticationToken == null || authenticationToken.trim().equals("")) {
			return false;
		}

		JwtUser userDetails = this.jwtTokenUtil.getUserDetailsFromToken(authenticationToken);
		if (userDetails == null) {
			return false;
		}
		
		SessionManager sessionManager = sessionListner.getSessionManager();
		List<String> usernameUsersSessionList = (List<String>) sessionManager.getUsernameUserInSession();
		
		if(usernameUsersSessionList == null || !usernameUsersSessionList.contains(this.jwtTokenUtil.getUsernameFromToken(authenticationToken)) ){
			throw new Exception("token was inavalidate after logout!");
		}
		
		Boolean isTokenValid = this.jwtTokenUtil.validateToken(authenticationToken, userDetails);
		if (!isTokenValid) {
			return false;
		}

		return true;
	}

	private boolean isAuthorized(HttpServletRequest request) {
		return true;
	}

	private boolean hasAuthorizationHeader(HttpServletRequest request) {
		String authenticationToken = request.getHeader(this.tokenHeader);

		return authenticationToken != null;
	}

	private boolean isPermittedPathWithoutAuthenticationAndAuthorization(String pathToCheckIfPermit) {
		List<String> listPermitPaths = Arrays.asList(this.PERMITTED_PATHS);
		boolean isPermit = listPermitPaths.contains(pathToCheckIfPermit);

		return isPermit;
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		return request.getHeader(this.tokenHeader);
	}

}