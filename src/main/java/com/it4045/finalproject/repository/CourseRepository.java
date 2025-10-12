package com.it4045.finalproject.repository;

import com.it4045.finalproject.entities.UserComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<UserComments, Integer>{
}
