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
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryIntegrationTest.class);
	@Autowired
	private UserRepository userRepository;


//	@AfterAll
//	public static void deleteTestObjects(@Autowired UserRepository userRepository) {
//
//		List<User> listOfUsers = userRepository.findAll();
//		for (User listOfUser : listOfUsers) {
//			try {
//				Long.parseLong(listOfUser.getName());
//				userRepository.delete(listOfUser);
//			} catch (Exception ignored) {
//
//			}
//		}
//
//	}

	@Test
	public void whenCalledSave_thenNewUserSavedCorrectly() {
		String name = String.valueOf(System.currentTimeMillis());
		String email = "wonderful@email.ru";
		User user = new User(name, email);
		long listSizeBeforeSaving;
		long listSizeAfterSaving;

		//List users before save
		List<User> listOfUsersBeforeSavingNewUser = userRepository.findAll();

		//user`s size field`s
		listSizeBeforeSaving = listOfUsersBeforeSavingNewUser.size();
		for (User userList : listOfUsersBeforeSavingNewUser
		) {
			LOGGER.debug("BEFORE SAVING: " + userList);
		}

		//saving new user
		Assertions.assertFalse(listOfUsersBeforeSavingNewUser.contains(user));
		userRepository.save(user);

		//user`s after save
		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
		listSizeAfterSaving = listOfUsersAfterSavingNewUser.size();
		for (User userList : listOfUsersAfterSavingNewUser
		) {
			LOGGER.debug("AFTER SAVING: " + userList);
		}

		//assertion
		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
		Assertions.assertEquals(listSizeBeforeSaving + 1, listSizeAfterSaving);
	}

	@Test
	public void whenCalledDelete_thenCorrectNumberOfUsers() {
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
		for (User userList : listOfUsersAfterSavingNewUser
		) {
			LOGGER.debug("AFTER SAVING: " + userList);
		}

		//deleting new user
		userRepository.delete(user);
		List<User> listOfUsersAfterDeletingNewUser = userRepository.findAll();
		listSizeAfterDeleting = listOfUsersAfterDeletingNewUser.size();
		for (User userList : listOfUsersAfterDeletingNewUser
		) {
			LOGGER.debug("AFTER DELETING: " + userList);
		}

		Assertions.assertFalse(listOfUsersAfterDeletingNewUser.contains(user));
		Assertions.assertEquals(listSizeBeforeDeleting - 1, listSizeAfterDeleting);

	}

//	@Test
//	public void whenCalledUpdate_thenCorrectElementChanges() {
//
//		name = String.valueOf(System.currentTimeMillis());
//		email = "some@email.com";
//		User userForUpdate = new User(name, email);
//
//		// taking data list
//		List<User> listOfUsersBeforeUpdatingUser = userRepository.findAll();
//		Assertions.assertFalse(listOfUsersBeforeUpdatingUser.contains(userForUpdate));
//
//		// taking an element by id
//		listSizeBefore = listOfUsersBeforeUpdatingUser.size();
//		userRepository.save(user);
//		userId = user.getId();
//
//		List<User> listOfUsersAfterSavingNewUser = userRepository.findAll();
//		Assertions.assertTrue(listOfUsersAfterSavingNewUser.contains(user));
//		Assertions.assertFalse(listOfUsersAfterSavingNewUser.contains(userForUpdate));
//
//
//
//		if (!usersBefore.contains(user)) {
//			userRepository.save(user);
//
//			long sizeAfter = usersAfter.size();
//
//			if (usersAfter.contains(user)) {
//
//				//taking a new element by this id
//				Optional<User> userAfter = userRepository.findById(userId);
//
//				// element`s comparison
//				if (sizeBefore == sizeAfter) {
//					Assertions.assertNotEquals(userBefore, userAfter);
//				} else {
//					System.out.println("Updating false: not update this id");
//
//				}
//			} else {
//				System.out.println("This user is not saved!");
//
//			}
//		} else {
//			System.out.println("This user is already in the DB!");
//
//		}
//	}
}