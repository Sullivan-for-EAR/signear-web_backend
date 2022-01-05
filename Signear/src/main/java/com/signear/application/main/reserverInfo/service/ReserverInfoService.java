package com.signear.application.main.reserverInfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.signear.application.exception.ApiException;
import com.signear.application.exception.ExceptionEnum;
import com.signear.application.main.reserverInfo.model.ReserverInfoModel;
import com.signear.domain.reservation.ReservationRepositiory;
import com.signear.domain.usercustomer.UserCustomer;
import com.signear.domain.usercustomer.UserCustomerRepository;

@Service
@Transactional
public class ReserverInfoService {

	@Autowired
	private UserCustomerRepository userCustomerRepository;
	@Autowired
	private ReservationRepositiory reservationRepositiory;

	// 1:읽지않음, 2:센터확인중, 3:예약확정, 4.예약취소, 5:예약거절,
	// 6: 통역취소, 7:통역 완료, 8: 긴급통역 연결중, 9: 긴급통역 취소, 10: 긴급통역 승인

	// 6번 : 통역 취소
	// -> 1, 2, 4, 5, 9
	// -> 8번 항목, 오늘 날짜 기준으로 24시간 비교 필요
	// 오늘날짜 + start -24 > 예약 날짜 + start

	// 7번 : 통역 완료
	// -> 3, 10 케이스
	/* 사용자 정보 목록 조회 */
	public ReserverInfoModel getList(int pageNumber, int numberPerPage, String area) {
		ReserverInfoModel model = new ReserverInfoModel();
		Pageable pageable = PageRequest.of(pageNumber - 1, numberPerPage, Direction.DESC, "customerid");
		model.setReserverInfoList(reservationRepositiory.findReserverInfo(pageable, area));
		return model;
	}

	public ReserverInfoModel getReserverInfo(int customerid, String area) {
		ReserverInfoModel model = new ReserverInfoModel();
		model.setReserverInfoList(reservationRepositiory.findReserverInfoByCustomeridAndArea(customerid, area));
		return model;
	}

	/* 메모 수정 */
	public void updateMemo(Integer customerid, String memo) {
		if (userCustomerRepository.findByCustomerid(customerid) == null) {
			throw new ApiException(ExceptionEnum.SECURITY_02);
		}

		userCustomerRepository.updateMemoByCustomerid(customerid, memo);
	}

	/* 메모 삭제 */
	public void deleteMemo(Integer customerid) {
		UserCustomer userCustomer = userCustomerRepository.findByCustomerid(customerid);
		if (userCustomer == null) {
			throw new ApiException(ExceptionEnum.SECURITY_02);
		}
		userCustomerRepository.updateMemoByCustomerid(customerid, null);
	}

	/* 메모 조회 */
	public UserCustomer getMemo(Integer customerid) {
		if (userCustomerRepository.findByCustomerid(customerid) == null) {
			throw new ApiException(ExceptionEnum.SECURITY_02);
		}

		return userCustomerRepository.findByCustomerid(customerid);
	}
}
