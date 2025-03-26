CREATE TABLE person (
	id int8 GENERATED ALWAYS AS IDENTITY NOT NULL,
	first_name varchar NULL,
	last_name varchar NULL,
	email varchar NULL,
	CONSTRAINT person_pk PRIMARY KEY (id)
);