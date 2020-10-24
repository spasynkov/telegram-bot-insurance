package com.example.telegrambotinsurance;

import com.example.telegrambotinsurance.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.telegrambotinsurance.repositories.UserRepository;

@SpringBootApplication
public class TelegramBotInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotInsuranceApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository) throws Exception {
		return (String[] args) -> {
			User user1 = new User( "John", "john@domain.com");
//			User user17 = new User( "gogogo", "net@gggg.ru");
			User user18 = new User( "target", "train@ever.com");


			userRepository.save(user18);
			userRepository.save(user1);
			System.out.println("Printing users");
			userRepository.findAll().forEach(System.out::println);
			System.out.println("Finished printing users");
		};
	}

}
