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
    private Integer userCommentId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userid")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="courseId")
    @ToString.Exclude
    private Course course;

    private String comment;
}
