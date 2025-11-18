CREATE TABLE users
(
    userid         INT          NOT NULL,
    firstname VARCHAR(255) NULL,
    lastname  VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    `role`          VARCHAR(255)      NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (userid)
);