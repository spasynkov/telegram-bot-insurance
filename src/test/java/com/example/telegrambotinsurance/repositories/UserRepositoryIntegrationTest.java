package com.example.telegrambotinsurance.repositories;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.telegrambotinsurance.entities.User;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserRepositoryIntegrationTest {
	private Logger loggerFactory = LoggerFactory.getLogger(UserRepositoryIntegrationTest.class);
	private int remoteUserCounter;


	@Autowired
	private UserRepository userRepository;

	@AfterAll
	public static void deleteTestObjects(@Autowired UserRepository userRepository) {

		List<User> listOfUsers = userRepository.findAll();
		for (User listOfUser : listOfUsers) {
			try {
				Long.parseLong(listOfUser.getName());
				userRepository.delete(listOfUser);
			} catch (Exception ignored) {

			}
		}

	}

	@Test
	public void whenCalledSave_thenNewUserSavedCorrectly() {
		// created user`s field
		String name = String.valueOf(System.currentTimeMillis());
		String email = "veryNice@mail.ru";
		User user = new User(name, email);
		long listSizeBefore;
		long listSizeAfter;

		//List users before save
		List<User> listOfUsersBeforeSavingNewUser = userRepository.findAll();

		//user`s size field`s
		listSizeBefore = listOfUsersBeforeSavingNewUser.size();


		//action
		Assertions.assertFalse(listOfUsersBeforeSavingNewUser.contains(user));
		userRepository.save(user);

		//user`s after save
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		listSizeAfter = listOfUsersAfterSavingNewUser.size();

		//assertion
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
		Assertions.assertEquals(listSizeBefore + 1, listSizeAfter);

	}



	@Test
	public void whenCalledDelete_thenCorrectNumberOfUsers() {
		// taking data size and denying object
		List<User> usersBefore = userRepository.findAll();
		long sizeBefore = usersBefore.size();
		User denyingUser = usersBefore.get(((int) sizeBefore - 1));
		long userId = denyingUser.getId();

		// deleting an element
		userRepository.deleteById(userId);

		//taking a new data size
		List<User> usersAfter = userRepository.findAll();
		long sizeAfter = usersAfter.size();

		// size comparison
		if (!usersAfter.contains(denyingUser)) {
			Assertions.assertEquals(sizeBefore - 1, sizeAfter);
		} else {
			System.out.println("Deleting false!");

		}
	}

	@Test
	public void whenCalledUpdate_thenCorrectElementChanges() {

		String name = String.valueOf(System.currentTimeMillis());
		String email = "some@email.com";

		// taking data list
		List<User> usersBefore = userRepository.findAll();

		// taking an element by id
		long sizeBefore = usersBefore.size();
		long userId = usersBefore.get(((int) sizeBefore - 1)).getId();
		Optional<User> userBefore = userRepository.findById(userId);

		// updating an element
		User user = new User(userId, name, email);

		if (!usersBefore.contains(user)) {
			userRepository.save(user);
			List<User> usersAfter = userRepository.findAll();
			long sizeAfter = usersAfter.size();

			if (usersAfter.contains(user)) {

				//taking a new element by this id
				Optional<User> userAfter = userRepository.findById(userId);

				// element`s comparison
				if (sizeBefore == sizeAfter) {
					Assertions.assertNotEquals(userBefore, userAfter);
				} else {
					System.out.println("Updating false: not update this id");

				}
			} else {
				System.out.println("This user is not saved!");

			}
		} else {
			System.out.println("This user is already in the DB!");

		}
	}
}