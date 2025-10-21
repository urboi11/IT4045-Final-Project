package com.it4045.finalproject.services;

import com.it4045.finalproject.entities.Course;

import java.util.List;

public interface ICourseService {
 public Course createCourse(Course course);

 public List<Course> searchCourses (int courseID);

 public void commentOnCourse (String comment, Course course);






}
