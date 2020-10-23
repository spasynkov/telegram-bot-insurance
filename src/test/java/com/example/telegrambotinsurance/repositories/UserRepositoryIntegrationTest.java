package com.example.telegrambotinsurance.repositories;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.telegrambotinsurance.entities.User;

@DataJpaTest
public class UserRepositoryIntegrationTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenCalledSave_thenCorrectNumberOfUsers() {
		userRepository.save(new User(0, "Bob", "bob@domain.com"));
		List<User> users = (List<User>) userRepository.findAll();

		Assertions.assertEquals(3, users.size());
	}
}