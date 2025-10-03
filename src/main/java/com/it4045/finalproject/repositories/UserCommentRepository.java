package com.it4045.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it4045.finalproject.entities.UserComments;

public interface UserCommentRepository extends JpaRepository<UserComments, Integer> {
    
}


