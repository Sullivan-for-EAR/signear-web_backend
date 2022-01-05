package com.signear.application.main.reserverInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.signear.application.main.reserverInfo.model.ReserverInfoModel;
import com.signear.application.main.reserverInfo.service.ReserverInfoService;
import com.signear.domain.reservation.ReservationRepositiory;
import com.signear.domain.usercustomer.UserCustomer;
import com.signear.domain.usercustomer.UserCustomerRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("reserverInfo")
public class ReserverInfoRestController {
	@Autowired
	ReserverInfoService reserverInfoService;
	@Autowired
	UserCustomerRepository userCustomerRepository;
	@Autowired
	ReservationRepositiory reservationRepositiory;

	@ApiOperation(value = "예약자 정보 목록 조회", notes = "예약자의 사용자 정보를 조회")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ReserverInfoModel getReservationListAll(
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "numberPerPage", required = false) Integer numberPerPage,
			@RequestParam("area") String area,
			@RequestParam(value = "customerid", required = false) Integer customerid) {
		if (customerid == null) {
			return reserverInfoService.getList(pageNumber, numberPerPage, area);
		} else {
			return reserverInfoService.getReserverInfo(customerid, area);
		}

	}

	@ApiOperation(value = "메모 삭제", notes = "메모 내용을 삭제한다.")
	@RequestMapping(value = "/deleteMemo/{customerid}", method = RequestMethod.POST)
	public void deleteMemo(@PathVariable("customerid") Integer customerid) {

		reserverInfoService.deleteMemo(customerid);

	}

	@ApiOperation(value = "메모 수정", notes = "메모를 수정한다.")
	@RequestMapping(value = "/updateMemo", method = RequestMethod.POST)
	public void updateReservation(@RequestParam Integer customerid, @RequestParam String memo) {

		reserverInfoService.updateMemo(customerid, memo);

	}

	@ApiOperation(value = "메모 조회", notes = "customerid로 메모 상세 내용을 조회한다.")
	@RequestMapping(value = "/getMemo/{customerid}", method = RequestMethod.GET)
	public UserCustomer getReservationInfo(@PathVariable("customerid") Integer customerid) {

		return reserverInfoService.getMemo(customerid);

	}

}