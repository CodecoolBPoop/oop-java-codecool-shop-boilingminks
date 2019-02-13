DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS product CASCADE ;
DROP TABLE IF EXISTS product_category CASCADE;
DROP TABLE IF EXISTS supplier CASCADE ;
DROP TABLE IF EXISTS shopping_cart CASCADE ;
DROP TABLE IF EXISTS orders CASCADE ;


CREATE TABLE IF NOT EXISTS
users (id SERIAL PRIMARY KEY,
      first_name VARCHAR NOT NULL,
      last_name VARCHAR NOT NULL,
      email VARCHAR NOT NULL,
      password VARCHAR NOT NULL,
      address VARCHAR NOT NULL,
      address2 VARCHAR NOT NULL,
      state VARCHAR NOT NULL,
      zip VARCHAR NOT NULL,
      country VARCHAR NOT NULL,
      shipping VARCHAR NOT NULL,
      saveInfo BOOLEAN NOT NULL);

CREATE TABLE IF NOT EXISTS
product (id SERIAL PRIMARY KEY NOT NULL,
         name VARCHAR NOT NULL,
         description VARCHAR NOT NULL,
         price float NOT NULL,
         currency VARCHAR NOT NULL,
         supplier_id int NOT NULL,
         category_id int NOT NULL,
         image_name VARCHAR);

CREATE TABLE IF NOT EXISTS
product_category (id SERIAL PRIMARY KEY NOT NULL,
         name VARCHAR NOT NULL,
         description VARCHAR NOT NULL,
         department VARCHAR NOT NULL);

CREATE TABLE IF NOT EXISTS
supplier (id SERIAL PRIMARY KEY NOT NULL,
         name VARCHAR NOT NULL,
         description VARCHAR NOT NULL);

CREATE TABLE IF NOT EXISTS
shopping_cart (order_id int NOT NULL,
         product_id int NOT NULL,
         quantity int NOT NULL);

CREATE TABLE IF NOT EXISTS
orders (id SERIAL PRIMARY KEY NOT NULL,
        user_id int NOT NULL,
        validated BOOLEAN NOT NULL,
	payment_method VARCHAR NOT NULL);

ALTER TABLE ONLY product
ADD CONSTRAINT fk_product_category
FOREIGN KEY (category_id)
REFERENCES product_category(id);

ALTER TABLE ONLY product
ADD CONSTRAINT fk_supplier
FOREIGN KEY (supplier_id)
REFERENCES supplier(id);

ALTER TABLE ONLY shopping_cart
ADD CONSTRAINT fk_product_id
FOREIGN KEY (product_id)
REFERENCES product(id);

ALTER TABLE ONLY shopping_cart
ADD CONSTRAINT fk_order_id
FOREIGN KEY (order_id)
REFERENCES orders(id);

ALTER TABLE ONLY orders
ADD CONSTRAINT fk_user_id
FOREIGN KEY (user_id)
REFERENCES users(id);

