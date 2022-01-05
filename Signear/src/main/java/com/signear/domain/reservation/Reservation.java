package com.signear.domain.reservation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.signear.application.main.reserverInfo.model.ReserverInfo;
import com.signear.domain.usercustomer.UserCustomer;
import com.signear.domain.usersign.UserSign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(name = "findReserverInfoMapper", classes = @ConstructorResult(targetClass = ReserverInfo.class, columns = {
		@ColumnResult(name = "email", type = String.class), @ColumnResult(name = "phone", type = String.class),
		@ColumnResult(name = "request", type = String.class), @ColumnResult(name = "date", type = String.class),
		@ColumnResult(name = "memo", type = String.class), @ColumnResult(name = "rsid", type = Integer.class),
		@ColumnResult(name = "customerid", type = Integer.class), }))
@NamedNativeQuery(name = "findReserverInfo", resultSetMapping = "findReserverInfoMapper", query = "SELECT r.customerid ,r.rsid ,r.date , r.request, u.phone, u.memo, u.email FROM reservation r INNER JOIN user_customer u ON u.customerid = r.customerid AND r.area = :area WHERE r.date = (select max(r2.date) from reservation r2 where r.customerid = r2.customerid) GROUP BY r.customerid")
@NamedNativeQuery(name = "findReserverInfoByCustomeridAndArea", resultSetMapping = "findReserverInfoMapper", query = "SELECT r.customerid ,r.rsid ,r.date , r.request, u.phone, u.memo, u.email FROM reservation r INNER JOIN user_customer u ON u.customerid = r.customerid AND r.area = :area AND r.customerid = :customerid WHERE r.date = (select max(r2.date) from reservation r2 where r.customerid = r2.customerid) GROUP BY r.customerid")
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

	@Column(nullable = true, insertable = false, updatable = false)
	private Integer customerid;

	@Column(nullable = true, insertable = false, updatable = false)
	private Integer signid;

	@Column(nullable = true)
	private String reject;

	@Column(nullable = true)
	private String start_time;

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
