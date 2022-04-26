INSERT INTO developers (name, age)
VALUES
('Bob Smith', 22),
('Nick Brown', 35),
('John Wilson', 27),
('Anna Davis', 25),
('Henry Jackobson', 38),
('Emma Hamilton', 30),
('Olivia Fox', 24),
('Axl Rose', 29);

INSERT INTO skills (language, skill)
VALUES
('Java', 'Junior'),
('Java', 'Middle'),
('Java', 'Senior'),
('C++', 'Junior'),
('C++', 'Middle'),
('C++', 'Senior'),
('JS', 'Junior'),
('JS', 'Middle'),
('JS', 'Senior');


INSERT INTO companies (name, location)
VALUES
('Company_1', 'Kyiv'),
('Company_2', 'Lviv'),
('Company_3', 'Kharkiv');

INSERT INTO projects (name, description)
VALUES
('CRM', 'Some new CRM-system'),
('TB', 'TeleBot for travel'),
('DescApp', 'New desctop application for something');

INSERT INTO customers (name, location)
VALUES
('Customer_1', 'Dnipro'),
('Customer_2', 'Kherson'),
('Customer_3', 'Ternopil');

INSERT INTO developers_skills (developer_id, skill_id)
VALUES
(1, 5),
(2, 3),
(2, 6),
(3, 1),
(4, 8),
(5, 4),
(5, 2),
(6, 2),
(7, 7),
(8, 9);

INSERT INTO developers_projects (developer_id, project_id)
VALUES
(1, 1),
(2, 3),
(3, 2),
(4, 3),
(5, 2),
(6, 1),
(7, 1),
(8, 2);

INSERT INTO companies_projects (company_id, project_id)
VALUES
(1, 3),
(2, 1),
(3, 2);

INSERT INTO customers_projects (customer_id, project_id)
VALUES
(1, 3),
(2, 2),
(3, 1);

ALTER TABLE developers
ADD salary INT;

UPDATE developers
SET salary = 4000
WHERE developer_id = 1;

UPDATE developers
SET salary = 7000
WHERE developer_id = 2;

UPDATE developers
SET salary = 700
WHERE developer_id = 3;

UPDATE developers
SET salary = 1500
WHERE developer_id = 4;

UPDATE developers
SET salary = 3300
WHERE developer_id = 5;

UPDATE developers
SET salary = 1700
WHERE developer_id = 6;

UPDATE developers
SET salary = 500
WHERE developer_id = 7;

UPDATE developers
SET salary = 2400
WHERE developer_id = 8;

ALTER TABLE projects
ADD creation_date DATE;

UPDATE projects
SET creation_date = '10.15.2020'
WHERE project_id = 1;

UPDATE projects
SET creation_date = '2019-05-13'
WHERE project_id = 2;

UPDATE projects
SET creation_date = '08-Mar-2021'
WHERE project_id = 3;
