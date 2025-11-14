package com.it4045.finalproject.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private int ratingCount;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private List<UserComments> userComments = new ArrayList<>();

    public void addComment(UserComments comment){
        userComments.add(comment);
        comment.setCourse(this);
    }
}
