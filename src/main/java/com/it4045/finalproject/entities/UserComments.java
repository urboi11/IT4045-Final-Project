package com.it4045.finalproject.entities;

import jakarta.persistence.*;
import lombok.*;

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
