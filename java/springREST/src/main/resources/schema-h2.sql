CREATE TABLE expenses(
  expense_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  expense_value FLOAT UNSIGNED NOT NULL DEFAULT 0.0,
  expense_reoccurring TINYINT DEFAULT 0,
  creation_date DATE
);


CREATE TABLE income(
  income_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  income_value FLOAT UNSIGNED NOT NULL DEFAULT 0.0,
  income_reoccurring TINYINT DEFAULT 0,
  creation_date DATE
);
