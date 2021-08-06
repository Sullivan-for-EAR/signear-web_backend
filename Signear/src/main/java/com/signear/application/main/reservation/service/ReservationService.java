package com.signear.application.main.reservation.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.signear.application.main.exception.ApiException;
import com.signear.application.main.exception.ExceptionEnum;
import com.signear.domain.reservation.Reservation;
import com.signear.domain.reservation.ReservationRepositiory;

@Service
@Transactional
public class ReservationService {
	@Autowired
	ReservationRepositiory reservationRepositiory;

//	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Reservation> findAll() {
		return reservationRepositiory.findAll();
	}

	public List<Reservation> findAllSignReservation(String address) {

		List<Integer> status = new ArrayList<>();
		status.add(1); // 1:읽지않음
		status.add(2); // 2:센터확인중

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		String date = dateFormat.format(cal.getTime());

		return reservationRepositiory.findByAreaAndStatusInAndDateGreaterThan(address, status, date);
	}

	public Optional<Reservation> findOne(Integer Reservation_id) {
		return reservationRepositiory.findById(Reservation_id);
	}

	public Reservation update(Reservation Reservation) {
		return reservationRepositiory.save(Reservation);
	}

	public void delete(Integer Reservation_id) {
		reservationRepositiory.deleteById(Reservation_id);
	}

	public Reservation create(Reservation reservation) {

		Integer customerID = reservation.getUserCustomer().getCustomerid();
		String date = LocalDateTime.now().toString().substring(0, 10).replaceAll("-", "");
		List<Reservation> reservationList = reservationRepositiory.findByCustomeridDate(customerID, date);

		if (reservationList.size() > 0) {
			throw new ApiException(ExceptionEnum.SECURITY_03);
		}

		return reservationRepositiory.save(reservation);
	}

	public Reservation cancel(Integer Reservation_id) {
		Reservation reservationMap = reservationRepositiory.findById(Reservation_id).get();
		reservationMap.setStatus(4); // 4.예약취소

		return reservationRepositiory.save(reservationMap);
	}

	public Reservation emergencyCancel(Integer Reservation_id) {
		Reservation reservationMap = reservationRepositiory.findById(Reservation_id).get();
		reservationMap.setStatus(9); // 9: 긴급통역 취소

		return reservationRepositiory.save(reservationMap);
	}

	public Reservation getOneByRsID(Integer rsID) {
		return reservationRepositiory.findByRsid(rsID);
	}

	public List<Reservation> getListByCustomerID(Integer customerID) {
		String localTime = LocalDateTime.now().toString().substring(0, 10).replaceAll("-", "");
		return reservationRepositiory.findByCustomerid(customerID, localTime);
	}

	public List<Reservation> getListBySignID(Integer signID) {
		String localTime = LocalDateTime.now().toString().substring(0, 10).replaceAll("-", "");
		return reservationRepositiory.findBySignid(signID, localTime);
	}

}
