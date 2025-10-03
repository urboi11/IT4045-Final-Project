package com.it4045.finalproject.mappers;

import com.it4045.finalproject.data.UserDTO;
import com.it4045.finalproject.entities.User;



public class UserMapper {
  
    public static UserDTO DTOConverter(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserFirstName(user.getUserFirstName());
        userDTO.setUserLastName(user.getUserLastName());
        userDTO.setUserEmail(user.getUserEmail());

        return userDTO;
    }
    
}
