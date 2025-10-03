package com.it4045.finalproject.mappers;

import com.it4045.finalproject.data.CourseDTO;
import com.it4045.finalproject.entities.Course;



public class CourseMapper {
    public CourseDTO courseDTOConverter(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        
        courseDTO.setCourseName(course.getCourseName());

        courseDTO.setCourseRating(course.getCourseRating());

        courseDTO.setDescription(course.getDescription());

        courseDTO.setUniversity(course.getUniversity());

        return courseDTO;
    }
}
