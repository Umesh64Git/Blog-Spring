package com.petrotec.payload;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	//these payload package DTO class is used to transfer data 
	//in project we will not use entity class 
	//to transfer data hence we are creating user DTO Class
	//we will not expose our entity class 
	//we will use entity class only to connect with DB and to Store Info in Db
	
	
	
    private Integer userId;
    
    //USing Validator anonotations
    
   @NotEmpty
   @Size(min = 4,message = "USername Must Be Of MIn 4 Characters!!!")
   private String name;
   
   @Email(message = "Email Addresss IS NOt Valid!!!")
	private String email;
   
   @NotEmpty
   @Size(min = 3,max = 10,message = "Password Must Be Minimum OF 3 Characters and Max Of 10 Chars!! ")
//   @Pattern(regexp="^[a-zA-Z0-9]{3}",message="length must be 3")  
	private String password;
   
   @NotEmpty
	private String about;


   
 //Basics Of Validation
 //Valid Data Yeto Aahe KA nahi tya saathi aapan Validation kartoy
 //(1) java bean is validated with JSR 380 Known As Bean Validation 2.0
 //(2) JSR 380 is Specification for the java API for bean validation.properties of bean meet the specific criteria.
 //(3) for validation different annotation is used like @NotNull, @Min, @Size etc.
 //(4) hibernate validator is a implementation of validation api.

 //Important Annotations For Validations
 //(1) NotNull
 //(2) Email
 //(3) Size
 //(4) Min
 //(5) Max
 //(6) NotEmpty 
 //etc

 //How to USe In Our Project
 //(1) Spring boot provides support for Hibernate Validator
 //(2) Add Dependency --> Spring-boot-starter-validation
 //(3) add Annotaions on our Bean (Variables Of UserDto Class).
 //(4) Add -->@Valid Annotation On Api's Methods In Controller .
 //(5) Format Response for These Message Arguement Not Found Which is 
 //coming when we tried to add invalid data with null fields in postman
 //(6) Global Exception madhe Handle KAron Ghya hai postman madhala Exception.
}
