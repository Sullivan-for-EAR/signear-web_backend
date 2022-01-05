package com.signear.domain.authtokeninfo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.signear.domain.usercustomer.UserCustomer;
import com.signear.domain.usersign.UserSign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth_token_info", schema = "eardb")
public class AuthTokenInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private String tokenValue;

	@Column(nullable = false)
	private Date expireDate;

	@Column(nullable = true, insertable = false, updatable = false)
	private Integer customerid;

	@Column(nullable = true, insertable = false, updatable = false)
	private Integer signid;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "customerid")
	private UserCustomer userCustomer;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "signid")
	private UserSign userSign;

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
