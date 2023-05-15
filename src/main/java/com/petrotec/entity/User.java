package com.petrotec.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.el.parser.AstFalse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "user_name",nullable = false,length = 20)
	private String name;
	
	
	//@Column(name = "user_mail",nullable = false,length = 20) 
	private String email;
	
	//@Column(name = "user_pass",nullable = false,length = 10)
	private String password;
	
	//@Column(name = "about",nullable = false,length = 100)
	private String about;
	
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> post = new ArrayList<>();
	//we can also use set here.
	//@OneToMany-->jar aapan user fetch kela tari aaplya la sarva post bheton jaatil database madhoon jpa aaplya la
	//kadoon deil aapoaap.
}


