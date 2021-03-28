package org.ra.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static Logger LOG = LoggerFactory.getLogger(JwtTokenUtil.class);
	
	private static final long serialVersionUID = -2565739196201969700L;

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "iat";
	static final String CLAIM_KEY_AUTHORITIES = "roles";
	static final String CLAIM_KEY_IS_ENABLED = "isEnabled";
	
	private static final String AUDIENCE_MOBILE = "mobile";
	private static final String AUDIENCE_TABLET = "tablet";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Autowired
	ObjectMapper objectMapper;

	public Boolean validateToken(String token, UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		
		final String username = this.getUsernameFromToken(token);
		
		return  username.equals(user.getUsername()) && !isTokenExpired(token);
	}
	
	public String getUsernameFromToken(String jwtToken) {
		String username = null;
		
		try {
			final Claims claims = this.getClaimsFromToken(jwtToken);
			username = claims.getSubject();
		} catch (Exception ex) {
			LOG.error("invalid jwt token",  ex);
			username = null;
		}

		return username;
	}

	public JwtUser getUserDetailsFromToken(String jwtToken) {
		if (jwtToken == null) {
			return null;
		}
		
		try {
			final Claims claims = this.getClaimsFromToken(jwtToken);
			List<SimpleGrantedAuthority> authorities = null;
			
			if (claims.get(CLAIM_KEY_AUTHORITIES) != null) {
				
				List<String> roles = (List<String>) claims.get(CLAIM_KEY_AUTHORITIES);
				
				authorities = roles.stream()
								   .map(role -> new SimpleGrantedAuthority(role))
								   .collect(Collectors.toList());
			}

			return new JwtUser(claims.getSubject(), "", authorities, (boolean) claims.get(CLAIM_KEY_IS_ENABLED));
		} catch (Exception ex) {
			LOG.error("invalid jwt token",  ex);
			return null;
		}

	}

	public Date getCreatedDateFromToken(String jwtToken) {
		Date created = null;
		
		try {
			final Claims claims = this.getClaimsFromToken(jwtToken);
			created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
		} catch (Exception ex) {
			LOG.error("jwt not contain creation date info!",  ex);
			created = null;
		}
		
		return created;
	}

	public Date getExpirationDateFromToken(String jwtToken) {
		Date expiration = null;
		
		try {
			final Claims claims = this.getClaimsFromToken(jwtToken);
			expiration = claims.getExpiration();
		} catch (Exception ex) {
			LOG.error("jwt not contain expiration date info!",  ex);
			expiration = null;
		}
		
		return expiration;
	}
	
	public String getAudienceFromToken(String jwtToken) {
		String audience = null;
		
		try {
			final Claims claims = this.getClaimsFromToken(jwtToken);
			audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
		} catch (Exception e) {
			audience = null;
		}
		
		return audience;
	}

	private Claims getClaimsFromToken(String jwtToken) {
		Claims claims = null;
		
		try {
			claims = Jwts.parser()
						 .setSigningKey(secret)
						 .parseClaimsJws(jwtToken)
						 .getBody();
		} catch (Exception e) {
			claims = null;
		}
		
		return claims;
	}
	
	public String generateToken(UserDetails userDetails) throws JsonProcessingException {
		Map<String, Object> claims = new HashMap<>();
		
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, new Date());
		
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		
		List<String> auth = roles.stream()
								 .map(role -> role.getAuthority())
								 .collect(Collectors.toList());
		
		claims.put(CLAIM_KEY_AUTHORITIES, auth);
		claims.put(CLAIM_KEY_IS_ENABLED, userDetails.isEnabled());

		String jwtToken =  this.generateToken(claims);
		
		return jwtToken;
	}

	String generateToken(Map<String, Object> claims) {
		String jwtToken = Jwts.builder()
							  .setClaims(claims)
							  .setExpiration(generateExpirationDate())
							  .signWith(SignatureAlgorithm.HS256, secret)
							  .compact();
		
		return jwtToken;
	}
	
	public Boolean canTokenBeRefreshed(String jwtToken) {
		return (!isTokenExpired(jwtToken) || ignoreTokenExpiration(jwtToken));
	}
	
	private Date generateExpirationDate() {
		Date expirationDate = new Date(System.currentTimeMillis() + expiration * 1000);
		return expirationDate;
	}

	private Boolean ignoreTokenExpiration(String token) {
		String audience = this.getAudienceFromToken(token);
		return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
	}
	
	private Boolean isTokenExpired(String jwtToken) {
		final Date expiration = this.getExpirationDateFromToken(jwtToken);
		return expiration.before(new Date());
	}
	

	public String refreshToken(String jwtToken) {
		String refreshedToken = null;
		
		try {
			final Claims claims = this.getClaimsFromToken(jwtToken);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		
		return refreshedToken;
	}
	
}