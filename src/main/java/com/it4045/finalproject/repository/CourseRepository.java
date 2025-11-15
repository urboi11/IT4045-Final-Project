package com.it4045.finalproject.repository;

import com.it4045.finalproject.entities.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
    List<Course> findByUniversityAndCourseName(String university, String courseName);
    List<Course> findByUniversityAndCourseNumber(String university, String courseNumber);
}
