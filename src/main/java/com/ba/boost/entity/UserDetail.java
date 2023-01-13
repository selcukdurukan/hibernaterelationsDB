package com.ba.boost.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "user")

@Entity
@Table(name = "users_details")
public class UserDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "biography")
	private String biography;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;
	
	@Lob
	@Column(name = "picture", nullable = true)
	private byte[] picture;
	
	@OneToOne(mappedBy = "userDetail")
	private User user;

	public UserDetail(String firstName, String lastName, String biography, Gender gender, byte[] picture) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.biography = biography;
		this.gender = gender;
		this.picture = picture;
	}

	public UserDetail(String firstName, String lastName, String biography, Gender gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.biography = biography;
		this.gender = gender;
	}
	
	
	
	
	

}
