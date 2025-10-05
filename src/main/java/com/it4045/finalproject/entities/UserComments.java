package com.it4045.finalproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="courseId")
    private Course course;

    private String comment;
}
