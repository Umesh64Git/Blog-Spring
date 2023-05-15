package com.petrotec.controllers;

import java.util.List;

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

import com.petrotec.payload.ApiResponse;
import com.petrotec.payload.CategoryDto;
import com.petrotec.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryContoller {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
	CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			                                                                   @PathVariable Integer catId){
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,catId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	
	}
	
	
	
	
	
	
	
	@DeleteMapping("/{CategId}")
	public ResponseEntity<ApiResponse> deleteCategory(@Valid @PathVariable Integer CategId){
		this.categoryService.deleteCategory(CategId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category Is Deleted Successfully",false), 
				                                                                              HttpStatus.OK);
	}
	
	
	@GetMapping("/{cateId}")
	public ResponseEntity<CategoryDto> getCateoryByID(@Valid @PathVariable Integer cateId ){
		CategoryDto category = this.categoryService.getCategory(cateId);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
	}
	
	
	@GetMapping("/getall")
	public ResponseEntity<List<CategoryDto>> getAllCateory( ){
		 List<CategoryDto> allCategory = this.categoryService.getAllCategory();
	return new ResponseEntity<List<CategoryDto>>(allCategory, HttpStatus.OK);
	}
	
	

}
