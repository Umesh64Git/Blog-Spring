package com.petrotec.service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petrotec.entity.Category;
import com.petrotec.entity.Post;
import com.petrotec.entity.User;
import com.petrotec.exceptions.ResourceNotFoundException;
import com.petrotec.payload.PostDto;
import com.petrotec.payload.PostResponse;
import com.petrotec.repositories.CategoryRepository;
import com.petrotec.repositories.PostRepository;
import com.petrotec.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", 
				"User id", userId));
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> 
		new ResourceNotFoundException("category","categorId", categoryId));
		
		Post post = this.modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepository.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", 
				"postId", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepository.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletPost(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", 
				"postId", postId));
		this.postRepository.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> allPosts = this.postRepository.findAll();
		//converting all posts into PostDto
		List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}
	
	
	

	@Override
	public PostDto getPostByID(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post",
				"postId", postId));
	
		return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
	Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> 
	new ResourceNotFoundException("Category", "CategoryId", categoryId));//getting all by categories
		List<Post> posts = this.postRepository.findByCategory(category);//will give us list of post
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList()); //we need to PostDto hence converting posts into PostDtosDto
		
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", 
				"UserID", userId));
		List<Post> posts = this.postRepository.findByUser(user);
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
	
		return postDtos;
	}

	

	@Override
	public PostResponse getAllPostByPage(Integer pageNumber, Integer pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
	
			Page<Post> pagePost = this.postRepository.findAll(pageRequest);
			List<Post> allPosts = pagePost.getContent();
			List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class))
					.collect(Collectors.toList());
			
			PostResponse postResponse = new PostResponse();
			
			postResponse.setContent(postDtos);
			postResponse.setPageNumber(pagePost.getNumber());
			postResponse.setPageSize(pagePost.getSize());
			postResponse.setTotalElements(pagePost.getTotalElements());
			postResponse.setTotalPages(pagePost.getTotalPages());
			postResponse.setLastPage(pagePost.isLast());
			
			return postResponse;
	
		
		
	}
	
	
	
	
	
	
	

	@Override
	public PostResponse getAllPostBySort(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
		
		//(1) -->By Using If Else Condition
//		Sort sort = null;
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort = Sort.by(sortBy).ascending();
//		}else {
//			sort= Sort.by(sortBy).descending();
//		}
		
		//(2) -->By Using Ternary Operator
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
	
		//to change An ascending and Descending order aata aapan dynamically aaplya hishobane 
		//asc aani desc order change karuu shakato.
		//http://localhost:9090/api/postsByPage?pageNumber=1&pageSize=3&sortBy=postId
//		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		//postman url for normal sorting
		
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
		//http://localhost:9090/api/postsByPage?pageNumber=1&pageSize=3&sortBy=postId&sortOrd=desc
		//by appliying sortBy dynamically we can use asc and desc		
		//http://localhost:9090/api/postsByPage?pageNumber=1&pageSize=3&sortBy=postId&sortOrd=asc
		//for asc shorting which is by default applied
		Page<Post> pagePost = this.postRepository.findAll(pageRequest);
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post)->
		              this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
		
	}
	
	
	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postRepository.findByTitleContaining(keyword);
		
		//aata aapan stream api waproon har ek mhanje pratyek posts la change karoo postDto madhe 
		//kaaran aapan direct entity network var pathvuu shakat nahi mhanoon aaplya la Dto waprayche aahet
		
		List<PostDto> listOfPostDtos = posts.stream().map((post)-> 
		                                  this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		//Explaination-->              using Stram api.map waproom ek ek post la convert kartoy postDto 
		//madhe aani mag tyaana as list aapan collect karoon ghetala aani aaplya la bhetoon geli listOfDtos.
		return listOfPostDtos;
	}
	
}
