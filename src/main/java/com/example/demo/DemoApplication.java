package com.example.demo;

import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;
import com.example.demo.validator.DateValidator;
import com.example.demo.validator.DateValidatorUsingLocalDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class DemoApplication {

	private static CustomerService customerService;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
