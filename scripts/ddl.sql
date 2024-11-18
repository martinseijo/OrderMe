-- Crear esquema (solo si no existe)
CREATE SCHEMA IF NOT EXISTS orderme;
SET search_path = orderme;

-- Tabla de roles (cada usuario tendrá un único rol)
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Tabla de usuarios (con relación 1:N hacia roles)
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    enabled BOOLEAN DEFAULT true,
    role_id INT REFERENCES roles(id) ON DELETE SET NULL,
    CONSTRAINT uc_username_email UNIQUE (username, email)
);

-- Tabla de tipo de producto
CREATE TABLE product_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

-- Tabla de productos (relacionada con tipo de producto)
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    product_type_id INT REFERENCES product_types(id) ON DELETE SET NULL
);

-- Tabla de mesas
CREATE TABLE tables (
    id SERIAL PRIMARY KEY,
    number INT NOT NULL UNIQUE,
    name VARCHAR(50),
    description TEXT
);

-- Relación N:M entre usuarios y mesas (gestores asignados a mesas)
CREATE TABLE user_tables (
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    table_id INT REFERENCES tables(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, table_id)
);

-- Tabla intermedia entre productos y usuarios
CREATE TABLE user_products (
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    product_id INT REFERENCES products(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, product_id)
);

CREATE TABLE order_status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    table_id INT REFERENCES tables(id) ON DELETE SET NULL,
    product_id INT REFERENCES products(id) ON DELETE RESTRICT,
    quantity INT NOT NULL CHECK (quantity > 0),
    observations TEXT,
    request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_id INT REFERENCES order_status(id) NOT NULL,
    CONSTRAINT unique_order_per_table UNIQUE (table_id, product_id, request_time)
);
