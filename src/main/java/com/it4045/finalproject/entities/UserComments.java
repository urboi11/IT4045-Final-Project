package com.it4045.finalproject.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * An entity class representing a user comment in the database.
 * UserComments have a userCommentId (Integer and primary key), a User (ManyToOne relationship, stored as an associated User entity),
 * a Course (ManyToOne relationship, stored as an associated Course entity), and a comment (String).
 * Uses Lombok annotations for boilerplate code reduction.
 * @author Enterprise Application Development Final Project Group
 */

@Entity
@Table(name="usercomments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_comment_id")
    private Integer userCommentId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userid")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    @ToString.Exclude
    private Course course;

    private String comment;
}
