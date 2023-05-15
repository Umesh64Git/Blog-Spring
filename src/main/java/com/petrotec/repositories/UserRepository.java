package com.petrotec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petrotec.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
