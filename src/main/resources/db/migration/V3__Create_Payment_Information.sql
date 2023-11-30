CREATE TABLE Payment_Information (
    id SERIAL PRIMARY KEY,
    gross_salary DECIMAL(10, 2),
    additional DECIMAL(10, 2),
    discounts DECIMAL(10, 2),
    inss_salary DECIMAL(10, 2),
    irrf_salary DECIMAL(10, 2),
    net_salary DECIMAL(10, 2),
    payment_date DATE
);