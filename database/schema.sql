-- ****************************************
-- Create tables and sequences for database
-- ****************************************

BEGIN;

CREATE SEQUENCE seq_customer_id;

CREATE TABLE customers (
  customer_id int PRIMARY KEY DEFAULT NEXTVAL('seq_customer_id'),
  company varchar(30) NOT NULL,
  address varchar(20) NOT NULL,
  city varchar(20) NOT NULL,
  state varchar(20) NOT NULL,
  zip int NOT NULL,
  created_at date NOT NULL,
  updated_at date 
);

CREATE SEQUENCE seq_project_id;

CREATE TABLE projects (
  project_id int PRIMARY KEY DEFAULT NEXTVAL('seq_project_id'), 
  project_name varchar(20) NOT NULL,
  customer_id int NOT NULL REFERENCES customers(customer_id),
  created_at date NOT NULL,
  updated_at date 
);

CREATE SEQUENCE seq_user_id;

CREATE TABLE users (
  user_id int PRIMARY KEY DEFAULT NEXTVAL('seq_user_id'),
  username varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
  email varchar(30) NOT NULL,
  created_at date NOT NULL,
  updated_at date 
);

CREATE SEQUENCE seq_task_id;

CREATE TABLE tasks (
  task_id int PRIMARY KEY DEFAULT NEXTVAL('seq_task_id'),
  project_id int NOT NULL REFERENCES projects(project_id),
  user_id int NOT NULL REFERENCES users(user_id),
  task_name varchar(30) NOT NULL,
  created_at date NOT NULL,
  updated_at date 
);

CREATE SEQUENCE seq_task_entries_id;

CREATE TABLE task_entries (
  task_entries_id int PRIMARY KEY DEFAULT NEXTVAL('seq_task_entries_id'),
  task_id int NOT NULL REFERENCES tasks(task_id),
  duration double precision NOT NULL,
  start_time date,
  created_at date NOT NULL,
  updated_at date 
);

COMMIT;
