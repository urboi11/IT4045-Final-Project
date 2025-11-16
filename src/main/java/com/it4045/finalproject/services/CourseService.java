package com.it4045.finalproject.services;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.repository.CourseRepository;
import com.it4045.finalproject.repository.UserRepository;
import com.it4045.finalproject.repository.UserCommentRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;

import java.util.List;

import static java.lang.Math.round;

/**
 * Service class for managing course-related business logic.
 * Handles course creation, retrieval, rating calculations, and comment management.
 */
@AllArgsConstructor
@Service
public class CourseService implements ICourseService{
private final CourseRepository courseRepository;
private final UserRepository userRepository;
private final UserCommentRepository userCommentRepository;
private final EntityManager entityManager;


    /**
     * Creates and persists a new course to the database.
     *
     * @param course the course entity to create
     * @return the saved course entity with generated ID
     */
    @Override
    public Course createCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    /*@Override
    public List<Course> searchCourses(int courseID) {
       var resultCourse = courseRepository.findById(courseID);
       var resultList = resultCourse.stream().toList();
        return resultList;
    }*/

    /**
     * Searches for courses by course number (e.g., "IT4045C").
     *
     * @param courseNum the course number to search for
     * @return list of courses matching the course number, empty list if none found
     */
    @Override
    public List<Course> searchCourses(String courseNum) {
        List<Course> allCourses = courseRepository.findAll();
        return allCourses.stream().filter(c -> c.getCourseNumber().equals(courseNum)).toList();
    }

    /**
     * Retrieves all courses from the database.
     *
     * @return list of all courses
     */
    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    /**
     * Retrieves a specific course by its ID.
     *
     * @param id the course ID to look up
     * @return the course entity with the specified ID
     * @throws IllegalArgumentException if no course exists with the given ID
     */
    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + id));
    }

    /**
     * Adds a user comment to a specific course.
     *
     * @param comment the comment text to add
     * @param user the user posting the comment
     * @param course the course being commented on
     */
    @Override
    public void commentOnCourse(String comment, User user, Course course) {
        //int targetCourseID = course.getCourseId();
        //Course targetCourse = entityManager.find(Course.class, targetCourseID);
        //UserComments commentToAdd = new UserComments(0, user, targetCourse, comment);
        UserComments commentToAdd = UserComments.builder().user(user).course(course).comment(comment).build();

        course.addComment(commentToAdd);
        //targetCourse.setRating_count(targetCourse.getRating_count()+1);
        //userCommentRepository.save(commentToAdd);
        courseRepository.save(course);

    }

    /**
     * Deletes a user comment and updates the associated course's rating count.
     *
     * @param userCommentId the ID of the comment to delete
     */
    @Override
    public void deleteComment(int userCommentId) {
        UserComments targetComment = entityManager.find(UserComments.class, userCommentId);
        Course targetCourse = targetComment.getCourse();
        userCommentRepository.deleteById(userCommentId);
        targetCourse.setRating_count(targetCourse.getRating_count()-1);

    }

    /**
     * Retrieves all comments associated with a specific course.
     *
     * @param course the course to get comments for
     * @return list of user comments for the specified course
     */
    @Override
    public List<UserComments> getCommentsForCourse(Course course) {
        /*int targetCourseID =course.getCourseId();
        List<UserComments> commentsList = entityManager.find(UserComments.class, course).getCourse().getUserComments();
        return commentsList;*/
        return course.getUserComments();
    }

    /**
     * Retrieves the rounded rating value for a course.
     *
     * @param course the course to get the rating for
     * @return the course rating rounded to the nearest integer
     */
    @Override
    public int getRating(Course course) {
        double rating = course.getCourseRating();
        return (int) round(rating);
    }

    /**
     * Calculates and updates a course's rating based on new user input.
     * Computes the average rating by incorporating the new rating with existing ratings.
     *
     * @param courseId the ID of the course to rate
     * @param ratingInput the rating value as a string (must be 1-5)
     * @throws IllegalArgumentException if the course is not found, rating format is invalid, 
     *         or rating is outside the 1-5 range
     */
    @Override
    public void calculateRating(Integer courseId, String ratingInput) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));
        
        int rating;
        try {
            rating = Integer.parseInt(ratingInput);
            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid rating format: " + ratingInput, e);
        }

        double currentRating = course.getCourseRating();
        int ratingCount = course.getRating_count();
        double newRating = rating;

        if (currentRating != 0) {
            newRating = ((currentRating * ratingCount) + rating) / (ratingCount + 1);
        }
        course.setCourseRating(newRating);
        course.setRating_count(ratingCount + 1);
        courseRepository.save(course);
    }
}



