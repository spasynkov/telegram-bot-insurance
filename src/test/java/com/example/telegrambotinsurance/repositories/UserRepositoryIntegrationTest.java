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
		List<User> users = (List<User>) userRepository.findAll();
		long size = users.size();
		userRepository.save(new User( "Bob", "bob@domain.com"));

		Assertions.assertEquals(size, users.size());
	}
}