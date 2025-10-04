package com.it4045.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.it4045.finalproject.entities.UserComments;

public interface UserCommentRepository extends CrudRepository<UserComments, Integer> {
    
}


