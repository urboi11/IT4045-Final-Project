CREATE TABLE courses
(
    course_id     INT          NOT NULL,
    course_number VARCHAR(255) NULL,
    course_name   VARCHAR(255) NULL,
    course_rating DOUBLE       NOT NULL,
    university    VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_courses PRIMARY KEY (course_id)
);