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

@RestController
@RequestMapping("reservation/customer")
public class ReservationRestController {
	@Autowired
	ReservationService reservationService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Reservation createReservation(@RequestBody Reservation reservation) {

		reservation.setStatus(1);

		return reservationService.create(reservation);

	}

	@RequestMapping(value = "/cancel/{rsID}", method = RequestMethod.POST)
	public Reservation cancelReservation(@PathVariable("rsID") Integer reservation_id) {

		return reservationService.cancel(reservation_id);

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Reservation updateReservation(@RequestBody Reservation Reservation) {

		return reservationService.update(Reservation);

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Reservation getReservationInfo(@RequestParam Integer reservation_id) {

		Reservation ReservationResult = reservationService.getOneByRsID(reservation_id);

		return ReservationResult;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Reservation> getReservationList(@RequestParam Integer customer_id) {

		List<Reservation> ReservationResult = reservationService.getListByCustomerID(customer_id);

		return ReservationResult;
	}
}
