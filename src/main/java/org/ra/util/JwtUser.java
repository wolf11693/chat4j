package org.ra.util;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = -726723675948096654L;
	
	private final Long id;
	private final String username;
	private final String firstname;
	private final String lastname;
	private final String email;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	private final Date lastPasswordResetDate;

	public JwtUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean userEnabled) {
		this(null, username, null, null, null, password, authorities, userEnabled, null);
	}
	
	public JwtUser() {
		this(null, null, null, null, null, null, null, false, null);
	}

	public JwtUser( Long id, String username, 
					String firstname, String lastname,
					String email, String password,
					Collection<? extends GrantedAuthority> authorities, boolean enabled, 
					Date lastPasswordResetDate) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@JsonIgnore
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}
}