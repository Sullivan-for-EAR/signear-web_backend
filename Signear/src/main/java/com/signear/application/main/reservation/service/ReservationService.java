package com.signear.application.main.reservation.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.signear.application.exception.ApiException;
import com.signear.application.exception.ExceptionEnum;
import com.signear.domain.reservation.Reservation;
import com.signear.domain.reservation.ReservationRepositiory;

@Service
@Transactional
public class ReservationService {
	@Autowired
	ReservationRepositiory reservationRepositiory;

//	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

	public void cancel(Integer rsid) {
		reservationRepositiory.updateStatusByRsid(4, rsid);
	}

	public void emergencyCancel(Integer rsid) {
		reservationRepositiory.updateStatusByRsid(9, rsid);
	}

	public Reservation getOneByRsID(Integer rsID) {
		return reservationRepositiory.findByRsid(rsID);
	}

	public List<Reservation> getReservationListAll(int pageNumber, int numberPerPage, String area) {
		Pageable pageable = PageRequest.of(pageNumber - 1, numberPerPage, Direction.DESC, "rsid");
		return reservationRepositiory.findEmergencyRsByTypeAndArea(pageable, 2, area);
	}

	public List<Reservation> getTodayReservationListAll(int pageNumber, int numberPerPage, String area) {
		Pageable pageable = PageRequest.of(pageNumber - 1, numberPerPage, Direction.DESC, "rsid");
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return reservationRepositiory.findEmergencyRsByDateAndTypeAndArea(pageable, dateFormat.format(new Date()), 2,
				area);
	}

}
