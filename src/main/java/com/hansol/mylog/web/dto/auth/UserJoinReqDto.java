package com.hansol.mylog.web.dto.auth;


import com.hansol.mylog.domain.user.user;

import lombok.Data;

@Data
public class UserJoinReqDto {

	private String username;
	private String password;
	private String email;
	private String name;
	
	public user toEntity() {
		return user.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
