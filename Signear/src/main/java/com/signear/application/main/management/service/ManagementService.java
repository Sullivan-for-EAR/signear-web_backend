package com.signear.application.main.management.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.signear.domain.reservation.Reservation;
import com.signear.domain.reservation.ReservationRepositiory;

@Service
@Transactional
public class ManagementService {

	@Autowired
	private ReservationRepositiory reservationRepository;

	// 1:읽지않음, 2:센터확인중, 3:예약확정, 4.예약취소, 5:예약거절,
	// 6: 통역취소, 7:통역 완료, 8: 긴급통역 연결중, 9: 긴급통역 취소, 10: 긴급통역 승인

	// 6번 : 통역 취소
	// -> 1, 2, 4, 5, 9
	// -> 8번 항목, 오늘 날짜 기준으로 24시간 비교 필요
	// 오늘날짜 + start -24 > 예약 날짜 + start

	// 7번 : 통역 완료
	// -> 3, 10 케이스

	public List<Reservation> getListByArea(Integer pageNumber, Integer numberPerPage, String area) {
		LocalDateTime.now().toString().substring(0, 10).replaceAll("-", "");
		Pageable pageable = PageRequest.of(pageNumber - 1, numberPerPage, Direction.DESC, "rsid");
		List<Reservation> result = reservationRepository.findByArea(pageable, area);

		return result;
	}

	/* 센터 변경 */
	public void updateArea(Integer rsId, String area) {
		reservationRepository.updateAreaByRsId(rsId, area);
	}

	public int getCurrentTime(int gap) {

		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -gap);
		String ysTime = date.format(cal.getTime());
		String hourTime = hour.format(new Date()).replaceAll(":", "").substring(0, 2);

		String finalTime = ysTime + hourTime;

		return Integer.parseInt(finalTime);
	}

}
