package com.it4045.finalproject.repository;

import com.it4045.finalproject.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A repository interface for managing Course entities in the database.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 * @see Course
 * @author Enterprise App Development Final Project Group
 */

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
    List<Course> findByUniversityAndCourseName(String university, String courseName);
    List<Course> findByUniversityAndCourseNumber(String university, String courseNumber);
    List<Course> findByCourseNumber(String courseNumber);
}
