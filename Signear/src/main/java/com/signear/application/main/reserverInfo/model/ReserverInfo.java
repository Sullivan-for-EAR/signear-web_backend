package com.signear.application.main.reserverInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReserverInfo {

	private String email;
	private String phone;
	private String request;
	private String date;
	private String memo;
	private Integer rsid;
	private Integer customerid;

}
