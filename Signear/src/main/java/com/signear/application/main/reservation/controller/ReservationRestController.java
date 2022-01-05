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

	@ApiOperation(value = "모든 예약목록을 조회", notes = "센터에 신청된 모든 예약 목록을 조회(긴급건만)")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Reservation> getReservationListAll(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("numberPerPage") int numberPerPage, @RequestParam("area") String area) {

		List<Reservation> ReservationResult = reservationService.getReservationListAll(pageNumber, numberPerPage, area);

		return ReservationResult;
	}

	@ApiOperation(value = "오늘의 예약목록 조회", notes = "센터에 신청된 예약 날짜가 오늘인 예약 목록을 조회한다.")
	@RequestMapping(value = "/todayReservationList", method = RequestMethod.GET)
	public List<Reservation> getTodayReservationListAll(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("numberPerPage") int numberPerPage, @RequestParam("area") String area) {

		List<Reservation> ReservationResult = reservationService.getTodayReservationListAll(pageNumber, numberPerPage,
				area);

		return ReservationResult;
	}

	@ApiOperation(value = "예약 취소", notes = "일반 통역 예약을 취소한다.")
	@RequestMapping(value = "/cancel/{rsid}", method = RequestMethod.POST)
	public void cancelReservation(@PathVariable("rsid") Integer rsid) {

		reservationService.cancel(rsid);

	}

	@ApiOperation(value = "긴급 통역 예약 취소", notes = "긴급 통역 예약을 rsid로 취소한다.")
	@RequestMapping(value = "/cancelEmergency/{rsid}", method = RequestMethod.POST)
	public void cancelEmergencyReservation(@PathVariable("rsid") Integer rsid) {

		reservationService.emergencyCancel(rsid);

	}

	@ApiOperation(value = "예약 거절", notes = "예약정보를 거절한다")
	@RequestMapping(value = "/rejectReservation", method = RequestMethod.POST)
	public void rejectReservation(@RequestBody Reservation reservation) {
		reservation.setStatus(5);
		reservationService.update(reservation);

	}

	@ApiOperation(value = "예약 수정", notes = "청각 장애인의 예약정보를 수정한다.")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Reservation updateReservation(@RequestBody Reservation Reservation) {

		return reservationService.update(Reservation);

	}

	@ApiOperation(value = "예약 상세정보 조회", notes = " reservation_id를 통해 청각장애인의 예약정보를 조회한다.")
	@RequestMapping(value = "/read/{rsid}", method = RequestMethod.GET)
	public Reservation getReservationInfo(@PathVariable("rsid") Integer rsid) {

		Reservation ReservationResult = reservationService.getOneByRsID(rsid);

		return ReservationResult;
	}

}