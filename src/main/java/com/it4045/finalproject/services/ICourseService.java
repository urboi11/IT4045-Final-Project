package com.it4045.finalproject.services;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;

import java.util.List;

/**
 * A service interface defining methods for managing Course entities and related operations.
 * @see Course
 * @see com.it4045.finalproject.controllers.CourseController
 * @author Enterprise Application Development Final Project Group
 */
public interface ICourseService {
 public Course createCourse(Course course);

 public List<Course> getCourses();

 public List<Course> searchCourses (String courseNum);

 public Course getCourseById(Integer id);

 public void commentOnCourse (String comment, User user, Course course);

public void calculateRating (Integer courseId, int rating);

public void deleteCourse(Integer courseId);


}
