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

		//checking the absence of a new user in the database, user shouldn't be there
		Assertions.assertFalse(listOfUsersBeforeSavingNewUser.contains(user));
		listSizeBeforeSaving = listOfUsersBeforeSavingNewUser.size();

		//saving new user
		userRepository.save(user);

		//user`s list after save
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		//last check for adding a new user to the database
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));

		//checking database size
		listSizeAfterSaving = listOfUsersAfterSavingNewUser.size();
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeAfterSaving);
	}

	@Test
	public void whenCalledDelete_thenDeletingUserCorrectly() {
		User user = new User(NAME, EMAIL);
		long listSizeBeforeSaving;
		long listSizeBeforeDeleting;
		long listSizeAfterDeleting;

		/* taking list users before save, checking the absence of a new user in the database,
		   user shouldn't be there */
		List<User> listOfUsersBeforeSavingUser = userRepository.findAll();
		Assertions.assertFalse(listOfUsersBeforeSavingUser.contains(user));
		listSizeBeforeSaving = listOfUsersBeforeSavingUser.size();

		//saving new user to test delete on it
		userRepository.save(user);

		//checking for adding a new user to the database
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));

		//checking database size
		listSizeBeforeDeleting = listOfUsersAfterSavingNewUser.size();
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeBeforeDeleting);

		//deleting new user
		userRepository.delete(user);

		//checking for deleting a new user from the database
		List<User> listOfUsersAfterDeletingNewUser = userRepository.findAll();
		Assertions.assertFalse(listOfUsersAfterDeletingNewUser.contains(user));

		//checking database size
		listSizeAfterDeleting = listOfUsersAfterDeletingNewUser.size();
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

		/* taking list users before save, checking the absence of a new user in the database,
		   user shouldn't be there */
		List<User> listOfUsersBeforeSavingUser = userRepository.findAll();
		Assertions.assertFalse(listOfUsersBeforeSavingUser.contains(user));
		listSizeBeforeSaving = listOfUsersBeforeSavingUser.size();

		//saving new user to test update on it
		userRepository.save(user);

		//checking the absence of a new user in the database, user shouldn't be there
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));

		//checking the database size change
		listSizeAfterSaving = listOfUsersAfterSavingNewUser.size();
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeAfterSaving);

		//setting new values name and email for our user
		user.setName(NAME);
		user.setEmail(EMAIL);

		//checking the absence of a new user in the database
		Assertions.assertFalse(listOfUsersAfterSavingNewUser.contains(user));

		//saving user after changes
		userRepository.save(user);

		//checking for updating a new user to the database

		List<User> listOfUsersAfterUpdatingUserById = userRepository.findAll();
		Assertions.assertTrue(listOfUsersAfterUpdatingUserById.contains(user));

		// checking database size
		listSizeAfterUpdating = listOfUsersAfterUpdatingUserById.size();
		Assertions.assertEquals(listSizeAfterUpdating, listSizeAfterSaving);
	}
}