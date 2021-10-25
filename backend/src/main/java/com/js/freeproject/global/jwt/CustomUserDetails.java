package com.js.freeproject.global.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.js.freeproject.domain.user.domain.User;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	User user;
	boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialNonExpired;
    boolean enabled = false;
	List<GrantedAuthority> roles = new ArrayList<>();

	public CustomUserDetails(User user) {
		super();
		this.user=user;
		GrantedAuthority grantedAutoAuthority = new SimpleGrantedAuthority(user.getRole().toString());
		this.roles = Collections.singletonList(grantedAutoAuthority);
	}
	
	@Override
	public String getPassword() {
		return this.user.getPass();
	}
	@Override
	public String getUsername() {
		return this.user.getName();
	}
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	
	public Long getUserid() {
		return this.user.getId();
	}
	
	public String getUseremail() {
		return this.user.getEmail();
	}
}
