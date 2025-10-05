package com.it4045.finalproject.repository;

import com.it4045.finalproject.entities.UserComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
@Repository
public interface CourseRepository extends JpaRepository<UserComments, Integer> , ICourseRepository{
}
