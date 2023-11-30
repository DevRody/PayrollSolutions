CREATE TABLE Employee_Frequency_Payment (
    employee_id INT,
    month_year VARCHAR(7),
    frequency_id INT,
    payment_id INT,
    PRIMARY KEY (employee_id, month_year),
    FOREIGN KEY (employee_id) REFERENCES Employee(id),
    FOREIGN KEY (frequency_id) REFERENCES Frequency_Control(id),
    FOREIGN KEY (payment_id) REFERENCES Payment_Information(id)
);