DROP TABLE people IF EXISTS;

CREATE TABLE people  (
    
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    primary key (first_name, last_name)
);