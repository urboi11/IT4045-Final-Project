CREATE TABLE users
(
    userid    INT AUTO_INCREMENT NOT NULL,
    firstname VARCHAR(255)       NULL,
    lastname  VARCHAR(255)       NULL,
    email     VARCHAR(255)       NULL,
    password  VARCHAR(255)       NULL,
    `role`    VARCHAR(255)       NULL,
    CONSTRAINT pk_users PRIMARY KEY (userid)
);