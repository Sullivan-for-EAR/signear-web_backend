package com.signear.application.main.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagementModel {

	private Integer rsID;

	private String date;

	private String start_time;

	private String end_time;

	private String area;

	private String address;

	private Integer method;

	private Integer status;

	private Integer type;

	private String request;

	private String reject;

//		private Integer customerID;
	//
//		private Integer signID;
}
