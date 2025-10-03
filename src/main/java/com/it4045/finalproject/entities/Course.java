package com.it4045.finalproject.entities;

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
    private int courseId;

    private String courseNumber;
    private String courseName;
    private double courseRating;
    private String university;
    private String description;
}
