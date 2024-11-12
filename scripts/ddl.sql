-- Tabla de roles
CREATE TABLE orderme.roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Tabla de usuarios
CREATE TABLE orderme.users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    enabled BOOLEAN DEFAULT true,
    CONSTRAINT uc_username_email UNIQUE (username, email)
);

-- Tabla intermedia para usuarios y roles (relación muchos a muchos)
CREATE TABLE orderme.user_roles (
    user_id INT REFERENCES users(id),
    role_id INT REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);