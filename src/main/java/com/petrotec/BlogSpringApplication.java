package com.petrotec;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSpringApplication.class, args);
	    System.out.println("Hell");
    }
	
	
	//Step-(1) -->Add Model Mapper Maven Dependency in pom.xml file
	//Step-(2) --> Create ModelMapper Method In Main Class As Below
	//Step-(3) -->Go Into ServiceImplClass -->@AutoWired MOdel Mapper
	//Step-(4) -->create method to convert userDto Object To USer(entity) Object USing modelMApper
	//Step-(5) -->

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
