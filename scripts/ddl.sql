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

-- Tabla de solicitudes (pedidos realizados por mesa)
CREATE TABLE requests (
    id SERIAL PRIMARY KEY,
    table_id INT REFERENCES tables(id) ON DELETE SET NULL,
    request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    observations TEXT
);

-- Tabla de pedidos (relacionada con solicitudes y productos)
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    request_id INT REFERENCES requests(id) ON DELETE cascade NOT NULL,
    product_id INT REFERENCES products(id) ON DELETE SET NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    active BOOLEAN DEFAULT true
);

-- Tabla intermedia entre productos y usuarios
CREATE TABLE user_products (
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    product_id INT REFERENCES products(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, product_id)
);
