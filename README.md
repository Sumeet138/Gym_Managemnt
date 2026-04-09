# FitZone - Gym & Fitness Center Management System

A Java desktop application for gym membership management, wallet payments, and class bookings — built with Java Swing and MySQL.

---

## Table of Contents

- [Abstract](#abstract)
- [Tech Stack](#tech-stack)
- [System Architecture](#system-architecture)
- [Features](#features)
- [Database Schema](#database-schema)
- [Installation & Setup](#installation--setup)
- [Usage](#usage)
- [Security](#security)
- [Project Structure](#project-structure)

---

## Abstract

FitZone is a comprehensive gym membership management platform providing:
- Wallet-based payment system with full transaction history
- Class booking with automatic balance validation
- Health tracking with BMI auto-calculation
- Multi-step member registration with form validation
- Secure authentication with SQL injection prevention (PreparedStatement)

---

## Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Java (JDK 8+, JDK 21 recommended) |
| GUI | Java Swing |
| Database | MySQL 8.0+ |
| JDBC Driver | MySQL Connector/J 9.3.0 |
| Date Picker | JCalendar 1.4 |
| IDE | IntelliJ IDEA / NetBeans / Eclipse |
| DB Server | XAMPP MySQL |
| Backend Framework | Spring Boot 3.x |

**External JARs (place in `lib/`):**
- `mysql-connector-j-9.3.0.jar`
- `jcalendar-1.4.jar`

---

## System Architecture

```
FitZone Gym Management System
│
├── GUI Layer (Java Swing)
│   ├── Main.java                          Entry point
│   ├── Login.java → Dashboard.java        Existing members
│   ├── SignUp → SignUpTwo → SignUpThree → AddFunds   New member flow
│   └── Dashboard operations:
│       ├── AddFunds.java
│       ├── RequestRefund.java
│       ├── QuickBook.java
│       ├── WalletBalance.java
│       ├── PaymentHistory.java
│       └── ChangePassword.java
│
├── Database Layer
│   └── Conn.java (PreparedStatement throughout)
│
└── MySQL Database: gym_management_system
    ├── login
    ├── member
    ├── member_health
    ├── membership
    ├── payments
    └── bookings
```

---

## Features

### Member Authentication
- Login with 10-digit Member ID + 4-digit PIN
- 3-step registration wizard (Personal → Health → Membership)
- Auto-generated unique Member ID and PIN on signup

### Wallet & Payments
- **Add Funds** — Top-up wallet (Rs.100 – Rs.50,000)
- **Request Refund** — Withdraw funds with balance validation
- **Wallet Balance** — Current balance with transaction breakdown
- **Payment History** — Full audit trail, color-coded entries

### Class Booking
- **Quick Book** — Instant booking for 6 preset classes:
  - Yoga (Rs.300), Zumba (Rs.400), HIIT Workout (Rs.500)
  - Personal Training (Rs.600), CrossFit (Rs.350), Cardio (Rs.250)
- Balance validated automatically before confirming booking

### Membership Management
- Plans: Monthly (Rs.1,500) / Quarterly (Rs.4,000) / Semi-Annual (Rs.7,000) / Annual (Rs.12,000)
- Add-on services: Personal Trainer, Yoga, Zumba, Diet Plan, Locker, Steam & Sauna, Parking
- Health tracking: BMI auto-calc, medical conditions, fitness goals, experience level
- PIN change with database transaction (commit/rollback)

---

## Database Schema

**Database:** `gym_management_system`

| Table | Purpose |
|-------|---------|
| `login` | Member authentication (member_id, pin) |
| `member` | Personal & contact details |
| `member_health` | Health info, BMI, fitness goals |
| `membership` | Plan type, services, join/expiry dates |
| `payments` | Wallet transactions (Credit / Debit) |
| `bookings` | Class booking records |

---

## Installation & Setup

### 1. Start MySQL (XAMPP)
Open XAMPP Control Panel → Start **Apache** and **MySQL**.

### 2. Create the database
Go to `http://localhost/phpmyadmin` → **Import** tab → upload `database_schema.sql`.

### 3. Download required JARs
Place both in the `lib/` folder:
- **MySQL Connector/J** — https://dev.mysql.com/downloads/connector/j/ (Platform Independent ZIP)
- **JCalendar 1.4** — search Maven Central for `com.toedter:jcalendar:1.4`

### 4. Add JARs to IDE classpath

**IntelliJ IDEA:**
- File → Project Structure → Libraries → `+` → Java → select both JARs

**Eclipse:**
- Right-click project → Build Path → Add External JARs

**NetBeans:**
- Right-click project → Properties → Libraries → Add JAR/Folder

### 5. Mark source root & run
- Right-click `src/` → Mark Directory as → Sources Root
- Run `src/gym_system/main/Main.java`

---

## Usage

### New Member
1. Click **NEW MEMBER**
2. **Page 1** — Full name, phone (10-digit), DOB, gender, email, emergency contact, address
3. **Page 2** — Blood group, height, weight (BMI auto-calculated), medical conditions, fitness goal, experience level
4. **Page 3** — Choose membership plan, select services, check declaration
5. System shows your generated **Member ID** and **PIN** — save these
6. Add initial wallet funds to activate account

### Existing Member
1. Enter Member ID + PIN → click **LOGIN**
2. From Dashboard:
   - **Add Funds** — top up wallet
   - **Request Refund** — withdraw funds
   - **Quick Book** — book a class (balance checked automatically)
   - **Payment History** — view all transactions
   - **Wallet Balance** — check current balance
   - **Change Password** — update PIN securely

---

## Security

| Feature | Status |
|---------|--------|
| SQL injection prevention (PreparedStatement) | Done |
| Input validation (phone, email, amount, PIN format) | Done |
| Balance check before refunds and bookings | Done |
| PIN change with DB transaction (commit/rollback) | Done |
| 4-digit PIN enforcement | Done |

---

## Project Structure

```
FitZone/
│
├── src/
│   ├── gym_system/
│   │   ├── main/
│   │   │   └── Main.java
│   │   └── repository/
│   │       ├── Conn.java
│   │       ├── Login.java
│   │       ├── Dashboard.java
│   │       ├── SignUp.java
│   │       ├── SignUpTwo.java
│   │       ├── SignUpThree.java
│   │       ├── AddFunds.java
│   │       ├── RequestRefund.java
│   │       ├── QuickBook.java
│   │       ├── WalletBalance.java
│   │       ├── PaymentHistory.java
│   │       └── ChangePassword.java
│   └── icons/
│       ├── gym_logo.png
│       └── gym_background.jpg
│
├── lib/
│   ├── mysql-connector-j-9.3.0.jar
│   └── jcalendar-1.4.jar
│
├── docs/
├── database_schema.sql
├── Gym_System.iml
├── setup-automated.ps1
├── setup.bat
├── QUICKSTART.md
└── CHECKLIST.md
```

---

*Built with Java, Swing, and MySQL*
