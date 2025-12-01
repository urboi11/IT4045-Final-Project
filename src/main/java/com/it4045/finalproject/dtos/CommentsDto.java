package com.it4045.finalproject.dtos;
/**
 * A Data Transfer Object for Comments, used to encapsulate their content.
 * Includes userCommentId (integer) and comment (string) fields.
 * Uses Lombok annotations for boilerplate code reduction.
 * @see com.it4045.finalproject.entities.UserComments
 * @author Enterprise Application Development Final Project Group
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentsDto {
    private Integer userCommentId;

    private String comment;
}
