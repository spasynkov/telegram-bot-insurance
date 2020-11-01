package com.example.telegrambotinsurance.entities;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // create new user without id
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}