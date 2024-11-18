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

-- Insertar datos en la tabla orderme.products
INSERT INTO orderme.products (name, description, price, product_type_id) VALUES
('Pan con tomate', 'Pan tostado con tomate fresco y aceite de oliva', 3.50, 1),
('Ensalada César', 'Lechuga, pollo, queso parmesano y salsa César', 7.00, 1),
('Tortilla Española', 'Tortilla con patatas y cebolla', 4.00, 2),
('Croquetas de Jamón', 'Croquetas caseras rellenas de jamón', 6.00, 2),
('Pulpo a la Gallega', 'Pulpo cocido con pimentón, aceite y sal', 12.00, 3),
('Calamares a la Romana', 'Calamares rebozados y fritos', 10.00, 3),
('Tarta de Queso', 'Tarta casera de queso con base de galleta', 5.00, 4),
('Helado de Vainilla', 'Helado artesano de vainilla', 3.50, 4),
('Coca Cola', 'Refresco clásico de cola', 2.50, 5),
('Cerveza Artesanal', 'Cerveza local de producción artesanal', 4.00, 5); -- Bebidas

-- Insertar datos en la tabla orderme.tables
INSERT INTO orderme.tables (number, name, description) VALUES
(1, 'Mesa Ventana', 'Mesa junto a la ventana con vista al exterior'),
(2, NULL, 'Mesa cerca de la barra'),
(3, 'Mesa Terraza', 'Mesa en la terraza al aire libre'),
(4, NULL, NULL),
(5, 'Mesa VIP', 'Mesa reservada en área privada');

-- Insertar datos en la tabla orderme.requests
INSERT INTO orderme.requests (table_id, request_time, observations) VALUES
(1, '2024-11-16 12:30:00', 'Sin cebolla en la ensalada, por favor'),
(3, '2024-11-16 13:00:00', 'Traer una vela para el postre'),
(5, '2024-11-16 13:15:00', 'Añadir hielo a la bebida'),
(2, '2024-11-16 13:30:00', 'Platos sin gluten'),
(4, '2024-11-16 14:00:00', 'Café después del postre');

-- Insertar datos en la tabla orderme.orders
INSERT INTO orderme.orders (request_id, product_id, quantity, active) VALUES
(1, 1, 2, true),   -- Pan con tomate (2 unidades)
(1, 2, 1, true),   -- Ensalada César (1 unidad)
(2, 7, 1, true),   -- Tarta de Queso (1 unidad)
(3, 10, 2, true),  -- Cerveza Artesanal (2 unidades)
(3, 9, 1, true),   -- Coca-Cola (1 unidad)
(4, 4, 3, true),   -- Croquetas de Jamón (3 unidades)
(5, 8, 2, true),   -- Helado de Vainilla (2 unidades)
(5, 9, 1, false);  -- Coca-Cola (1 unidad, ya atendido)

-- Insertar datos de prueba en la tabla user_products
INSERT INTO orderme.user_products (user_id, product_id) VALUES
(1, 1),  -- Usuario 1 (asociado al producto 1, por ejemplo, Pan con tomate)
(1, 2),  -- Usuario 1 (asociado al producto 2, Ensalada César)
(2, 3),  -- Usuario 2 (asociado al producto 3, Tortilla Española)
(2, 4),  -- Usuario 2 (asociado al producto 4, Croquetas de Jamón)
(3, 5),  -- Usuario 3 (asociado al producto 5, Pulpo a la Gallega)
(3, 6);  -- Usuario 3 (asociado al producto 6, Calamares a la Romana)
