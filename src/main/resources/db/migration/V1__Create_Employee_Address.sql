CREATE TABLE Address (
    id SERIAL PRIMARY KEY,
    street_address VARCHAR(255),
    city VARCHAR(100),
    postal_code VARCHAR(10),
    neighborhood VARCHAR(100),
    house_number VARCHAR(10)
);

CREATE TABLE Employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    document VARCHAR(20) UNIQUE,
    job_title VARCHAR(50),
    base_salary DECIMAL(10, 2),
    phone VARCHAR(20),
    birth_date DATE,
    address_id INT,
    FOREIGN KEY (address_id) REFERENCES Address(id)
);