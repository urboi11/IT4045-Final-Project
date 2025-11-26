package com.it4045.finalproject.services;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.exceptions.CourseNameExistsException;
import com.it4045.finalproject.exceptions.CourseNumberExistsException;
import com.it4045.finalproject.exceptions.EmptyCommentException;
import com.it4045.finalproject.repository.CourseRepository;
import com.it4045.finalproject.repository.UserRepository;
import com.it4045.finalproject.repository.UserCommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
@Data
@Service
public class CourseService implements ICourseService{
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final UserCommentRepository userCommentRepository;

    @Override
    public Course createCourse(Course course) {
        if (course.getCourseNumber().isBlank()) {
            throw new IllegalArgumentException("Course number is required");
        }

        if (course.getCourseName().isBlank()) {
            throw new IllegalArgumentException("Course name is required");
        }

        if (course.getUniversity().isBlank()) {
            throw new IllegalArgumentException("University is required");
        }

        if (course.getDescription().isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        // Check for duplicates by course name at the same university
        if (!courseRepository.findByUniversityAndCourseName(course.getUniversity(), course.getCourseName()).isEmpty()) {
            throw new CourseNameExistsException("A course with this name already exists at the university");
        }

        // Check for duplicates by course number at the same university
        if (!courseRepository.findByUniversityAndCourseNumber(course.getUniversity(), course.getCourseNumber()).isEmpty()) {
            throw new CourseNumberExistsException("A course with this number already exists at the university");
        }
        courseRepository.save(course);
        return course;
    }

    @Override
    @Transactional
    public void deleteCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        courseRepository.delete(course);
    }

    @Override
    public List<Course> searchCourses(String courseNum) {
        return courseRepository.findByCourseNumber(courseNum);
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void commentOnCourse(String comment, User user, Course course) {
        if (comment.length() == 0)
            throw new EmptyCommentException("Comment cannot be empty");
        UserComments commentToAdd = UserComments.builder().user(user).course(course).comment(comment).build();
        course.addComment(commentToAdd);
        userCommentRepository.save(commentToAdd);
    }

    @Override
    public void calculateRating(Integer courseId, int rating) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

        double currentRating = course.getCourseRating();
        int ratingCount = course.getRating_count();
        double newRating = rating;

        if (currentRating != 0) {
            newRating = ((currentRating * ratingCount) + rating) / (ratingCount + 1);
        }
        newRating = (double) Math.round(newRating * 100) / 100;
        course.setCourseRating(newRating);
        course.setRating_count(ratingCount + 1);
        courseRepository.save(course);
    }
}



