package com.example.telegrambotinsurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TelegramBotInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotInsuranceApplication.class, args);
	}

}
