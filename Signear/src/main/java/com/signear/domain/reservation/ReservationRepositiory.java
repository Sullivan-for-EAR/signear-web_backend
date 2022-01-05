
package com.signear.domain.reservation;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.signear.application.main.reserverInfo.model.ReserverInfo;

public interface ReservationRepositiory extends JpaRepository<Reservation, Integer> {
	Reservation findByRsid(Integer rsid);

	List<Reservation> findByAddressAndStatusInAndAreaAndDateGreaterThan(String address, List<Integer> status,
			String date, String area);

	List<Reservation> findByArea(Pageable pageable, String area);

	@Query(nativeQuery = true, name = "findReserverInfo")
	List<ReserverInfo> findReserverInfo(Pageable pageable, String area);

	@Query(nativeQuery = true, name = "findReserverInfoByCustomeridAndArea")
	List<ReserverInfo> findReserverInfoByCustomeridAndArea(Integer customerid, String area);

	@Query(value = "SELECT r FROM Reservation r join fetch r.userCustomer where r.customerid =:customerID and r.date = :date and r.status = 8")
	List<Reservation> findByCustomeridDate(Integer customerID, String date);

	@Query(value = "SELECT r FROM Reservation r join fetch r.userCustomer where r.customerid = :customerID and r.date >= :date")
	List<Reservation> findByCustomerid(Integer customerID, String date);

	@Query(value = "SELECT r FROM Reservation r join fetch r.userSign where r.signid =:signid and r.status in (3, 4) and r.date >=:date")
	List<Reservation> findBySignid(Pageable pageable, Integer signid);

	@Query(value = "SELECT r FROM Reservation r join fetch r.userCustomer where r.signid is null and r.date >= :date")
	List<Reservation> findByDateAndSignIdIsNull(String date);

	@Query(value = "SELECT r FROM Reservation r join fetch r.userCustomer where r.signid is null ")
	List<Reservation> findAllSignIdIsNull();

	@Query(value = "SELECT r FROM Reservation r join fetch r.userCustomer where r.type = :type and r.area = :area")
	List<Reservation> findEmergencyRsByTypeAndArea(Pageable pageable, int type, String area);

	@Query(value = "SELECT r FROM Reservation r join fetch r.userCustomer where r.date = :date and r.type = :type and r.area = :area")
	List<Reservation> findEmergencyRsByDateAndTypeAndArea(Pageable pageable, String date, int type, String area);

	@Query(value = "SELECT r FROM Reservation r join fetch r.userCustomer where r.date = :date and r.type in (:status)")
	List<Reservation> findEmergencyRsByDateAndStatus(Pageable pageable, String date, List<Integer> status);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Reservation r SET r.area = :area where r.rsid = :rsId")
	int updateAreaByRsId(Integer rsId, String area);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Reservation r SET r.status = :status where r.rsid = :rsId")
	int updateStatusByRsid(Integer status, Integer rsId);

}