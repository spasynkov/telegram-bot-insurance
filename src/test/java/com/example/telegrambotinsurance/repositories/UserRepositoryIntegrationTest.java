package com.example.telegrambotinsurance.repositories;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.telegrambotinsurance.entities.User;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    public void fail() {
        Assertions.assertEquals(0, 1);
    }


    @AfterAll
    public static void destroyTestData(@Autowired UserRepository userRepos) {
        System.out.println("Begin destroying test object`s");
        List<User> users = userRepos.findAll();
        for (int i = 0; i < users.size(); i++) {
            try {
                Long.parseLong(users.get(i).getName());
                userRepos.delete(users.get(i));
            } catch (Exception ignored) {
            }
        }
        System.out.println("All test object`s destroyed");
    }

    @Test
    public void whenCalledSave_thenCorrectWriteNewUser() {
        // created user`s field
        String name = String.valueOf(System.currentTimeMillis());
        String email = "veryNice@mail.ru";
        User user = new User(name, email);

        //List users before save
        List<User> userBefore = userRepository.findAll();

        //user`s size field`s
        long sizeBefore = userBefore.size();
        long sizeAfter;

        //action
        if (!userBefore.contains(user) & user.getName() != null || user.getEmail() != null) {
            userRepository.save(user);

            //user`s after save
            List<User> usersAfter = userRepository.findAll();
            sizeAfter = usersAfter.size();

            //assertion
            if (usersAfter.contains(user)) {
                Assertions.assertEquals(sizeBefore + 1, sizeAfter);

            } else {
                System.out.println("This user not saved!");
                fail();
            }
        } else {
            System.out.println("This user already saved or has name==null or email==null!");
            fail();
        }

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
            fail();
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
                    fail();
                }
            } else {
                System.out.println("This user is not saved!");
                fail();
            }
        } else {
            System.out.println("This user is already in the DB!");
            fail();
        }
    }
}