CREATE TABLE Frequency_Control (
    id SERIAL PRIMARY KEY,
    worked_hours DECIMAL(8, 2),
    absences INT,
    justified_absences INT
);