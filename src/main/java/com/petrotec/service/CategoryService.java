package com.petrotec.service;

import java.util.List;

import com.petrotec.payload.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	
	//delete
	void deleteCategory(Integer categoryID);
	
	//get
	CategoryDto getCategory (Integer categoryId);
	
	//getAll
	List<CategoryDto> getAllCategory();
	

}
