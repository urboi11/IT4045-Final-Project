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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;

    @Column(name="user_first_name")
    private String userFirstName;

    @Column(name="user_last_name")
    private String userLastName;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="user_pass")
    private String userPass;

    @Column(name="is_admin")
    private boolean isAdmin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @Builder.Default
    private List<UserComments> userComments = new ArrayList<>();

    public void addComment(UserComments comment){
        userComments.add(comment);
        comment.setUser(this);
    }

}
