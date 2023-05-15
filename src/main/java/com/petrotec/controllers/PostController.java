package com.petrotec.controllers;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petrotec.config.AppConstants;
import com.petrotec.payload.ApiResponse;
import com.petrotec.payload.PostDto;
import com.petrotec.payload.PostResponse;
import com.petrotec.service.FileService;
import com.petrotec.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("$(project.image)") //getting image path-->hai aapan application.properties file madhe Taakla aahe
	private String path;

	
	// create
	@PostMapping("/user/{userId}/category/{categoryId}/post") //user id aani categoryId bhetel tevhaach aapan post pass
																// karuu ya prakaare aapan Sub-Resource Manage
																// (Mappinng) karat Hoto.
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
	// working--> Explaination
	// jevha client api/user/userId/category/categoryDi/post
	// mag aapan post la create kartoy-->createdPost
	// mag aaplyalya postDto,userId,categoryId bhetoon jaail
	// mag aapan postService.createPost mtd waaproo -->jo ki sarvaat aadhi user
	// kaadhel database madhoon
	// mag category kaadhel database madhoon aani mag value set karel post aani mag
	// post la save karoon taakel-->see PostServiceImpl class ya madhe aapan hei
	// kelay

	
	
	
	
	// get post By UserId  -->//task--> ya saathi pagination design kara.
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> postByUser = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
	}

	
	
	// get post by category-->//task--> ya saathi pn pagination design kara.
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postByCategory, HttpStatus.OK);
	}

	
	
	
	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost() {
		List<PostDto> allPost = this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
	}

	// get post detail by Id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostbyId(@PathVariable Integer postId) {
		PostDto postByID = this.postService.getPostByID(postId);
		return new ResponseEntity<PostDto>(postByID, HttpStatus.OK);
	}
	
	//delete Post By ID
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletPost(postId);
		return new ApiResponse("Post Is Successfully Deleted",true);
	}

	//update Post By ID
	@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId) {
			PostDto updatePost = this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
		}

	// get all posts By Pagination
		@GetMapping("/postsByPage")
		public ResponseEntity<PostResponse> getAllPostByPage(@RequestParam(value = "pageNumber",
		                                                      defaultValue = "0",required = false) Integer pageNumber,
				@RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize) {
			
			 PostResponse allPostByPage = this.postService.getAllPostByPage(pageNumber, pageSize);
			return new ResponseEntity<PostResponse>(allPostByPage, HttpStatus.OK);
		}
		
		
		
		
		
		//get all post By sorting
		@GetMapping("/postsBySort")
		public ResponseEntity<PostResponse> getAllPostBySort
	(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
	    @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
		    @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			    @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir){
			
			 PostResponse allPostBySorting = this.postService.getAllPostBySort(pageNumber, pageSize, sortBy,sortDir);
			return new ResponseEntity<PostResponse>(allPostBySorting, HttpStatus.OK);
		}
		
		
		
		
		
		//searching api
	@GetMapping("/posts/search/{keywords}")	
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords ){ 
		//jar aapla path URI aani pathvariable(parameter) ch naav vegda vegda asala tar aapan hai wapruu shakto asa ("") madhe. 
		List<PostDto> searchPostResult = this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(searchPostResult, HttpStatus.OK);
		//je aapan keyword taaku tya hishobane search karoon deil
	
	}
	
	
	
	
	
	
	
	
	
	
	
	//post Image upload
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		//uploading image
		PostDto postDto = this.postService.getPostByID(postId);
		//jar post asel db madhe tarach yenaar anahi tar ithoonach exception yevoon jaail ki post nahiye db madhe asa.
		
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	               
		//Explaination-->
		//(1) url for postman we have to pass-->"/post/image/upload/{postId}"
		//(2) we have to pass the image in the form of by using 2RequestParam And @pathvariable
		//(3) we Are Uploading it to the folder-->String fileName = this.fileService.uploadImage(path, image);
		//(4) then name of the image will be updated to the database to that post
		//We Will Test It in Postman
		//for testing we need postId 
		
		
		
//	//Method To Serve Files	
//		@GetMapping(value = "/post/image/{imageName}",produces = MediaType.)
//		public void downloadimage(@PathVariable String imageName,HttpServletResponse response){ {
//	not Working Due To Spring version Update Spring Boot And then Try It		
//		}
			
		}
		
		
		
	}
	
	
	
	

