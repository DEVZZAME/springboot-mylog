package com.hansol.mylog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private static final long serialVersionUID = 1L;

	private com.hansol.mylog.domain.user.user user;
	private Map<String, Object> attributes;

	public PrincipalDetails(com.hansol.mylog.domain.user.user user) {
		this.user = user;
	}

	public PrincipalDetails(com.hansol.mylog.domain.user.user user, Map<String, Object> attributes) {
		this.user = user;
	}

	// 권한 : 한개가 아닐 수 있음. (3개 이상의 권한)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collector = new ArrayList<>();
		collector.add(() -> { return user.getRole();});
		return collector;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
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
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String) attributes.get("name");
	}
}
