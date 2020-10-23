package com.example.telegrambotinsurance.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.telegrambotinsurance.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
