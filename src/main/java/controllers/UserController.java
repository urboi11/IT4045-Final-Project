package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    // @GetMapping("/{id}")
    // public ResponseEntity<User> getUser(@PathVariable int id) {
    //     // Fetch user from service
    //     // Return user profile
    // }

    // @GetMapping("/{id}/comments")
    // public ResponseEntity<List<UserComment>> getUserComments(@PathVariable int id) {
    //     // Retrieve all comments made by user
    //     // Return list of comments
    // }

    // @DeleteMapping("/comments/{commentId}")
    // public ResponseEntity<Void> deleteComment(@PathVariable int commentId) {
    //     // Validate ownership
    //     // Delete comment
    //     // Return success status
    // }
}