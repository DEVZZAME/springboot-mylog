package com.hansol.mylog.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user, Integer>{
	user findByUsername(String username);
}
