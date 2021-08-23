package com.signear.domain.usercustomer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "user_customer", schema = "eardb")
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

	/**
	 * 생성 일자
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REG_DATE")
	@CreationTimestamp
	private Date regDate;

	/**
	 * 업데이트 일자
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MOD_DATE")
	@UpdateTimestamp
	private Date modDate;
}
