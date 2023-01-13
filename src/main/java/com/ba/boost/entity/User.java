package com.ba.boost.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "created_on")
	private Date createdOn;
	
	/*
	 * CASCADE ALL ÇÜNKÜ BİR USER SİLDİGİMİZDE ONE-TO-ONE İLİŞKİDE ONUN DETAŞ BİLGİSİNİN DE SİLİNMESİNİ İSTERİZ.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_detail_id", referencedColumnName = "id", nullable = false)
	private UserDetail userDetail;
	
//	@ManyToOne(cascade = CascadeType.PERSIST)
	@ManyToOne()
	@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
	private Role role;

	
	public User(String email, String password, UserDetail userDetail, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.userDetail = userDetail;
		this.role = role;
	}
	
	

}
