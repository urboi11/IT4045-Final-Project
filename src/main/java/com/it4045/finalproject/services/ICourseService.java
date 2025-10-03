package com.it4045.finalproject.services;

import java.util.List;

import com.it4045.finalproject.data.CourseDTO;
import com.it4045.finalproject.entities.Course;

public interface ICourseService{

    public CourseDTO createCourse(Course course);

    public CourseDTO searchCourses(int courseId);

}