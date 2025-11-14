package com.it4045.finalproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;


}
