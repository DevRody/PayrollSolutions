CREATE TABLE UserLogin (
    id SERIAL PRIMARY KEY,
    document VARCHAR(20) UNIQUE,
    password VARCHAR(100),
    role VARCHAR(20),
    employee_id INT UNIQUE NULL,
    FOREIGN KEY (employee_id) REFERENCES Employee(id)
);