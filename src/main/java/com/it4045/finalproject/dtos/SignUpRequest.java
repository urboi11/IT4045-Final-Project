package com.it4045.finalproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Data Transfer Object for Sign-Up Requests, used to encapsulate details provided by a new user during registration.
 * Includes firstName (String), lastName (String), email (String), and password (String) fields.
 * Uses Lombok annotations for boilerplate code reduction.
 * @see com.it4045.finalproject.entities.User
 * @author Enterprise Application Development Final Project Group
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    

    private String firstName;

    private String lastName;

    private String email;

    private String password;
    
}
