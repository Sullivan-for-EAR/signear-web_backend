package com.signear.application.main.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@ApiOperation(value = "센터의 예약목록 조회", notes = "센터명으로 예약 목록을 조회한다.")
	@RequestMapping(value = "/reservation/list", method = RequestMethod.GET)
	public List<Reservation> readSignList(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("numberPerPage") int numberPerPage, @RequestParam("area") String area) {

		List<Reservation> ReservationResult = managementService.getListByArea(pageNumber, numberPerPage, area);

		return ReservationResult;
	}

	@ApiOperation(value = "센터변경", notes = "센터를 변경한다.")
	@RequestMapping(value = "/reservation/updateCenter/{rsid}", method = RequestMethod.POST)
	public void updateCenter(@RequestParam("rsid") Integer rsid, @RequestParam("area") String area) {

		managementService.updateArea(rsid, area);

	}

}
