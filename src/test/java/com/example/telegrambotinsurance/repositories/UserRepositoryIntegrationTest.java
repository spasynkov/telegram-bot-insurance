package com.example.telegrambotinsurance.repositories;

import com.example.telegrambotinsurance.entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class UserRepositoryIntegrationTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryIntegrationTest.class);
	private static final String NAME = "testUser";
	private static final String EMAIL = "test@example.com";
	@Autowired
	UserRepository userRepository;

	@AfterAll
	public static void deletingTestObjects(@Autowired UserRepository userRepository) {delete(userRepository);}

	private static void delete(@Autowired UserRepository userRepository) {
		int deletingCounts = 0;
		List<User> listOfUsers = userRepository.findAll();
		for(User user : listOfUsers) {
			if(user.getEmail().equals("test@example.com")) {
				userRepository.delete(user);
				deletingCounts++;
			}
		}
		LOGGER.debug("Count of deleting operations  after test: " + deletingCounts);
	}

	@AfterEach
	public void deletingTestObjectsAfterMethod(@Autowired UserRepository userRepository) {delete(userRepository);}

	@Test
	public void whenCalledSave_thenNewUserSavedCorrectly() {
		User user = new User(NAME, EMAIL);
		long listSizeBeforeSaving;
		long listSizeAfterSaving;

		//taking list users before save
		List<User> listOfUsersBeforeSavingNewUser = userRepository.findAll();
		listSizeBeforeSaving = listOfUsersBeforeSavingNewUser.size();

		//saving new user
		Assertions.assertFalse(listOfUsersBeforeSavingNewUser.contains(user));
		userRepository.save(user);

		//user`s after save
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		listSizeAfterSaving = listOfUsersAfterSavingNewUser.size();

		//last check for adding a new user to the database
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeAfterSaving);
	}

	@Test
	public void whenCalledDelete_thenDeletingUserCorrectly() {
		User user = new User(NAME, EMAIL);
		long listSizeBeforeSaving;
		long listSizeBeforeDeleting;
		long listSizeAfterDeleting;

		//taking list users before save
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

	//	@Test
	//	public void whenCalledUpdate_thenUpdatingCorrectly() {
	//		String name = String.valueOf(System.currentTimeMillis());
	//		String email = "masterOfUniverse@deathstar.empire";
	//		User user = new User(name, email);
	//		Long userId;
	//		long listSizeBeforeSaving;
	//		long listSizeAfterSaving;
	//		long listSizeAfterUpdating;
	//
	//		// taking data list
	//		List<User> listOfUsersBeforeSavingUser = userRepository.findAll();
	//		Assertions.assertFalse(listOfUsersBeforeSavingUser.contains(user));
	//		listSizeBeforeSaving = listOfUsersBeforeSavingUser.size();
	//
	//		// taking an element by id
	//		userRepository.save(user);
	//		userId = user.getId();
	//		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
	//		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
	//		listSizeAfterSaving = listOfUsersAfterSavingNewUser.size();
	//		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeAfterSaving);
	//
	//
	//
	//		// create new user for update
	//
	//
	//		Assertions.assertFalse(listOfUsersAfterSavingNewUser.contains(userForUpdate));
	//		userRepository.save(userForUpdate);
	//
	//		//checking for a match
	//		List<User> listOfUsersAfterUpdatingUserById = userRepository.findAll();
	//		listSizeAfterUpdating = listOfUsersAfterUpdatingUserById.size();
	//
	//		Assertions.assertTrue(listOfUsersAfterUpdatingUserById.contains(userForUpdate));
	//		Assertions.assertEquals(listSizeAfterUpdating, listSizeAfterSaving);
	//	}
}