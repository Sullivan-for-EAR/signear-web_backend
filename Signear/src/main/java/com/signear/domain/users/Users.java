package com.signear.domain.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer user_no;

	@Column(length = 255, nullable = true)
	private String email;

	@Column(length = 255, nullable = true)
	private String nickname;

	@Column(length = 255, nullable = true)
	private String password;

}
