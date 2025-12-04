-- CATEGORIES
INSERT INTO categories (id, name)
VALUES
(1, 'Electronics'),
(2, 'Pets');

-- SUPPLIERS
INSERT INTO suppliers (id, name)
VALUES
(1, 'Acme Co'),
(2, 'Random Inc');

-- PRODUCTS (10 st)
INSERT INTO products (id, name, price, category_id)
VALUES
(1, 'Laptop', 12999, 1),
(2, 'Tablet', 3999, 1),
(3, 'Rabbit', 50, 2),
(4, 'Monitor', 2499, 1),
(5, 'Keyboard', 499, 1),
(6, 'Mouse', 299, 1),
(7, 'Gaming Headset', 1299, 1),
(8, 'Hamster', 40, 2),
(9, 'Dog Food', 299, 2),
(10, 'Smartwatch', 1999, 1);

-- PRODUCT ↔ SUPPLIERS (many-to-many)
INSERT INTO product_suppliers (product_id, supplier_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 2),

(4, 1),
(5, 1),
(6, 1),
(7, 2),
(8, 2),
(9, 2),
(10, 1);

-- PRODUCT DETAILS (one-to-one)
INSERT INTO product_details (id, description, manufacturer, product_id)
VALUES
(1, 'High-end gaming laptop', 'ACME Computers', 1),
(2, 'Low-end surf tablet', 'Temu', 2),
(3, 'A rabbit caught in the wild', 'Mother Nature', 3),

(4, '24-inch 1080p Monitor', 'Acme Displays', 4),
(5, 'Mechanical keyboard', 'KeyPro', 5),
(6, 'Wireless ergonomic mouse', 'ClickTech', 6),
(7, 'Surround sound gaming headset', 'SoundStorm', 7),
(8, 'Small fluffy hamster', 'PetWorld', 8),
(9, 'Premium dog food', 'PetFoods Inc', 9),
(10, 'Fitness smartwatch', 'Acme Wearables', 10);

-- ROLES
INSERT INTO roles(name) VALUES ('ROLE_USER');
INSERT INTO roles(name) VALUES ('ROLE_ADMIN');

-- USERS
INSERT INTO users(username, password) VALUES
('test', '$2a$12$VAbgw3A6dpd75tEUudrqXOlelyuqecc54N9WNfxN7zStPQV7aY8EO'),  -- 1234
('admin', '$2a$12$CXqSeBneszgxnW96G8KDZuk44Px5KeBTN3BHGnZKqv1jv8nkZBrTC'); -- admin123

-- USER ROLES
INSERT INTO user_roles(user_id, role_id) VALUES
(1, 1),  -- test → USER
(2, 1),  -- admin → USER
(2, 2);  -- admin → ADMIN

INSERT INTO orders(item,user_id) VALUES ("order 1", 1),("order 2", 1),("order 3",2),("order 4",2)


