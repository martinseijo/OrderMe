-- Insertar roles predefinidos
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');


-- Insertar datos en la tabla orderme.product_types
INSERT INTO orderme.product_types (name, description) VALUES
('Entrantes', 'Aperitivos para comenzar la comida'),
('Tapas', 'Porciones pequeñas para compartir'),
('Raciones', 'Platos completos para compartir'),
('Postres', 'Dulces para terminar la comida'),
('Bebidas', 'Refrescos, cervezas, vinos, y más');

-- Insertar productos (relacionados con un tipo de producto y un usuario)
INSERT INTO products (name, description, price, product_type_id, user_id) VALUES
('Pan con tomate', 'Pan tostado con tomate fresco y aceite de oliva', 3.50, 1, 1),
('Ensalada César', 'Lechuga, pollo, queso parmesano y salsa César', 7.00, 1, 1),
('Tortilla Española', 'Tortilla con patatas y cebolla', 4.00, 2, 1),
('Croquetas de Jamón', 'Croquetas caseras rellenas de jamón', 6.00, 2, 1),
('Pulpo a la Gallega', 'Pulpo cocido con pimentón, aceite y sal', 12.00, 3, 1),
('Calamares a la Romana', 'Calamares rebozados y fritos', 10.00, 3, 1),
('Tarta de Queso', 'Tarta casera de queso con base de galleta', 5.00, 4, 1),
('Helado de Vainilla', 'Helado artesano de vainilla', 3.50, 4, 1),
('Coca Cola', 'Refresco clásico de cola', 2.50, 5, 1),
('Cerveza Artesanal', 'Cerveza local de producción artesanal', 4.00, 5, 1);

-- Insertar mesas asociadas a usuarios
INSERT INTO tables (number, name, description, user_id) VALUES
(1, 'Mesa Ventana', 'Mesa junto a la ventana con vista al exterior', 1),
(2, NULL, 'Mesa cerca de la barra', 1),
(3, 'Mesa Terraza', 'Mesa en la terraza al aire libre', 1),
(4, NULL, NULL, 2),
(5, 'Mesa VIP', 'Mesa reservada en área privada', 2);  -- Usuario 3 (asociado al producto 6, Calamares a la Romana)

INSERT INTO order_status (name) VALUES
('PENDING'),
('SERVED'),
('CANCELED'),
('PAID');

INSERT INTO orders (table_id, product_id, quantity, observations, status_id)
VALUES (1, 5, 2, 'Sin cebolla', 1);

INSERT INTO orders (table_id, product_id, quantity, observations, status_id)
VALUES (2, 3, 1, 'Extra queso', 2);

INSERT INTO orders (table_id, product_id, quantity, observations, status_id)
VALUES (3, 7, 3, 'Sin sal', 3);

INSERT INTO orders (table_id, product_id, quantity, observations, status_id)
VALUES (4, 2, 4, NULL, 1);

INSERT INTO orders (table_id, product_id, quantity, observations, status_id)
VALUES (5, 9, 2, 'Añadir picante', 3);
