package com.signear.domain.usercustomer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserCustomerRepository extends JpaRepository<UserCustomer, Integer> {
	UserCustomer findByEmail(String email);

	UserCustomer findByCustomerid(Integer customerid);

	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE user_customer u SET u.memo = :memo where u.customerid = :customerid")
	int updateMemoByCustomerid(Integer customerid, String memo);

}
