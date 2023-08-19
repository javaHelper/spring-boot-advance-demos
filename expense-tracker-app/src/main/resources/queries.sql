CREATE DATABASE expensetracker;

USE expensetracker;

CREATE TABLE tbl_expenses
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    expense_name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    expense_amount DOUBLE(5, 2) NOT NULL,
    category VARCHAR(255) NOT NULL,
    date DATE NOT NULL
);

INSERT INTO tbl_expenses(expense_name, description, expense_amount, category, date)
VALUES("Water bill", "water bill", 600.00, "Bills", "2021-10-14");

INSERT INTO tbl_expenses(expense_name, description, expense_amount, category, date)
VALUES("Electricity bill", "electricity bill", 900.00, "Bills", "2021-10-13");