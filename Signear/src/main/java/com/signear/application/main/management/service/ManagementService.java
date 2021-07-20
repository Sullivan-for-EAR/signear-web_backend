package com.signear.application.main.management.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.signear.application.main.management.Model.ManagementModel;
import com.signear.domain.reservation.Reservation;
import com.signear.domain.reservation.ReservationRepositiory;
import com.signear.domain.reservationhistory.ReservationHistory;
import com.signear.domain.reservationhistory.ReservationHistoryRepository;

@Service
@Transactional
public class ManagementService {

	@Autowired
	private ReservationRepositiory reservationRepository;

	@Autowired
	private ReservationHistoryRepository reservationHistoryRepository;

	// 1:읽지않음, 2:센터확인중, 3:예약확정, 4.예약취소, 5:예약거절,
	// 6: 통역취소, 7:통역 완료, 8: 긴급통역 연결중, 9: 긴급통역 취소, 10: 긴급통역 승인

	// 6번 : 통역 취소
	// -> 1, 2, 4, 5, 9
	// -> 8번 항목, 오늘 날짜 기준으로 24시간 비교 필요
	// 오늘날짜 + start -24 > 예약 날짜 + start

	// 7번 : 통역 완료
	// -> 3, 10 케이스

	public List<Reservation> getListByCustomerID(Integer customerID) {
		String localTime = LocalDateTime.now().toString().substring(0, 10).replaceAll("-", "");
		List<Reservation> customerManagementList = reservationRepository.findByCustomerID(customerID, localTime);
		List<Reservation> customerManagementReturnList = this.getList(customerManagementList);

		return customerManagementReturnList;
	}

	public List<Reservation> getListBySignID(Integer signID) {
		String localTime = LocalDateTime.now().toString().substring(0, 10).replaceAll("-", "");
		List<Reservation> signManagementList = reservationRepository.findBySignID(signID, localTime);
		List<Reservation> signManagementReturnList = this.getList(signManagementList);

		return signManagementReturnList;
	}

	public void delete(Integer Reservation_id) {
		reservationRepository.deleteById(Reservation_id);
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

	public List<Reservation> getList(List<Reservation> ManagementList) {

		List<Reservation> returnList = new ArrayList<Reservation>();

		for (Reservation management : ManagementList) {

			// ManagementModel managementModel = this.getManagementMapper(management);
			ReservationHistory reservationHistory = new ReservationHistory();
			reservationHistory.setRsID(management.getRsid());
			reservationHistory.setFromStatus(management.getStatus());

			int status = management.getStatus();
			if (status == 1 || status == 2 || status == 4 || status == 5 || status == 9) {
				management.setStatus(6);
				reservationHistory.setToStatus(6);
				reservationHistoryRepository.save(reservationHistory);
			}

			if (status == 8) {
				int currenTime = this.getCurrentTime(1);
				int myDate = Integer.parseInt(
						management.getDate().replaceAll("-", "") + management.getStart_time().substring(0, 2));

				if (currenTime > myDate) {
					management.setStatus(6);
					reservationHistory.setToStatus(6);
					reservationHistoryRepository.save(reservationHistory);
				}

			}

			if (status == 3 || status == 10) {
				management.setStatus(7);
				reservationHistory.setToStatus(7);
				reservationHistoryRepository.save(reservationHistory);
			}

			if (management.getStatus() != 8) {
				returnList.add(management);
			}
		}

		return returnList;
	}

	public ManagementModel getManagementMapper(Reservation management) {

		ManagementModel managementModel = new ManagementModel();

		managementModel.setRsID(management.getRsid());
		managementModel.setDate(management.getDate());
		managementModel.setStart_time(management.getStart_time());
		managementModel.setEnd_time(management.getEnd_time());
		managementModel.setArea(management.getArea());
		managementModel.setAddress(management.getAddress());
		managementModel.setMethod(management.getMethod());
		managementModel.setStatus(management.getStatus());
		managementModel.setType(management.getType());
		managementModel.setRequest(management.getRequest());
		managementModel.setReject(management.getReject());

		return managementModel;
	}
}
