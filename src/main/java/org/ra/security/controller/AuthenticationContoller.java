package org.ra.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ra.request.AuthenticationRequest;
import org.ra.request.AuthenticationResponse;
import org.ra.service.AuthenticatonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationContoller {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationContoller.class);

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticatonService authService;

	@PostMapping(path = "/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest, HttpServletResponse response, HttpSession session) throws Exception {
		LOG.info("createAuthenticationToken - START - body={}", authRequest);
		LOG.info(">> POST /autenticate");

		String username = authRequest.getUsername();
		String password = authRequest.getPassword();

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		String jwtToken = this.authService.authenticate(usernamePasswordAuthenticationToken, session);

		if (jwtToken == null || jwtToken.trim().equals("")) {
			throw new Exception("token not valid");
		}

		response.setHeader(tokenHeader, jwtToken);

		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setAuthenticated(true);
		authResponse.setJwtToken(jwtToken);
		authResponse.setUsername(username);

		LOG.info("<< [ username={}, authenticated={}, token={} ]", authResponse.getUsername(), authResponse.isAuthenticated(), authResponse.getJwtToken());
		LOG.info("createAuthenticationToken - END");
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/protected/refresh-token")
	public ResponseEntity<AuthenticationResponse> refreshAndGetAuthenticationToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("refreshAndGetAuthenticationToken - START");
		LOG.info(">> GET /protected/refresh-token");
		
		String token = request.getHeader(tokenHeader);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String refreshedToken = this.authService.refreshToken(token);

		if (refreshedToken == null) {
			throw new Exception("token not be refreshed beacause is already expired");
		}

		response.setHeader(tokenHeader, refreshedToken);
		
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setAuthenticated(true);
		authResponse.setJwtToken(refreshedToken);
		authResponse.setUsername(userDetails.getUsername());

		LOG.info("<< [ {}\n     {}\n     {} ]", authResponse.getUsername(), authResponse.isAuthenticated(), authResponse.getJwtToken());
		return new ResponseEntity<AuthenticationResponse>(authResponse, HttpStatus.OK);
	}
}