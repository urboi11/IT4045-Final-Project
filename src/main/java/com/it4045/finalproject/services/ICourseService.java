package com.it4045.finalproject.services;

import com.it4045.finalproject.data.CourseDTO;

public interface ICourseService{

    public CourseDTO createCourse(CourseDTO course);

    public List<CourseDTO> searchCourses(int courseId);


}