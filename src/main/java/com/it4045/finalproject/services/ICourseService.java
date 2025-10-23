package com.it4045.finalproject.services;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;

import java.util.List;

public interface ICourseService {
 public Course createCourse(Course course);

 public List<Course> searchCourses (int courseID);

 public void commentOnCourse (String comment, User user, Course course);

 public void deleteComment(int userCommentId);

public List<UserComments> getCommentsForCourse(Course course);

public int getRating (Course course);

public void calculateRating (Course course, int rating);



}
