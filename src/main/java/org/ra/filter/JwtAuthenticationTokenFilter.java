package org.ra.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ra.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	private static final String AUTH_PATH = "/authenticate";

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		LOG.info("doFilterInternal - START");
		LOG.info("request HEADER={}", request.getHeaderNames());

				
		String authenticationToken = request.getHeader(this.tokenHeader);
		UserDetails userDetails = null;

		if (authenticationToken != null) {
			userDetails = this.jwtTokenUtil.getUserDetailsFromToken(authenticationToken);
		}

		if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// Ricostruisco l userdetails con i dati contenuti nel token
			// controllo integrita' token
			Boolean isValidToken = this.jwtTokenUtil.validateToken(authenticationToken, userDetails);

			if (isValidToken) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				String usernameUserSession = (String) request.getSession().getAttribute("username");
				String requestPath = request.getServletPath();
				
				if(requestPath.equals(AUTH_PATH) || ( usernameUserSession != null && usernameUserSession.equals(authentication.getName())) ) {
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}

		filterChain.doFilter(request, response);
		LOG.info("doFilterInternal - END");
	}
}
