package com.petrotec.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Category_Tbl")
@NoArgsConstructor
@Getter
@Setter
public class Category {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name = "Title",length = 100,nullable = false)
	private String categoryTitle;
	
	@Column(name = "Description")
	private String categoryDescription;
	
	
	//mapping-->one Category Can have Many Posts
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//                                                         FetchType=Eager/Lazy
	//parent sobat child che changes pn save houn jaatil cascadeType.all Waprlyaane.
	private List<Post> post = new ArrayList<>();
	
	//@OneToMany-->jar aapan category fetch keli tar aaplya la sarva post bhetoon jaatil.

}
