-- This seed.sql is a placeholder and currently does not contain accurate seed data.
-- It will be populated with dummy data for testing purposes in the future.

-- Seed data for users table
INSERT INTO users (first_name, last_name, email, phone, username, password) VALUES 
('Caroline', 'Horne', 'carolinehorne2@gmail.com', '9735900418', 'funkiistraw', 'hashed_password_here'),
('Adam', 'Mills', 'adammills@example.com', '0987654321', 'djmillzy', 'another_hashed_password'),
('Charles', 'Evans', 'charlesevans@example.com', '1122334455', 'wharlez', 'yet_another_hashed_password');

-- Seed data for potholes table (using fictional latitude and longitude data)
INSERT INTO potholes (latitude, longitude, status) VALUES
(34.0522, -118.2437, 'Reported'),    -- Example: Los Angeles
(40.7306, -73.9352, 'Confirmed'),   -- Example: New York
(37.7749, -122.4194, 'Repaired');   -- Example: San Francisco

-- Seed data for reports table (connecting the users to the potholes they reported)
-- Assuming the IDs from the inserts above, John reported the first pothole, Jane reported the second, and Alice reported the third
INSERT INTO reports (user_id, pothole_id) VALUES
(1, 1),
(2, 2),
(3, 3);
