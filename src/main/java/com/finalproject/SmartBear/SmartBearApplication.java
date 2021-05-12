package com.finalproject.SmartBear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;

import static com.finalproject.SmartBear.constant.FileConstant.USER_FOLDER;

@SpringBootApplication
public class SmartBearApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartBearApplication.class, args);
		new File(USER_FOLDER).mkdirs();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
