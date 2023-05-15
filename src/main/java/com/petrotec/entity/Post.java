package com.petrotec.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Post_Tbl")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name = "Post_Title",length = 100,nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String content;
	
	private String imageName;
	
	private Date addDate;
	
	//post jo aahe aapla to sub-resource aahe 
	//See dig in mobile Gallery Date
	//aani yaache 2 parent resources aahet
	//(1) Category aani
	//(2) User
	//Hibernate Mapping -->building Relationship of Post With User & Category By Doing Mapping.
	//Aani Ya Maaping Chya Hishobhaane Aaple Table Pn Create zhaale DataBAse Madhe.
	
	@ManyToOne
	@JoinColumn(name ="category_Id")//to change the name of join column which is mainting our mapping(Relationship)
	private Category category;
	//jar aaplya la post bhetoon geli 1 tar aapan category bahguu shakto kuthlya category madhe post keli aahe ti
	
	@ManyToOne
	private User user;
	//jar aaplya la post bhetoon geli 1 tar aapan user pn baghuu shakto ki ti kuthlya user ne kelli aahe te
	
	//Aata Mapping Kelya Var Hai Lakshaat Theva 
	//Jevha Aapan separately Post la add karuu tar aaplya 2 vastuu laagtil
	//1 aaplya la user paahije
	//2 aaplya category paahije tevhaach aapan post la add karuu shakuu
	//jar post la add karat astaana user mahit nasel  ki kuthalya user ne post add keliye, 
	//aani ti post kuthalya category madhe jaatey aahe hai maahit paahije
	//jar ya 2 goshti aaplya la maahit nastil tar aapan post la add karuu shaknaaar nahi
	
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	//mapped by post ya sathi kartoy kaaran aapli ji foreign key banel 
	//ti fakata comment table madhe banel mhanoon aapan mapped by kartoy.
	
	//application run kara aani checkkara mapping barobar zhali aahe ki nahi
		//mhanje table barobar generate hotaay ki nahi te aapan check karoon gheuu sql madhe javoon.

	//Sql Madhe Aapan reveser Engineer Karoon Relationship Kashi aahe Tables madhe te pn Baghuu Shakto.
	//Procedure
	//(1) Go Into MySql-->
	//(2) Select Schema
	//(3) At Top Select On Database
	//(4) Reverse Engineer
	//(5) Select Stored Connection As Localhost
	//(6) Next-> Enter PAssword->next->select Schema ->next->next->Excecute
	//We have Generated Automatic Entity diagram.
	
}
