package com.vvcoders.SecurityApp.SecurityApplication;

import com.vvcoders.SecurityApp.SecurityApplication.entities.Users;
import com.vvcoders.SecurityApp.SecurityApplication.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {

		Users user= new Users(4L, "vaibhavvirat18@gmail.com", "1234", "vaibhav");

		String token = jwtService.generateAccessToken(user);

		System.out.println(token);

		System.out.println(jwtService.getUserNameFromToken(token));


	}

}
