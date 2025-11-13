package com.it4045.finalproject.controllers;



import com.it4045.finalproject.dtos.CommentsDto;
import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.mappers.UserAndCommentsMapper;
import com.it4045.finalproject.repository.UserCommentRepository;
import com.it4045.finalproject.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Comments;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Controller
@Slf4j
@RequestMapping("/users")
public class UserController {

    //TODO: I need to implement a checker for status of user, so someone cannot access the endpoint without being authenticated. 


    private final UserService userService;

    private final UserAndCommentsMapper mapper;

    private final UserCommentRepository commentRepository;


    @GetMapping("/profile")
    public String showProfilePage(HttpServletRequest session, Model model) {
        

        if(session.getSession().getAttribute("CurrentUser").equals(null)) {
            return "redirect:/auth/login";
        }
        //Check for Current Role of user
        User user = userService.findByEmail(session.getAttribute("CurrentUser").toString());     

        model.addAttribute("Email", user.getEmail());
        model.addAttribute("Name", user.getFirstname() + " " + user.getLastname());

        //TODO: Deliver the content of HTML through the api back to the HTML, not a if in the HTML.
        
        List<UserComments> commentsForUser = commentRepository.findByUser(user);
        if(commentsForUser.size() == 0) {
            model.addAttribute("comments", "No Comments for this user.");
        }
        
        
        //Admin, TODO: need to add permissions for the comments and courses to be able to delete them.
        if(user.getRole().equals("Admin")){
            // model.addAttribute("coursesList", )
            model.addAttribute("course", new Course());
            model.addAttribute("isAdmin", true);
        }

        
        return "/users/profile";
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id, HttpServletRequest session) {
        

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


}