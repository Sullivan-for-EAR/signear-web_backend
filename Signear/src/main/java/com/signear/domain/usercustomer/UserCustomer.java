package com.signear.domain.usercustomer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer customerid;

	@Column(length = 255, nullable = true)
	private String email;

	@Column(length = 255, nullable = true)
	private String phone;

	@Column(length = 255, nullable = true)
	private String password;

	@Column(nullable = true)
	private Date regDate;

	@Column(nullable = true)
	private Date modDate;

}
