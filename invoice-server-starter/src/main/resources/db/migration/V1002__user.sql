CREATE TABLE user
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NULL NOT NULL,
    admin    BOOLEAN DEFAULT FALSE,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

-- Index for user table (admin flag for filtering)
CREATE INDEX idx_user_admin ON user(admin);