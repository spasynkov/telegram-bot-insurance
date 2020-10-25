package com.example.telegrambotinsurance.repositories;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.telegrambotinsurance.entities.User;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenCalledSave_thenCorrectNumberOfUsers() {
        // taking data size
        List<User> users = (List<User>) userRepository.findAll();

        // incrementing data size
        long sizeBefore = users.size() + 1;

        //writing a new element
        User user = new User("Bob", "bob@domain.com");
        userRepository.save(user);

        //taking a new data size
        List<User> usersAfter = (List<User>) userRepository.findAll();
        long sizeAfter = usersAfter.size();

        // size comparison
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void whenCalledDelete_thenCorrectNumberOfUsers() {
        // taking data size
        List<User> users = (List<User>) userRepository.findAll();

        // decrementing data size
        long sizeBefore = users.size() - 1;

        // deleting an element
        userRepository.deleteById(sizeBefore);

        //taking a new data size
        List<User> usersAfter = (List<User>) userRepository.findAll();
        long sizeAfter = usersAfter.size();

        // size comparison
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void whenCalledUpdate_thenCorrectElementChanges() {
        // taking data list
        List<User> users = (List<User>) userRepository.findAll();

        // taking an element by id
        long idUser = users.size() - 1;
        Optional<User> userBefore = userRepository.findById(idUser);

        // updating an element
        User user = new User(idUser, "someName", "some@email.com");
        userRepository.save(user);

        //taking a new element by this id
        List<User> usersAfter = (List<User>) userRepository.findAll();
        Optional<User> userAfter = userRepository.findById(idUser);

        // element`s comparison
        Assertions.assertNotEquals(userBefore, userAfter);
    }
}