package com.signear.domain.usersign;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSignRepository extends JpaRepository<UserSign, Integer> {
	UserSign findByEmail(String email);
}
