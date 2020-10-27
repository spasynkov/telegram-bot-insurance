package com.example.telegrambotinsurance.repositories;

import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.telegrambotinsurance.entities.User;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @After("whenCalledUpdate_thenCorrectElementChanges()")
    public void destroyTestData() {
        System.out.println("Test finished");
        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            try {
                Long.parseLong(users.get(i).getName());
                userRepository.delete(users.get(i));
            } catch (Exception ignored) {
            }
        }
        System.out.println("Test DONE");
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
                Assertions.assertEquals(0, 1);
            }
        } else {
            System.out.println("This user already saved or has name==null or email==null!");
            Assertions.assertEquals(0, 1);
        }

    }

    @Test
    public void whenCalledDelete_thenCorrectNumberOfUsers() {
        // taking data size
        List<User> usersBefore = userRepository.findAll();
        long sizeBefore = usersBefore.size();
        long userId = usersBefore.get(((int) sizeBefore - 1)).getId() - 1;

        // deleting an element
        userRepository.deleteById(userId);

        //taking a new data size
        List<User> usersAfter = userRepository.findAll();
        long sizeAfter = usersAfter.size();

        // size comparison
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
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
        userRepository.save(user);

        //taking a new element by this id
        Optional<User> userAfter = userRepository.findById(userId);

        // element`s comparison
        if (userRepository.findAll().contains(user)) {
            Assertions.assertNotEquals(userBefore, userAfter);
        } else {
            Assertions.assertEquals(0, 1);
        }
    }
}