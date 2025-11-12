CREATE TABLE usercomments
(
    user_comment_id INT          NOT NULL,
    userid          INT          NULL,
    course_id       INT          NULL,
    comment         VARCHAR(255) NULL,
    CONSTRAINT pk_usercomments PRIMARY KEY (user_comment_id)
);

ALTER TABLE usercomments
    ADD CONSTRAINT FK_USERCOMMENTS_ON_COURSEID FOREIGN KEY (course_id) REFERENCES courses (course_id);

ALTER TABLE usercomments
    ADD CONSTRAINT FK_USERCOMMENTS_ON_USERID FOREIGN KEY (userid) REFERENCES users (userid);