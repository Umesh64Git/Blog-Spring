package com.petrotec.controllers;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.petrotec.payload.ApiResponse;
import com.petrotec.payload.UserDto;
import com.petrotec.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto ){
		UserDto createdUser = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
		
	}
	@PutMapping("/{userID}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userID){
		UserDto updatedUser = this.userService.updateUser(userDto, userID);
		return ResponseEntity.ok(updatedUser);
	}
	
//	@DeleteMapping("/{uId}")
//	public ResponseEntity<?> deleteUser(@PathVariable Integer uId){ 
	//jar aaplya la type nasala mahit aapan ky return kartoy tar aapan ? pn use Karuu shakato
//		this.userService.deleteUser(uId);
//		return new ResponseEntity(Map.of("Message", "User Deleted Successfully"),HttpStatus.OK);
	//client la ha message bhethel key value pair madhe
//		//aata aaplya la jar ha prakaar nasnaar wapraayacha tar aapan 1 seperate class pn banvuu shakato
//		//payload package madhe Api response class banvuu
//		//aani yaat aaplya la ky ky pathvaychay te variable declare karoon taaku
//		//jasa ki erorcod aani error message
//		//aata ya case madhe aapan api kasa generate karuu te bagha 
//		
//	}
	//Deleting User Using ApiResponse Class and Not using Map As We Used It In Above Example
	//mostly use these concept.
	@DeleteMapping("/{uIdDelete}")
	public ResponseEntity<ApiResponse> deleteingUser(@PathVariable Integer uIdDelete){ 
		//jar aaplya la type nasala mahit aapan ky return kartoy tar aapan ? pn use Karuu shakato
		this.userService.deleteUser(uIdDelete);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUser());
		 
	}
	@GetMapping("/getByID/{uIdGet}")
	public ResponseEntity<UserDto> getUsers(@PathVariable Integer uIdGet){
		return ResponseEntity.ok(this.userService.getUserByID(uIdGet));
		 
	}
	
}
