package com.hansol.mylog.config;

import com.hansol.mylog.config.oauth.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final OAuth2DetailsService oAuth2DetailsService;

	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	// 모델 : Image, User, Likes, Subscribe, Tag : 인증 필요함.
	// auth 주소 : 인증 필요없음.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/", "/user/**", "/image/**", "/subscribe/**, /comment/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.anyRequest()
				.permitAll()
				.and()
				.formLogin()
				.loginPage("/auth/signin")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/")
				.and()
				.oauth2Login()
				.userInfoEndpoint()
				.userService(oAuth2DetailsService);
	}
}



