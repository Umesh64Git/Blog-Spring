package com.petrotec.service;

import java.util.List;
import com.petrotec.payload.PostDto;
import com.petrotec.payload.PostResponse;


public interface PostService {

	
	//create
	PostDto createPost(PostDto postDto, Integer userId,Integer categoryID);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletPost(Integer postId);
	
	//getAllPost
	List<PostDto> getAllPost();
	
	
	
	//get Single Post
	PostDto getPostByID(Integer postId);
	
	//get All Post By Category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get All Post User
	List<PostDto> getPostByUser(Integer userId);
	
	
	
	
	
	//getAllPostByPage
	//List<PostDto> getAllPostByPage(Integer pageNumber,Integer pageSize);
	PostResponse getAllPostByPage(Integer pageNumber,Integer pageSize);
	//modifying and sending PostRespone to client ya mudhe tyaala purn mahiti bhetel.
		
	
	////getAllPostBySorting
	//List<PostDto> getAllPostByPage(Integer pageNumber,Integer pageSize);
	PostResponse getAllPostBySort(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	
	//serach Posts
	//for searching we need to create our findBy method In PostRepository interface 
	//Because There Is No Predefined Method For Searching.
		List<PostDto> searchPost(String keyword);
		//ya keyword chya hishobane aapan ssearch karuu
}
