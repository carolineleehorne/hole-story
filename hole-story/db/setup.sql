CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50) UNIQUE,
    phone VARCHAR(10) UNIQUE,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE potholes (
    pothole_id SERIAL PRIMARY KEY,
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6),
    status VARCHAR(50)
);

CREATE TABLE reports (
    report_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    pothole_id INT REFERENCES potholes(pothole_id),
    report_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);