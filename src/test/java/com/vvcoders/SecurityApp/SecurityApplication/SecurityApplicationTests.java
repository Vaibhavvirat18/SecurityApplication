package com.vvcoders.SecurityApp.SecurityApplication;

import com.vvcoders.SecurityApp.SecurityApplication.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.vvcoders.SecurityApp.SecurityApplication.entities.Users;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {

		Users user= new Users(4L, "vaibhavvirat18@gmail.com", "1234");

		String token = jwtService.generateToken(user);

		System.out.println(token);

		System.out.println(jwtService.getUserIdFromToken(token));


	}

}
