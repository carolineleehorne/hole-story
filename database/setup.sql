CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50) UNIQUE, -- set email requirements
    phone VARCHAR(10),
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100) -- set password requirements
);

CREATE TABLE potholes (
    pothole_id SERIAL PRIMARY KEY,
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6),
    status VARCHAR(50) -- Example: Reported, Confirmed, Repaired
);

CREATE TABLE reports (
    report_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    pothole_id INT REFERENCES potholes(pothole_id),
    report_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- We're linking the reports table to both users and potholes to keep track of
-- who reported a particular pothole and when it was reported.