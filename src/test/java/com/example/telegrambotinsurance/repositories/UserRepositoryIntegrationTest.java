package com.example.telegrambotinsurance.repositories;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.telegrambotinsurance.entities.User;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserRepositoryIntegrationTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryIntegrationTest.class);
	@Autowired
	private UserRepository userRepository;

	@AfterAll
	public static void deletingTestObjects(@Autowired UserRepository userRepository) {
		int deletingCounts = 0;
		List<User> listOfUsers = userRepository.findAll();
		for (User user : listOfUsers) {
			if (user.getEmail().equals("test@example.com")) {
				userRepository.delete(user);
				deletingCounts++;
			}
		}
		LOGGER.debug("Count of deleting operations  after all tests: " + deletingCounts);
	}

	@AfterEach
	public void deletingTestObjectsAfterMethod() {
		int deletingCounts = 0;
		List<User> listOfUsers = userRepository.findAll();
		for (User user : listOfUsers) {
			if (user.getEmail().equals("test@example.com")) {
				userRepository.delete(user);
				deletingCounts++;
			}
		}
		LOGGER.debug("Count of deleting operations: " + deletingCounts);
	}

	@Test
	public void whenCalledSave_thenNewUserSavedCorrectly() {
		String name = String.valueOf(System.currentTimeMillis());
		String email = "test@example.com";
		User user = new User(name, email);
		long listSizeBeforeSaving;
		long listSizeAfterSaving;

		//List users before save
		List<User> listOfUsersBeforeSavingNewUser = userRepository.findAll();

		//user`s size field`s
		listSizeBeforeSaving = listOfUsersBeforeSavingNewUser.size();

		//saving new user
		Assertions.assertFalse(listOfUsersBeforeSavingNewUser.contains(user));
		userRepository.save(user);

		//user`s after save
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		listSizeAfterSaving = listOfUsersAfterSavingNewUser.size();

		//assertion
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeAfterSaving);
	}

	@Test
	public void whenCalledDelete_thenDeletingUserCorrectly() {
		String name = String.valueOf(System.currentTimeMillis());
		String email = "twoBeerOrNotTwoBeer@drunkard.ru";
		User user = new User(name, email);
		long listSizeBeforeSaving;
		long listSizeBeforeDeleting;
		long listSizeAfterDeleting;

		// taking data list
		List<User> listOfUsersBeforeSavingUser = userRepository.findAll();
		Assertions.assertFalse(listOfUsersBeforeSavingUser.contains(user));
		listSizeBeforeSaving = listOfUsersBeforeSavingUser.size();

		//saving new user
		userRepository.save(user);


		//taking data list size
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		listSizeBeforeDeleting = listOfUsersAfterSavingNewUser.size();
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeBeforeDeleting);

		//deleting new user
		userRepository.delete(user);
		List<User> listOfUsersAfterDeletingNewUser = userRepository.findAll();
		listSizeAfterDeleting = listOfUsersAfterDeletingNewUser.size();

		Assertions.assertFalse(listOfUsersAfterDeletingNewUser.contains(user));
		Assertions.assertEquals(listSizeBeforeDeleting - 1, listSizeAfterDeleting);
	}

	@Test
	public void whenCalledUpdate_thenUpdatingCorrectly() throws InterruptedException {
		String name = String.valueOf(System.currentTimeMillis());
		String email = "masterOfUniverse@deathstar.empire";
		User user = new User(name, email);
		Long userId;
		long listSizeBeforeSaving;
		long listSizeAfterSaving;
		long listSizeAfterUpdating;

		// taking data list
		List<User> listOfUsersBeforeSavingUser = userRepository.findAll();
		Assertions.assertFalse(listOfUsersBeforeSavingUser.contains(user));
		listSizeBeforeSaving = listOfUsersBeforeSavingUser.size();

		// taking an element by id
		userRepository.save(user);
		userId = user.getId();
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		listSizeAfterSaving = listOfUsersAfterSavingNewUser.size();
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeAfterSaving);

		//delay for unique variable "name"
		Thread.sleep(100);

		// create new user for update
		String nameForUpdate = String.valueOf(System.currentTimeMillis());
		String emailForUpdate = "twoBeerOrNotTwoBeer@drunkard.ru";
		User userForUpdate = new User(userId, nameForUpdate, emailForUpdate);
		Assertions.assertFalse(listOfUsersAfterSavingNewUser.contains(userForUpdate));
		userRepository.save(userForUpdate);

		//checking for a match
		List<User> listOfUsersAfterUpdatingUserById = userRepository.findAll();
		listSizeAfterUpdating = listOfUsersAfterUpdatingUserById.size();

		Assertions.assertTrue(listOfUsersAfterUpdatingUserById.contains(userForUpdate));
		Assertions.assertEquals(listSizeAfterUpdating, listSizeAfterSaving);
	}
}