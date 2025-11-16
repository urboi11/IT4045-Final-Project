CREATE TABLE users
(
    user_id         INT          NOT NULL,
    user_first_name VARCHAR(255) NULL,
    user_last_name  VARCHAR(255) NULL,
    user_email      VARCHAR(255) NULL,
    user_pass       VARCHAR(255) NULL,
    is_admin        BIT(1)       NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);