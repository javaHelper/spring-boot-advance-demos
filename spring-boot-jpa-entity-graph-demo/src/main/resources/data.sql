-- Insert categories
INSERT INTO category (name) VALUES ('Electronics');
INSERT INTO category (name) VALUES ('Clothing');
INSERT INTO category (name) VALUES ('Groceries');

-- Insert products
INSERT INTO product (name, description, price, category_id)
VALUES ('Laptop', 'High performance laptop', 999.99, 1);
INSERT INTO product (name, description, price, category_id)
VALUES ('Smartphone', 'Latest model smartphone', 699.99, 1);
INSERT INTO product (name, description, price, category_id)
VALUES ('T-Shirt', 'Cotton t-shirt', 19.99, 2);
INSERT INTO product (name, description, price, category_id)
VALUES ('Jeans', 'Blue denim jeans', 49.99, 2);
INSERT INTO product (name, description, price, category_id)
VALUES ('Milk', 'Organic milk 1L', 2.99, 3);

-- Insert addons
INSERT INTO addon (name, additional_price) VALUES ('Extended Warranty', 99.99);
INSERT INTO addon (name, additional_price) VALUES ('Device Insurance', 49.99);
INSERT INTO addon (name, additional_price) VALUES ('Gift Wrapping', 4.99);

-- Insert customers
INSERT INTO customer (name, email) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO customer (name, email) VALUES ('Jane Smith', 'jane.smith@example.com');
INSERT INTO customer (name, email) VALUES ('Bob Johnson', 'bob.johnson@example.com');

-- Insert addresses
INSERT INTO address (street, city, state, zip_code, country, customer_id)
VALUES ('123 Main St', 'New York', 'NY', '10001', 'USA', 1);
INSERT INTO address (street, city, state, zip_code, country, customer_id)
VALUES ('456 Business Ave', 'New York', 'NY', '10005', 'USA', 1);
INSERT INTO address (street, city, state, zip_code, country, customer_id)
VALUES ('789 Park Blvd', 'Boston', 'MA', '02108', 'USA', 2);

-- Insert orders
INSERT INTO customer_order (order_date, total_amount, customer_id, shipping_address_id, billing_address_id)
VALUES (CURRENT_TIMESTAMP() - 3, 2449.95, 1, 1, 1);
INSERT INTO customer_order (order_date, total_amount, customer_id, shipping_address_id, billing_address_id)
VALUES (CURRENT_TIMESTAMP() - 1, 109.95, 2, 3, 3);
INSERT INTO customer_order (order_date, total_amount, customer_id)
VALUES (CURRENT_TIMESTAMP(), 14.95, 3);

-- Insert order items
INSERT INTO order_item (quantity, price, order_id, product_id) VALUES (1, 999.99, 1, 1);
INSERT INTO order_item (quantity, price, order_id, product_id) VALUES (2, 699.99, 1, 2);
INSERT INTO order_item (quantity, price, order_id, product_id) VALUES (3, 19.99, 2, 3);
INSERT INTO order_item (quantity, price, order_id, product_id) VALUES (1, 49.99, 2, 4);
INSERT INTO order_item (quantity, price, order_id, product_id) VALUES (5, 2.99, 3, 5);

-- Insert order item addons
INSERT INTO order_item_addon VALUES (1, 1); -- Laptop with warranty
INSERT INTO order_item_addon VALUES (1, 2); -- Laptop with insurance
INSERT INTO order_item_addon VALUES (2, 2); -- Smartphone with insurance
INSERT INTO order_item_addon VALUES (4, 3); -- Jeans with gift wrap

-- Insert payments
INSERT INTO payment (amount, payment_date, payment_method, order_id)
VALUES (2449.95, CURRENT_TIMESTAMP() - 3, 'CREDIT_CARD', 1);
INSERT INTO payment (amount, payment_date, payment_method, order_id)
VALUES (109.95, CURRENT_TIMESTAMP() - 1, 'PAYPAL', 2);
INSERT INTO payment (amount, payment_date, payment_method, order_id)
VALUES (14.95, CURRENT_TIMESTAMP(), 'DEBIT_CARD', 3);