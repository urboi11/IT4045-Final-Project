package com.it4045.finalproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A Data Transfer Object for User information, used to encapsulate user details. Does not include passwords or other sensitive information.
 * Includes userid (Integer), firstname (String), and lastname (String) fields.
 * Uses Lombok annotations for boilerplate code reduction.
 * @see com.it4045.finalproject.entities.User
 * @author Enterprise Application Development Final Project Group
 */
@Getter
@AllArgsConstructor
public class UserDto {
    private Integer userid;

    private String firstname;

    private String lastname;

}
