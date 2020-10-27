package com.example.telegrambotinsurance.entities;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;

	// create new user without id
	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}
}