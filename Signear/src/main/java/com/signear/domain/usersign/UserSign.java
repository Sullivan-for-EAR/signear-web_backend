package com.signear.domain.usersign;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "user_sign", schema = "eardb")
public class UserSign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer signid;

	@Column(length = 255, nullable = true)
	private String email;

	@Column(length = 255, nullable = true)
	private String password;

	@Column(nullable = true)
	private Date modDate;

	@Column(nullable = true)
	private Date regDate;

	@Column(nullable = true)
	private String address;

	@Column(length = 255, nullable = true)
	private String phone;
}
