
package com.signear.domain.authtokeninfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenInfoRepositiory extends JpaRepository<AuthTokenInfo, Integer> {
	AuthTokenInfo findByTokenValue(String tokenValue);

	AuthTokenInfo findBySignid(String signid);

}