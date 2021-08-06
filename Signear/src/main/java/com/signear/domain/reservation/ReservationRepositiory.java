package com.signear.domain.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepositiory extends JpaRepository<Reservation, Integer> {
	Reservation findByRsid(Integer rsid);

	List<Reservation> findByAreaAndStatusInAndDateGreaterThan(String address, List<Integer> status, String date);

	@Query(value = "SELECT r FROM Reservation r join fetch r.userCustomer where r.customerid =:customerID and r.date = :date and r.status = 8")
	List<Reservation> findByCustomeridDate(Integer customerID, String date);

	@Query(value = "SELECT r FROM Reservation r join fetch r.userCustomer where r.customerid =:customerID and r.date >= :date")
	List<Reservation> findByCustomerid(Integer customerID, String date);

	@Query(value = "SELECT r FROM Reservation r join fetch r.userSign where r.signid =:signid and r.status in (3, 4) and r.date >=:date")
	List<Reservation> findBySignid(Integer signid, String date);
}
