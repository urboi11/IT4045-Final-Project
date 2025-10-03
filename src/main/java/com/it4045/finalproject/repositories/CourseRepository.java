package com.it4045.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.it4045.finalproject.entities.Course;



@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, ICourseRepository   {


}
