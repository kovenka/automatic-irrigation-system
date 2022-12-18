package com.assessment.irrigation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class IrrigationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrrigationApplication.class, args);
	}

}
