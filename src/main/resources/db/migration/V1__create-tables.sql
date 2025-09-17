CREATE TABLE users(
    user_id UUID PRIMARY KEY,
    user_email VARCHAR,
    user_password VARCHAR,
    username VARCHAR,
    created_at TIMESTAMP(3),
    updated_at TIMESTAMP(3),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);
CREATE TABLE roles(
    role_id UUID PRIMARY KEY,
    role_name VARCHAR
);
CREATE TABLE user_roles (
    user_id UUID,
    role_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);


