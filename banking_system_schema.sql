-- ============================================
-- Silver Valley Bank - Banking System
-- Database Schema for MySQL (XAMPP)
-- ============================================

CREATE DATABASE IF NOT EXISTS banking_system;
USE banking_system;

-- ============================================
-- Table 1: signup
-- Purpose: Personal details (Page 1 of registration)
-- ============================================
DROP TABLE IF EXISTS signupthree;
DROP TABLE IF EXISTS signuptwo;
DROP TABLE IF EXISTS login;
DROP TABLE IF EXISTS bank;
DROP TABLE IF EXISTS signup;

CREATE TABLE signup (
    form_no     VARCHAR(10) PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    father      VARCHAR(100),
    dob         VARCHAR(30),
    gender      VARCHAR(10),
    email       VARCHAR(100),
    martial     VARCHAR(20),
    address     VARCHAR(200),
    city        VARCHAR(50),
    state       VARCHAR(50),
    pincode     VARCHAR(10)
);

-- ============================================
-- Table 2: signuptwo
-- Purpose: Additional details (Page 2 of registration)
-- ============================================
CREATE TABLE signuptwo (
    form_no     VARCHAR(10) PRIMARY KEY,
    religion    VARCHAR(20),
    category    VARCHAR(20),
    income      VARCHAR(30),
    education   VARCHAR(30),
    occupation  VARCHAR(30),
    pan         VARCHAR(15),
    aadhar      VARCHAR(15),
    senior      VARCHAR(5),
    account     VARCHAR(5)
);

-- ============================================
-- Table 3: signupthree
-- Purpose: Account type, card, PIN, services (Page 3)
-- ============================================
CREATE TABLE signupthree (
    form_no      VARCHAR(10) PRIMARY KEY,
    account_type VARCHAR(40),
    card_no      VARCHAR(20),
    pin          VARCHAR(10),
    facility     VARCHAR(300),
    declaration  VARCHAR(20)
);

-- ============================================
-- Table 4: login
-- Purpose: Card number + PIN authentication
-- ============================================
CREATE TABLE login (
    form_no  VARCHAR(10),
    card_no  VARCHAR(20) UNIQUE NOT NULL,
    pin      VARCHAR(10) NOT NULL
);

-- ============================================
-- Table 5: bank
-- Purpose: Transaction ledger (deposits & withdrawals)
-- ============================================
CREATE TABLE bank (
    pin              VARCHAR(10) NOT NULL,
    date             VARCHAR(100) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    amount           VARCHAR(20) NOT NULL
);

-- ============================================
-- Verify
-- ============================================
SHOW TABLES;
