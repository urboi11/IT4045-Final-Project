package com.it4045.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.it4045.finalproject.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}
