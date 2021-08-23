package com.signear.application.main.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.signear.application.main.reservation.service.ReservationService;
import com.signear.domain.reservation.Reservation;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("reservation")
public class ReservationRestController {
	@Autowired
	ReservationService reservationService;

	@ApiOperation(value = "예약 취소", notes = "청각 장애인이 reservation_id를 통해 얘약을 취소한다.")
	@RequestMapping(value = "/cancel/{rsid}", method = RequestMethod.POST)
	public Reservation cancelReservation(@PathVariable("rsid") Integer reservation_id) {

		return reservationService.cancel(reservation_id);

	}

	@ApiOperation(value = "예약 수정", notes = "청각 장애인이 reservation_id를 통해 얘약정보를 수정한다.")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Reservation updateReservation(@RequestBody Reservation Reservation) {

		return reservationService.update(Reservation);

	}

	@ApiOperation(value = "예약 상세정보 조회", notes = " reservation_id를 통해 청각장애인의 얘약정보를 조회한다.")
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public Reservation getReservationInfo(@RequestParam Integer reservation_id) {

		Reservation ReservationResult = reservationService.getOneByRsID(reservation_id);

		return ReservationResult;
	}

	@ApiOperation(value = "청각장애인의 예약목록", notes = "청각 장애인의 customer_id를 통해 얘약 목록을 조회한다")
	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public List<Reservation> getReservationList(@RequestParam Integer customer_id) {

		List<Reservation> ReservationResult = reservationService.getListByCustomerID(customer_id);

		return ReservationResult;
	}

	@ApiOperation(value = "날짜를 기준으로 예약 신청한 목록을 조회", notes = "예약 날짜를 기준으로 모든 청각장애인이 예약 신청한 목록을 조회한다. 날짜형식 yyyymmdd")
	@RequestMapping(value = "/rsDateList", method = RequestMethod.GET)
	public List<Reservation> getReservationListToday(@RequestParam String fromDate) {

		List<Reservation> ReservationResult = reservationService.getReservationListAfterCurrentDate(fromDate);

		return ReservationResult;
	}

	@ApiOperation(value = "예약 신청한 모든 목록을 조회", notes = "모든 청각장애인이 예약 신청한 목록을 조회한다.")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Reservation> getReservationListAll() {

		List<Reservation> ReservationResult = reservationService.getReservationListAll();

		return ReservationResult;
	}

}
