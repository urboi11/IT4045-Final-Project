package com.it4045.finalproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentsDto {
    private Integer userCommentId;

    private String comment;
}
