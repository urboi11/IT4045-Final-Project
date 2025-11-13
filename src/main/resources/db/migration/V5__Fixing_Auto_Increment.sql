SET FOREIGN_KEY_CHECKS = 0;

alter table users
    modify userid int auto_increment;

alter table users
    auto_increment = 1;

alter table usercomments
    modify user_comment_id int auto_increment;

alter table usercomments
    auto_increment = 1;

alter table courses
    modify course_id int auto_increment;

alter table courses
    auto_increment = 1;

SET FOREIGN_KEY_CHECKS = 1;