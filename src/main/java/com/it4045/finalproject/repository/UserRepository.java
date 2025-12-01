package com.it4045.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.it4045.finalproject.entities.User;

/**
 * A repository interface for managing User entities in the database.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 * @see User
 * @author Enterprise Application Development Final Project Group
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
}
