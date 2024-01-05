CREATE TABLE app_user (
                          id SERIAL PRIMARY KEY,
                          firstname VARCHAR(50),
                          lastname VARCHAR(50),
                          email VARCHAR(100) UNIQUE,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE band (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(100)
);

CREATE TABLE user_band (
                           user_id INT REFERENCES app_user(id),
                           band_id INT REFERENCES band(id),
                           PRIMARY KEY (user_id, band_id)
);

CREATE TABLE comment (
                         id SERIAL PRIMARY KEY,
                         thread_id INT,  -- Vil referere til 'forum_thread' tabel
                         content TEXT,
                         created_by INT REFERENCES app_user(id),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE event (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(100),
                       address TEXT,
                       date DATE,
                       time_of_get_in TIME,
                       time_of_soundcheck TIME,
                       time_of_concert TIME,
                       time_of_done TIME,
                       salary_per_person DECIMAL,
                       cost_of_rental_gear DECIMAL,
                       cost_of_transport DECIMAL,
                       extra_costs DECIMAL,
                       length_of_each_set INT,
                       number_of_sets INT,
                       type VARCHAR(50)
);

CREATE TABLE contact_person (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(100),
                                telephone_number VARCHAR(20)
);

CREATE TABLE event_contact (
                               event_id INT REFERENCES event(id),
                               contact_id INT REFERENCES contact_person(id),
                               PRIMARY KEY (event_id, contact_id)
);

CREATE TABLE forum_thread (
                              id SERIAL PRIMARY KEY,
                              title VARCHAR(100),
                              created_by INT REFERENCES app_user(id),
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO app_user (firstname, lastname, email) VALUES
                                                      ('Alice', 'Johnson', 'alice.johnson@example.com'),
                                                      ('Bob', 'Smith', 'bob.smith@example.com');

INSERT INTO band (name) VALUES
                            ('The Rockers'),
                            ('The Jazzers');

INSERT INTO user_band (user_id, band_id) VALUES
                                             (1, 1),
                                             (2, 2);

INSERT INTO forum_thread (title, created_by) VALUES
                                                 ('Welcome to the Forum', 1),
                                                 ('Upcoming Events', 2);

INSERT INTO comment (thread_id, content, created_by) VALUES
                                                         (1, 'Thank you for creating this forum!', 2),
                                                         (2, 'Looking forward to the events!', 1);

INSERT INTO event (title, address, date, type) VALUES
                                                   ('Summer Music Festival', '123 Main St, Anytown', '2023-06-15', 'Outdoor'),
                                                   ('Winter Jazz Night', '456 Center Rd, Bigcity', '2023-12-10', 'Indoor');

INSERT INTO contact_person (name, telephone_number) VALUES
                                                        ('Emily Turner', '12345678'),
                                                        ('John Doe', '87654321');

INSERT INTO event_contact (event_id, contact_id) VALUES
                                                     (1, 1),
                                                     (2, 2);
