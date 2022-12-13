CREATE TABLE users(
   id serial PRIMARY KEY,
   firstname VARCHAR (50),
   lastname VARCHAR (50),
   email varchar(50)
);

CREATE TABLE product(
   id serial PRIMARY KEY,
   description VARCHAR (500),
   price numeric (10,2) NOT NULL,
   qty_available integer NOT NULL
);

CREATE TABLE purchase_order(
    id serial PRIMARY KEY,
    user_id integer references users (id),
    product_id integer references product (id),
    price numeric (10,2) NOT NULL
);

INSERT INTO public.users (id, firstname, lastname, email) VALUES(1, 'Laxmi', 'Parate', 'laxmi.parate@gmail.com');
INSERT INTO public.users (id, firstname, lastname, email) VALUES(2, 'Pallavi', 'Mohadikar', 'pallavi.mohadikar@gmail.com');
INSERT INTO public.users (id, firstname, lastname, email) VALUES(3, 'Harish', 'Nimje', 'harish.nimje@gmail.com');
INSERT INTO public.users (id, firstname, lastname, email) VALUES(4, 'Sagar', 'Nandankar', 'sagar.nandankar@gmail.com');
INSERT INTO public.users (id, firstname, lastname, email) VALUES(5, 'Sankalp', 'Dekate', 'sankalp.dekate@gmail.com');

INSERT INTO public.product (id, description, price, qty_available) VALUES(1, 'IPad', 300.00, 10);
INSERT INTO public.product (id, description, price, qty_available) VALUES(2, 'IPhone', 650.00, 50);
INSERT INTO public.product (id, description, price, qty_available) VALUES(3, 'Sony TV', 320.00, 100);

INSERT INTO public.purchase_order (id, user_id, product_id, price) VALUES(1, 1, 1, 500.00);
INSERT INTO public.purchase_order (id, user_id, product_id, price) VALUES(2, 2, 2, 100.00);
INSERT INTO public.purchase_order (id, user_id, product_id, price) VALUES(3, 3, 3, 100.00);