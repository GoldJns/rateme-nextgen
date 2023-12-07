package com.app.rateme.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.rateme.model.User;

public class AuthenticatedUser extends User implements UserDetails {

	public AuthenticatedUser(User userEntity) {
		super();

		this.setUserId(userEntity.getUserId());
		this.setFirstname(userEntity.getFirstname());
		this.setLastname(userEntity.getLastname());
		this.setMail(userEntity.getMail());
		this.setPassword(userEntity.getPassword());
		this.setRoles(userEntity.getRoles());
		this.setCity(userEntity.getCity());
		this.setStreet(userEntity.getStreet());
		this.setStreetNr(userEntity.getStreetNr());
		this.setUsername(userEntity.getUsername());
		this.setZip(userEntity.getZip());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.getRoles());
		 return Arrays.asList(authority);
	}

	@Override
	public String getUsername() {
		return this.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "AuthenticatedUser [username=" + getUsername() + ", id=" + getUserId() + "]";
	}
}