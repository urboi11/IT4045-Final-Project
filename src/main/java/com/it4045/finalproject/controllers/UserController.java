package com.it4045.finalproject.controllers;

import com.it4045.finalproject.dtos.CommentsDto;
import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.mappers.UserAndCommentsMapper;
import com.it4045.finalproject.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {


    private final UserService userService;


    private final UserAndCommentsMapper mapper;

    // // This method is for testing the createCourse method in CourseController
    // @GetMapping
    // public String showAdminPage(Model model) {
    //     // since the POST for courses is on the admin page, need to add course to model
    //     model.addAttribute("course", new Course());
    //     return "users/admin";
    // }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.UserDto(user));
        
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentsDto>> getUserComments(@PathVariable int id) {
        // Retrieve all comments made by user
        User user = userService.getUser(id);
        List<UserComments> comments =  userService.getCommentsForUser(user);
        List<CommentsDto> commentsDto = new ArrayList();

        for(UserComments comment : comments) {
            commentsDto.add(mapper.CommentsDto(comment));
        }
        return ResponseEntity.ok(commentsDto);
    }

    //HTTP BODY?
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentId) {
        // Validate ownership
        // Delete comment

        // Return success status
    }
}