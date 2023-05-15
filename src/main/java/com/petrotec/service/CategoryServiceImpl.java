package com.petrotec.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petrotec.entity.Category;
import com.petrotec.exceptions.ResourceNotFoundException;
import com.petrotec.payload.CategoryDto;
import com.petrotec.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category savedCate = this.categoryRepository.save(category);
		return this.modelMapper.map(savedCate,CategoryDto.class);
	}
	
	

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		 Category category = this.categoryRepository.findById(categoryId).
				 orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));

		 category.setCategoryTitle(categoryDto.getCategoryTitle());
		 category.setCategoryDescription(categoryDto.getCategoryDescription());
		 
		 Category updatedCategory = this.categoryRepository.save(category);
		 
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	
	
	
	
	
	
	@Override
	public void deleteCategory(Integer categoryID) {
		Category category = this.categoryRepository.findById(categoryID).orElseThrow(()->
		                          new ResourceNotFoundException("Category","categoryId",categoryID));
		this.categoryRepository.delete(category);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->
		                        new ResourceNotFoundException("Category", "categoryId", categoryId));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryDto> listOfCateDto = categories.stream().map((cat)->
		                         this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return listOfCateDto;
	}

}
