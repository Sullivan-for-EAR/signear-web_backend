package com.signear.application.main.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.signear.application.main.management.service.ManagementService;
import com.signear.domain.reservation.Reservation;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("management")
public class ManagementRestController {

	@Autowired
	ManagementService managementService;

	@ApiOperation(value = "통역사의 예약정보 목록 조회", notes = "통역사가 예약확정, 예약 취소 한 예약 목록을 조회한다.")
	@RequestMapping(value = "/reservation/list", method = RequestMethod.GET)
	public List<Reservation> readSignList(@RequestParam Integer sign_id) {

		List<Reservation> ReservationResult = managementService.getListBySignID(sign_id);

		return ReservationResult;
	}

	@ApiOperation(value = "예약 삭제", notes = "reservation_id 로 예약을 삭제한다.")
	@RequestMapping(value = "/delete/{rsid}", method = RequestMethod.POST)
	public void deleteReservation(@PathVariable("rsid") Integer reservation_id) {

		managementService.delete(reservation_id);

	}

}
