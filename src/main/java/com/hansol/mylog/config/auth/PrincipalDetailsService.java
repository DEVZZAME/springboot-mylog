package com.hansol.mylog.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hansol.mylog.domain.user.User;
import com.hansol.mylog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("로그인 진행중 .......");
		User userEntity = userRepository.findByUsername(username);
		if(userEntity == null) {
			return null;
		}else {
			return new PrincipalDetails(userEntity); // SecurityContextHolder => Authentication 객체 내부에 담김.
		}
		
	}

}
