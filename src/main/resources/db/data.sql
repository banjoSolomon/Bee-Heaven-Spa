-- Truncate all tables
TRUNCATE TABLE notification CASCADE;
TRUNCATE TABLE payment CASCADE;
TRUNCATE TABLE bookings CASCADE;
TRUNCATE TABLE therapist CASCADE;
TRUNCATE TABLE services CASCADE;
TRUNCATE TABLE clients CASCADE;
TRUNCATE TABLE available_therapist CASCADE;

-- Insert sample data into the clients table
INSERT INTO clients (id, name, email, phone_number, time_created, time_updated)
VALUES
    (2, 'solomon', 'ayomidebanjo02@gmail.com', '09034562345', '2024-06-04T15:03:03.792', '2025-06-04T15:03:03.792'),
(9, 'solomon', 'ayomidebanjo02@gmail.com', '09034562345', '2024-06-04T15:03:03.792', '2025-06-04T15:03:03.792');

-- Insert sample data into the service table
INSERT INTO services (id, service_types, description, duration, price)
VALUES
    (1, 'MASSAGE', 'Full-body relaxation massage', '60 minutes', 100.00),
    (2, 'FACIALS', 'Rejuvenating facial treatment', '45 minutes', 75.00);

-- Insert sample data into the therapist table
INSERT INTO therapist (id, name, email, phone_number, rating, availability_schedule, service_id)
VALUES
    (9, 'Alice Johnson', 'ayomidebanjo012@gmail.com', '08012345678', '4.8', 'Mon-Fri: 9am-5pm', 1),
    (6, 'Bob Smith', 'ayomidebanjo022@gmail.com', '08023456789', '4.5', 'Tue-Sat: 10am-6pm', 2);

-- Insert sample data into the bookings table
INSERT INTO bookings (id, client_id, service_id, therapist_id, time_appointment, payment_status, service_types, total_cost)
VALUES
    (100, 2, 6, 9, '2024-06-10T14:30:00', 'COMPLETED', 'MASSAGE', 22.00),
    (12, 2, 2, 6, '2024-06-12T10:30:00', 'PENDING', 'FACIALS', 100.00);

-- Insert sample data into the payment table
INSERT INTO payment (id, client_id, booking_id, amount, payment_status, transaction_date)
VALUES
    (5, 2, 1, 100.00, 'COMPLETED', '2024-06-10T14:30:00'),
    (6, 2, 2, 75.00, 'PENDING', '2024-06-12T10:30:00');

-- Insert sample data into the notifications table
INSERT INTO notification (id, clients_id, message, date_sent)
VALUES
    (100, 2, 'Your appointment is confirmed for June 10, 2024.', '2024-06-01T10:00:00');
-- Insert sample data into the available_therapist table
INSERT INTO available_therapist (id, name, email, phone_number, availability_schedule, availability)
VALUES
    (9, 'Alice Johnson', 'ayomidebanjo012@gmail.com', '08012345678', 'Mon-Fri: 9am-5pm', '2024-06-10T14:30:00'),
    (6, 'Bob Smith', 'ayomidebanjo022@gmail.com', '08023456789', 'Tue-Sat: 10am-6pm', '2026-06-10T14:30:00');
