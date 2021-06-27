package com.signear;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.signear.domain.usercustomer.UserCustomer;
import com.signear.domain.usercustomer.UserCustomerRepository;

@SpringBootTest
class SignearApplicationTests {
	@Autowired
	UserCustomerRepository userCustomerRepository;

	// @Test
	public void customerTest() {
		List<UserCustomer> userCustomerList = userCustomerRepository.findAll();
		for (UserCustomer userCustomer : userCustomerList) {
			System.out.println(userCustomer.toString());
		}
	}

}
