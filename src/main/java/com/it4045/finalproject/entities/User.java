package com.it4045.finalproject.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An entity class representing a User in the database.
 * Users have a userid (Integer and primary key), a firstname (String), a lastname (String),
 * an email (String), a password (String), a role (String), and a List of associated UserComments.
 * Uses Lombok annotations for boilerplate code reduction.
 * @author Enterprise Application Development Final Project Group
 */
@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userid;

    @Column(name="user_first_name")
    private String firstname;

    @Column(name="user_last_name")
    private String lastname;

    @Column(name="user_email")
    private String email;

    @Column(name="user_pass")
    private String password;

    @Column(name="role")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @Builder.Default
    private List<UserComments> userComments = new ArrayList<>();

    public void addComment(UserComments comment){
        userComments.add(comment);
        comment.setUser(this);
    }

}
