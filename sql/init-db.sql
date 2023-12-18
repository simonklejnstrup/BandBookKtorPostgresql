CREATE TABLE IF NOT EXISTS book
(
    id   SERIAL PRIMARY KEY,
    name varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS people
(
    id   SERIAL PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO book(name) VALUES ('Book #1');
INSERT INTO book(name) VALUES ('Book #2');

INSERT INTO people(firstname, lastname, email) VALUES ('firstname #1', 'lastname #1', 'email #1');
INSERT INTO people(firstname, lastname, email) VALUES ('firstname #2', 'lastname #2', 'email #2');