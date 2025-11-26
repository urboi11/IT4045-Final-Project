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

/**
 * Service class for managing course-related business logic.
 * Handles course creation, retrieval, rating calculations, and comment management.
 *
 * @see ICourseService
 * @see CourseRepository
 * JavaDoc proposed by Gabby Herlocher and edited by Ethan Goudy
 *
 * * @author Enterprise App Development Final Project Group
 */

@AllArgsConstructor
@Data
@Service
public class CourseService implements ICourseService{
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final UserCommentRepository userCommentRepository;

    /**
     * Creates and persists a new course to the database.
     *
     * @param course the course entity to create
     * @return the saved course entity with generated ID
     *
     * @throws IllegalArgumentException if any required course fields are missing
     * @throws CourseNameExistsException if a course with the same name already exists with the target name/host
     * @throws CourseNumberExistsException if a course with the same number already exists with the target number
     * @throws IllegalArgumentException if required parameters are blank
     * JavaDoc proposed by Gabby Herlocher and edited by Ethan Goudy
     *
     * @author Enterprise App Development Final Project Group
     */
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


    /**
     * This method deletes a course from the database, given a target ID as an integer.
     *
     * JavaDoc by Ethan Goudy
     *
     * @param courseId the ID of the course to delete
     *
     * @author Enterprise App Development Final Project Group
     */
    @Override
    @Transactional
    public void deleteCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        courseRepository.delete(course);
    }

    /**
     * Searches for courses by course number (e.g., "IT4045C").
     *
     * JavaDoc proposed by Gabby Herlocher and edited by Ethan Goudy
     *
     * @param courseNum the course number to search for
     * @return list of courses matching the course number, empty list if none found
     */
    @Override
    public List<Course> searchCourses(String courseNum) {
        return courseRepository.findByCourseNumber(courseNum);
    }

    /**
     * Retrieves all courses from the database.
     *
     * JavaDoc proposed by Gabby Herlocher and edited by Ethan Goudy
     *
     * @return list of all courses
     *
     * @author Enterprise App Development Final Project Group
     */
    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    /**
     * Retrieves a specific course by its ID.
     *
     * JavaDoc proposed by Gabby Herlocher and edited by Ethan Goudy
     *
     * @param id the course ID to look up
     * @return the course entity with the specified ID
     * @throws IllegalArgumentException if no course exists with the given ID
     * @author Enterprise App Development Final Project Group
     */
    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    /**
     * Adds a user comment to a specific course.
     *
     * JavaDoc proposed by Gabby Herlocher and edited by Ethan Goudy
     *
     * @param comment the comment text to add
     * @param user the user posting the comment
     * @param course the course being commented on
     *
     * @throws EmptyCommentException if the comment has no text
     *
     * @author Enterprise App Development Final Project Group
     */
    @Override
    @Transactional
    public void commentOnCourse(String comment, User user, Course course) {
        if (comment.length() == 0)
            throw new EmptyCommentException("Comment cannot be empty");
        UserComments commentToAdd = UserComments.builder().user(user).course(course).comment(comment).build();
        course.addComment(commentToAdd);
        userCommentRepository.save(commentToAdd);
    }

    /**
     * Calculates and updates a course's rating based on new user input.
     * Computes the average rating by incorporating the new rating with existing ratings.
     *
     * JavaDoc proposed by Gabby Herlocher and edited by Ethan Goudy
     *
     * @param courseId the ID of the course to rate
     * @param rating the rating value as a string (must be 1-5)
     * @throws IllegalArgumentException if the course is not found, rating format is invalid,
     *         or rating is outside the 1-5 range
     *
     * @author Enterprise App Development Final Project Group
     */
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



