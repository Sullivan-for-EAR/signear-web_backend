package com.signear.domain.usercustomer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCustomerRepository extends JpaRepository<UserCustomer, Integer> {
	UserCustomer findByEmail(String email);

}
