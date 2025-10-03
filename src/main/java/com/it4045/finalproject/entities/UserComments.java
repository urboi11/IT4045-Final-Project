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
    private int userCommentId;


    //TODO: I think a OneToMany annotation needs to be here for the reference to the User class?
    private int userId;
    private int courseId;
    private String comment;
}
