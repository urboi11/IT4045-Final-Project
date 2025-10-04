package com.it4045.finalproject.repository;

import com.it4045.finalproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
