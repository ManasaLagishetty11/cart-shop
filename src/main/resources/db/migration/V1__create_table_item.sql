CREATE TABLE item (
 id INT PRIMARY KEY IDENTITY,
 name VARCHAR(255),
 description VARCHAR(255),
 price FLOAT,
 category INT,
 number_of_items INT,
 status INT,
 is_available BIT,
);