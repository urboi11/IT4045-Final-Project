package com.it4045.finalproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Data Transfer Object for Login Requests, used to encapsulate credentials for user authentication.
 * Includes username (String) and password (String) fields.
 * Uses Lombok annotations for boilerplate code reduction.
 * @see com.it4045.finalproject.entities.User
 * @author Enterprise Application Development Final Project Group
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    private String username;

    private String password;
}
