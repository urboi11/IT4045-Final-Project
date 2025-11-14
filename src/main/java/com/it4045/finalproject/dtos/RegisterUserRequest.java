package com.it4045.finalproject.dtos;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPass;
}
