CREATE SCHEMA test;

USE test;

CREATE SEQUENCE products_seq START WITH 1 INCREMENT BY 1;

create table products
(
    id bigint auto_increment primary key,
    name varchar(255),
    description varchar(255),
    price double,
    quantity int
);

INSERT INTO products (id, name, description, price, quantity) VALUES (1, 'Apple I phone', 'IOS device', 50000, 10);
INSERT INTO products (id, name, description, price, quantity) VALUES (2, 'Samsung S23', 'Android device', 90000, 75);
INSERT INTO products (id, name, description, price, quantity) VALUES (3, 'One Plus 6T', 'Android device', 45000, 50);
INSERT INTO products (id, name, description, price, quantity) VALUES (4, 'Noting One', 'NOS device', 30000, 100);
INSERT INTO products (id, name, description, price, quantity) VALUES (5, 'Google Pixel', 'Google device', 55000, 5);

ALTER SEQUENCE products_seq RESTART WITH 6;
