package com.it4045.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import org.springframework.stereotype.Repository;

/**
 * A repository interface for managing UserComments entities in the database.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 * @see UserComments
 * @author Enterprise Application Development Final Project Group
 */
@Repository
public interface UserCommentRepository extends JpaRepository<UserComments, Integer>{
    public List<UserComments> findByUser(User user);
}