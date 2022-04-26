CREATE TABLE developers (
	developer_id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	age INT
);

CREATE TABLE skills (
	skill_id SERIAL PRIMARY KEY,
	language VARCHAR(50) NOT NULL,
	skill VARCHAR(50) NOT NULL
);

CREATE TABLE companies (
	company_id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	location VARCHAR(50)
);

CREATE TABLE projects (
	project_id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(250)
);

CREATE TABLE customers (
	customer_id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	location VARCHAR(100)
);

CREATE TABLE developers_skills (
	developer_id INT NOT NULL,
	skill_id INT NOT NULL,
	PRIMARY KEY (developer_id, skill_id),
	FOREIGN KEY (developer_id) REFERENCES developers(developer_id),
	FOREIGN KEY (skill_id) REFERENCES skills(skill_id)
);

CREATE TABLE developers_projects (
	developer_id INT NOT NULL,
	project_id INT NOT NULL,
	PRIMARY KEY (developer_id, project_id),
	FOREIGN KEY (developer_id) REFERENCES developers(developer_id),
	FOREIGN KEY (project_id) REFERENCES projects(project_id)
);

CREATE TABLE companies_projects (
	company_id INT NOT NULL,
	project_id INT NOT NULL,
	PRIMARY KEY (company_id, project_id),
	FOREIGN KEY (company_id) REFERENCES companies(company_id),
	FOREIGN KEY (project_id) REFERENCES projects(project_id)
);

CREATE TABLE customers_projects (
	customer_id INT NOT NULL,
	project_id INT NOT NULL,
	PRIMARY KEY (customer_id, project_id),
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
	FOREIGN KEY (project_id) REFERENCES projects(project_id)
);