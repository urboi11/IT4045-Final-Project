package com.it4045.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.it4045.finalproject.entities.Course;



@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>  {


}
