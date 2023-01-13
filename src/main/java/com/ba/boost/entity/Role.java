package com.ba.boost.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = "userLists")

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "description")
	private String description;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "created_on")
	private Date createdOn; 
	
	
	@OneToMany(mappedBy = "role")
	private List<User> userLists;
	
	@ManyToMany
	@JoinTable(name = "roles_rules", joinColumns = {@JoinColumn(name= "role_id")}, inverseJoinColumns = {@JoinColumn(name="rule_id")})
	private List<Rule> rules;

	public Role(String roleName, String description) {
		super();
		this.roleName = roleName;
		this.description = description;
	}
	
	
}
