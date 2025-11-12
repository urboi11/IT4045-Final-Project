package com.it4045.finalproject.repository;

import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findBy(User user);
}
