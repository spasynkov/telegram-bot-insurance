package com.example.telegrambotinsurance;

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
			/*User user1 = new User(1, "John", "john@domain.com");
			User user2 = new User(2, "Julie", "julie@domain.com");
			userRepository.save(user1);
			userRepository.save(user2);*/
			System.out.println("Printing users");
			userRepository.findAll().forEach(System.out::println);
			System.out.println("Finished printing users");
		};
	}

}
