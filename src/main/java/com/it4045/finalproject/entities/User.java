package com.it4045.finalproject.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    public User(String firstname, String lastname, String email, String password, String role){
        this.firstname = firstname; 
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid")
    private Integer userid;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="email")
    private String email;

    @Column(name="password")
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
