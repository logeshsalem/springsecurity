package com.security.model;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="customer")
@Getter @Setter
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int id;
	
	@Column(name="name")
	private String name; 
	
	@Column(name="email")
	private String email;
	
	@Column(name="pwd")
	private String password;
	
	@Column(name="mobile_number")
	private long mobileNumber;
	
	@Column(name="role")
	private String role;
	
	@Column(name="create_dt")
	@JsonIgnore
	private Date createDt;
	
	@OneToMany(mappedBy = "customer", fetch=FetchType.EAGER)
	private Set<Authority> authorities;
	
	

}
