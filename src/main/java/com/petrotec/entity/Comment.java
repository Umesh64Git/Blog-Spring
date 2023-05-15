package com.petrotec.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ManyToAny;
import lombok.Getter;	
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;
	
	//comment kontya post cha aahe te manage karuu
	//Many comment Has One Post
	//Aapan Itha Mapping KArtoy Post Chi Aani Comment Chi
	//tyaach Prakaare aapan User Saathi Pn mapping karuu Shakato He aaplya saathi assignment aahe.
	
	
	@ManyToOne
	private Post post;
	
	//aata aapan ek post taakli 
	//tar ek post javal khoop saare comment asuu shakat
	//mhanoon aata aapan post entity waaalya class madhe javoon relation
	//maintain thevuu mhanje mapping karuu
	//@OneToMany
	//private Set<Comment> comment = new HashSet<>();
	//One Comment Has Many Post
	
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
