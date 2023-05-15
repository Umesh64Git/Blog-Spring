package com.petrotec.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.petrotec.entity.Category;
import com.petrotec.entity.Comment;
import com.petrotec.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<Comment> comments = new HashSet<>();
	//yaache ha fayada hoil
	//jevha aapan post la fetch karuu aapo aap tyaache comments pn yevoon jaatil
	//aaplya vegade comments fetch karnyachi garaj nahi padnaar.
	
	
	//private String imageName"default.png";
	//by default aapan itha 1 photo ghetala aahe aapan yaala change karoon pn gheuu shakati
	
	//postId Aanayci Kahi garaj Nahi kaaran aapan entity madhe aapla class aahe na post tya madhoon he entity madhoonyeil 
	//ti postDto madhe by default
	//we are Taking An Title And Content From User 
	//mhanoon aapan ya fields ithe declare kartoy

}
