package com.signear.domain.reservation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer rsid;

	@Column(length = 255, nullable = true)
	private String address;

	@Column(length = 255, nullable = true)
	private String area;

	@Column(length = 255, nullable = true)
	private String date;

	@Column(length = 255, nullable = true)
	private String end_time;

	@Column(length = 255, nullable = true)
	private Integer method;

	@Column(nullable = true)
	private String request;

	@Column(nullable = true)
	private Integer status;

	@Column(nullable = true)
	private Integer type;

	@Column(nullable = true)
	private Integer customerid;

	@Column(nullable = true)
	private Integer signid;

	@Column(nullable = true)
	private String reject;

	@Column(nullable = true)
	private String start_time;

	@ManyToOne(optional = false)
	@JoinColumn
	private UserCustomer userCustomer;

	@ManyToOne(optional = false)
	@JoinColumn
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
