package com.petrotec.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petrotec.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
