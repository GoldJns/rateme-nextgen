package com.app.rateme.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", getRoles()));
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