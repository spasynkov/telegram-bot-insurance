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
	private static int deletingCounts = 0;
	@Autowired
	UserRepository userRepository;

	@AfterAll
	public static void deletingTestObjects(@Autowired UserRepository userRepository) {delete(userRepository);}

	private static void delete(@Autowired UserRepository userRepository) {
		List<User> listOfUsers = userRepository.findAll();
		for(User user : listOfUsers) {
			if(user.getEmail().equals("test@example.com")) {
				userRepository.delete(user);
			}
		}
		deletingCounts++;
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

		//checking the absence of a new user in the database, user shouldn't be there
		Assertions.assertFalse(listOfUsersBeforeSavingNewUser.contains(user));

		//saving new user
		userRepository.save(user);

		//user`s list after save
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		listSizeAfterSaving = listOfUsersAfterSavingNewUser.size();

		//last check for adding a new user to the database and  checking database size
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeAfterSaving);
	}

	@Test
	public void whenCalledDelete_thenDeletingUserCorrectly() {
		User user = new User(NAME, EMAIL);
		long listSizeBeforeSaving;
		long listSizeBeforeDeleting;
		long listSizeAfterDeleting;

		//taking list users before save and checking the absence of a new user in the database
		List<User> listOfUsersBeforeSavingUser = userRepository.findAll();
		Assertions.assertFalse(listOfUsersBeforeSavingUser.contains(user));
		listSizeBeforeSaving = listOfUsersBeforeSavingUser.size();

		//saving new user
		userRepository.save(user);

		//checking for adding a new user to the database and checking database size
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		listSizeBeforeDeleting = listOfUsersAfterSavingNewUser.size();
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeBeforeDeleting);

		//deleting new user
		userRepository.delete(user);
		List<User> listOfUsersAfterDeletingNewUser = userRepository.findAll();
		listSizeAfterDeleting = listOfUsersAfterDeletingNewUser.size();

		//checking for deleting a new user from the database and checking database size
		Assertions.assertFalse(listOfUsersAfterDeletingNewUser.contains(user));
		Assertions.assertEquals(listSizeBeforeDeleting - 1, listSizeAfterDeleting);
	}

	@Test
	public void whenCalledUpdate_thenUpdatingCorrectly() {
		String name = String.valueOf(System.currentTimeMillis());
		String email = "masterOfUniverse@deathstar.empire";
		User user = new User(name, email);
		long listSizeBeforeSaving;
		long listSizeAfterSaving;
		long listSizeAfterUpdating;

		//taking list users before save, checking the absence of a new user in the database
		List<User> listOfUsersBeforeSavingUser = userRepository.findAll();
		Assertions.assertFalse(listOfUsersBeforeSavingUser.contains(user));
		listSizeBeforeSaving = listOfUsersBeforeSavingUser.size();

		//saving new user
		userRepository.save(user);

		//checking the absence of a new user in the database and checking the database size change
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
		listSizeAfterSaving = listOfUsersAfterSavingNewUser.size();
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeAfterSaving);

		//setting new values name and email for our user
		user.setName(NAME);
		user.setEmail(EMAIL);

		//checking the absence of a new user in the database
		Assertions.assertFalse(listOfUsersAfterSavingNewUser.contains(user));

		//saving user after changes
		userRepository.save(user);

		//checking for updating a new user to the database and checking database size
		List<User> listOfUsersAfterUpdatingUserById = userRepository.findAll();
		listSizeAfterUpdating = listOfUsersAfterUpdatingUserById.size();
		Assertions.assertTrue(listOfUsersAfterUpdatingUserById.contains(user));
		Assertions.assertEquals(listSizeAfterUpdating, listSizeAfterSaving);
	}
}