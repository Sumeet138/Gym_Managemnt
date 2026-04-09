-- ============================================
-- FitZone Gym Management System
-- Database Schema for MySQL 8.0+
-- ============================================

-- Create database
CREATE DATABASE IF NOT EXISTS gym_management_system;
USE gym_management_system;

-- ============================================
-- Table 1: login
-- Purpose: Member authentication credentials
-- ============================================
DROP TABLE IF EXISTS login;
CREATE TABLE login (
    form_no VARCHAR(10) PRIMARY KEY,
    member_id VARCHAR(15) UNIQUE NOT NULL,
    pin VARCHAR(10) NOT NULL
);

-- ============================================
-- Table 2: member
-- Purpose: Personal and contact details
-- ============================================
DROP TABLE IF EXISTS member;
CREATE TABLE member (
    form_no VARCHAR(10) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    dob VARCHAR(20) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    emergency_contact VARCHAR(15) NOT NULL,
    address VARCHAR(200) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    pincode VARCHAR(10) NOT NULL
);

-- ============================================
-- Table 3: member_health
-- Purpose: Health and medical information
-- ============================================
DROP TABLE IF EXISTS member_health;
CREATE TABLE member_health (
    form_no VARCHAR(10) PRIMARY KEY,
    blood_group VARCHAR(5) NOT NULL,
    height_cm VARCHAR(10) NOT NULL,
    weight_kg VARCHAR(10) NOT NULL,
    bmi VARCHAR(10),
    medical_conditions VARCHAR(200),
    previous_injuries VARCHAR(200),
    fitness_goal VARCHAR(50) NOT NULL,
    experience_level VARCHAR(30) NOT NULL,
    preferred_time VARCHAR(50) NOT NULL,
    allergies VARCHAR(200)
);

-- ============================================
-- Table 4: membership
-- Purpose: Membership plan and services
-- ============================================
DROP TABLE IF EXISTS membership;
CREATE TABLE membership (
    form_no VARCHAR(10) PRIMARY KEY,
    plan_type VARCHAR(30) NOT NULL,
    member_id VARCHAR(15) UNIQUE NOT NULL,
    pin VARCHAR(10) NOT NULL,
    services VARCHAR(300),
    join_date DATE,
    expiry_date DATE,
    declaration VARCHAR(20)
);

-- ============================================
-- Table 5: payments
-- Purpose: Wallet transactions (credits & debits)
-- ============================================
DROP TABLE IF EXISTS payments;
CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    member_id VARCHAR(15) NOT NULL,
    transaction_date DATETIME NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    description VARCHAR(100) NOT NULL,
    amount INT NOT NULL
);

-- ============================================
-- Table 6: bookings
-- Purpose: Class booking records
-- ============================================
DROP TABLE IF EXISTS bookings;
CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id VARCHAR(15) NOT NULL,
    booking_date DATETIME NOT NULL,
    class_type VARCHAR(50) NOT NULL,
    amount INT NOT NULL,
    status VARCHAR(20) DEFAULT 'Confirmed'
);

-- ============================================
-- Verify tables created successfully
-- ============================================
SHOW TABLES;

-- ============================================
-- Sample data for testing (Optional)
-- ============================================

-- Insert a sample member for testing
-- Uncomment below to add test data

/*
-- Sample login
INSERT INTO login VALUES ('1234', '5040936789', '1234');

-- Sample member
INSERT INTO member VALUES (
    '1234', 'Test User', '9876543210', '01/15/1995', 'Male',
    'test@example.com', '9876543211', '123 Test Street',
    'Mumbai', 'Maharashtra', '400001'
);

-- Sample health record
INSERT INTO member_health VALUES (
    '1234', 'O+', '175', '70', '22.86', 'None', 'None',
    'Weight Loss', 'Beginner', 'Morning (6-10 AM)', 'None'
);

-- Sample membership
INSERT INTO membership VALUES (
    '1234', 'Monthly', '5040936789', '1234',
    'Personal Trainer Yoga Classes', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), 'Agreed'
);

-- Sample payment (initial deposit)
INSERT INTO payments VALUES (
    NULL, '5040936789', NOW(), 'Credit', 'Initial Wallet Top-Up', 10000
);

-- Sample booking
INSERT INTO bookings VALUES (
    NULL, '5040936789', NOW(), 'Yoga', 300, 'Confirmed'
);
*/

-- ============================================
-- Useful queries for verification
-- ============================================

-- Check all tables exist
-- SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES 
-- WHERE TABLE_SCHEMA = 'gym_management_system' ORDER BY TABLE_NAME;

-- Calculate wallet balance for a member
-- SELECT 
--     SUM(CASE WHEN transaction_type = 'Credit' THEN amount ELSE -amount END) as balance
-- FROM payments WHERE member_id = '5040936789';

-- Get member's booking history
-- SELECT b.*, m.full_name 
-- FROM bookings b 
-- JOIN membership ms ON b.member_id = ms.member_id
-- JOIN member m ON ms.form_no = m.form_no
-- WHERE b.member_id = '5040936789' ORDER BY b.booking_date DESC;
