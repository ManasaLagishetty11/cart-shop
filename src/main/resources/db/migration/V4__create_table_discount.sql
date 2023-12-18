CREATE TABLE discount(
id INT PRIMARY KEY IDENTITY,
original_price FLOAT,
offer_price FLOAT,
discount_percentage FLOAT,
item_id INT NOT NULL FOREIGN KEY REFERENCES item(id),
is_discount_available BIT
);