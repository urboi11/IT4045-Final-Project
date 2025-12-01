package com.it4045.finalproject.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An entity class representing a Course in the database.
 * Courses have a courseID (Integer and primary key), courseNumber (String), courseName (String),
 * courseRating (double), university (String), description (String), rating_count (int), and a list of associated UserComments.
 * Uses Lombok annotations for boilerplate code reduction.
 * @author Enterprise Application Development Final Project Group
 */
@Entity
@Table(name="courses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Integer courseId;

    @Column(name="course_number")
    private String courseNumber;

    @Column(name="course_name")
    private String courseName;

    @Column(name="course_rating")
    private double courseRating;

    private String university;

    private String description;

    private int rating_count;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<UserComments> userComments = new ArrayList<>();

    public void addComment(UserComments comment){
        userComments.add(comment);
        comment.setCourse(this);
    }
}
