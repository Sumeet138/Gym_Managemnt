# PRD: Gym & Fitness Center Management System

## 📋 Document Overview

| Attribute | Details |
|-----------|---------|
| **Project Name** | FitZone - Gym & Fitness Center Management |
| **Type** | Desktop Application (Java Swing + MySQL) |
| **Base Code** | Banking_System (refactored to Gym domain) |
| **Target Users** | Gym members, trainers, admin staff |
| **College Demo** | Fully functional with realistic gym workflows |

---

## 🎯 Problem Statement

Gym owners struggle to manage memberships, track payments, schedule classes, and maintain member health records manually. A unified system is needed to:
- Register members with health & fitness details
- Manage multiple membership plans (monthly, quarterly, annual)
- Track payments, dues, and wallet balances
- Schedule fitness classes (yoga, HIIT, Zumba, etc.)
- Assign personal trainers
- Monitor attendance & engagement
- Maintain health metrics (BMI, weight, goals)

---

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                     PRESENTATION LAYER                      │
│                    (Java Swing GUI)                         │
│                                                             │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │  Login   │  │  SignUp  │  │Dashboard │  │  Forms   │   │
│  │ Screen   │  │ (3-page) │  │ (Hub)    │  │ (Book,   │   │
│  │          │  │          │  │          │  │  Pay,    │   │
│  │          │  │          │  │          │  │  Track)  │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
└─────────────────────────────────────────────────────────────┘
                           ↕ JDBC
┌─────────────────────────────────────────────────────────────┐
│                      DATABASE LAYER                         │
│                    (MySQL 8.0+)                             │
│                                                             │
│  ┌────────┐ ┌─────────┐ ┌──────────┐ ┌──────────┐         │
│  │ login  │ │ member  │ │ payments │ │ bookings │         │
│  │ table  │ │ tables  │ │  table   │ │  table   │         │
│  └────────┘ └─────────┘ └──────────┘ └──────────┘         │
└─────────────────────────────────────────────────────────────┘
```

---

## 🖥️ FRONTEND SPECIFICATION

### Screen Inventory

| # | Screen Name | Based On | Purpose |
|---|------------|----------|---------|
| 1 | `Login.java` | `Login.java` | Member authentication with Member ID + PIN |
| 2 | `SignUp.java` | `SignUp.java` | Page 1: Personal & Contact Details |
| 3 | `SignUpTwo.java` | `SignUpTwo.java` | Page 2: Health & Medical Information |
| 4 | `SignUpThree.java` | `SignUpThree.java` | Page 3: Membership Plan & Services Selection |
| 5 | `Dashboard.java` | `Transaction.java` | Main member hub with all options |
| 6 | `AddFunds.java` | `Deposit.java` | Add money to gym wallet |
| 7 | `RequestRefund.java` | `Withdrawal.java` | Request wallet refund |
| 8 | `QuickBook.java` | `FastCash.java` | Quick book popular classes |
| 9 | `WalletBalance.java` | `BalanceEnquiry.java` | Check wallet balance |
| 10 | `PaymentHistory.java` | `MiniStatement.java` | View all transactions |
| 11 | `ChangePassword.java` | `PinChange.java` | Update account PIN |

---

### Screen 1: Login

**UI Elements:**
- Gym logo/banner: "FITZONE GYM & FITNESS"
- Member ID text field
- PIN password field
- Buttons: LOGIN, CLEAR, NEW MEMBER

**Validation:**
- Both fields required
- Query: `SELECT * FROM login WHERE member_id = ? AND pin = ?`
- On success → Dashboard
- On failure → "Invalid Member ID or PIN"

**Theme:**
- Background: Dark (#1a1a2e)
- Accent: Orange (#ff6b35) for buttons
- Text: White

---

### Screen 2: SignUp - Page 1 (Personal Details)

**Form No:** Auto-generated (random 4-digit)

**Fields:**

| Field | Type | Required | Validation |
|-------|------|----------|------------|
| Full Name | TextField | ✅ | Min 3 chars |
| Phone Number | TextField | ✅ | 10 digits |
| Date of Birth | JDateChooser | ✅ | Age ≥ 16 |
| Gender | Radio (Male/Female/Other) | ✅ | Single select |
| Email | TextField | ✅ | Format: *@*.* |
| Emergency Contact | TextField | ✅ | 10 digits |
| Address | TextField | ✅ | Min 10 chars |
| City | TextField | ✅ | - |
| State | TextField | ✅ | - |
| Pin Code | TextField | ✅ | 6 digits |

**Next Button:** Validates → Inserts into `member` table → Opens SignUpTwo

---

### Screen 3: SignUpTwo - Page 2 (Health Details)

**Fields:**

| Field | Type | Required | Options/Validation |
|-------|------|----------|-------------------|
| Blood Group | ComboBox | ✅ | A+, A-, B+, B-, AB+, AB-, O+, O- |
| Height (cm) | TextField | ✅ | Numeric, 100-250 |
| Weight (kg) | TextField | ✅ | Numeric, 20-300 |
| BMI | Auto-calculated | - | Display only |
| Any Medical Conditions | CheckBox[] | - | Diabetes, Asthma, Heart Issue, Joint Issue, None |
| Previous Injuries | TextField | - | Free text |
| Fitness Goal | Radio | ✅ | Weight Loss, Muscle Gain, Endurance, General Fitness |
| Experience Level | Radio | ✅ | Beginner, Intermediate, Advanced |
| Preferred Time | ComboBox | ✅ | Morning (6-10 AM), Afternoon (12-4 PM), Evening (5-9 PM) |
| Allergies | TextField | - | Free text |

**Auto-calculations:**
```java
BMI = weight(kg) / (height(m) * height(m))
```

**Next Button:** Validates → Inserts into `member_health` table → Opens SignUpThree

---

### Screen 4: SignUpThree - Page 3 (Membership Plan)

**Membership Plans (Radio - Single Select):**

| Plan | Duration | Base Price |
|------|----------|-----------|
| Monthly | 30 days | ₹1,500 |
| Quarterly | 90 days | ₹4,000 |
| Semi-Annual | 180 days | ₹7,000 |
| Annual | 365 days | ₹12,000 |

**Auto-Generated Credentials:**
- **Member ID:** 10-digit number (displayed after submission)
- **PIN:** 4-digit number (displayed after submission)

**Additional Services (CheckBox - Multi Select):**

| Service | Description | Extra Cost |
|---------|-------------|-----------|
| Personal Trainer | 1-on-1 sessions | ₹3,000/month |
| Yoga Classes | Group yoga sessions | ₹500/month |
| Zumba Classes | Dance fitness | ₹500/month |
| Diet Plan | Customized nutrition plan | ₹1,000/month |
| Locker Facility | Secure personal locker | ₹300/month |
| Steam & Sauna | Access to wellness area | ₹400/month |
| Parking | Reserved parking slot | ₹200/month |

**Declaration Checkbox:**
- "I declare that all information provided is accurate and I agree to the gym's terms & conditions."

**On Submit:**
1. Insert into `membership` table
2. Insert into `login` table
3. Show Member ID + PIN in dialog
4. Redirect to `AddFunds` for initial payment

---

### Screen 5: Dashboard (Main Hub)

**Layout:**
- Background: Gym-themed image
- Title: "Welcome to FitZone - Select Your Action"
- Member ID displayed at top

**Buttons:**

| Button | Opens | Icon Suggestion |
|--------|-------|----------------|
| 💰 Add Funds | `AddFunds.java` | Wallet/Plus |
| 💸 Request Refund | `RequestRefund.java` | Money out |
| ⚡ Quick Book Class | `QuickBook.java` | Lightning |
| 📋 Payment History | `PaymentHistory.java` | List |
| 🔑 Change Password | `ChangePassword.java` | Key |
| 👤 View Profile | `ViewProfile.java` | User |
| 🚪 Exit | System.exit | Power |

**Frame Size:** 780x737 (same as current Transaction.java)

---

### Screen 6: Add Funds (Wallet Top-Up)

**UI Elements:**
- Label: "Enter amount to add to your wallet:"
- Amount TextField
- Buttons: Add Funds, Back to Dashboard

**Validation:**
- Amount required, numeric, > 0, ≤ ₹50,000
- On success: Insert into `payments` table with type "Credit"
- Show success dialog with amount
- Return to Dashboard

---

### Screen 7: Request Refund

**UI Elements:**
- Label: "Enter refund amount:"
- Amount TextField
- Display: "Available Balance: Rs. X"
- Buttons: Request Refund, Back

**Validation:**
- Amount required, numeric, > 0
- Amount ≤ wallet balance
- On success: Insert into `payments` table with type "Debit"
- Show confirmation dialog
- Return to Dashboard

---

### Screen 8: Quick Book Class

**Purpose:** Quick registration for popular classes (replaces FastCash)

**Preset Buttons:**

| Button | Class | Price |
|--------|-------|-------|
| Rs. 300 | Yoga Session | ₹300 |
| Rs. 400 | Zumba Session | ₹400 |
| Rs. 500 | HIIT Workout | ₹500 |
| Rs. 600 | Personal Training | ₹600 |
| Rs. 350 | CrossFit Session | ₹350 |
| Rs. 250 | Cardio Session | ₹250 |

**Flow:**
1. User clicks a class button
2. System checks wallet balance
3. If sufficient balance → deduct amount, book class
4. If insufficient → "Insufficient Wallet Balance"
5. Insert into `bookings` table + `payments` table (Debit)
6. Show confirmation
7. Return to Dashboard

---

### Screen 9: Wallet Balance

**UI Elements:**
- Label: "Your Wallet Balance: Rs. X"
- Breakdown (optional):
  - Total Credits: Rs. X
  - Total Debits: Rs. X
- Button: Back to Dashboard

**Calculation:**
```java
balance = sum(Credits) - sum(Debits)
```

---

### Screen 10: Payment History

**UI Elements:**
- Header: "FITZONE GYM & FITNESS"
- Member ID (masked): "MEMBER ID: XXXX••••1234"
- Transaction list: Date | Description | Amount | Type
- Footer: "Current Balance: Rs. X"
- Scrollable for long histories

**Data Sources:**
- Query `payments` table ordered by date DESC
- Query `bookings` table for class bookings

---

### Screen 11: Change Password

**UI Elements:**
- Label: "Enter New PIN:"
- JPasswordField: newPin
- Label: "Re-Enter PIN:"
- JPasswordField: confirmPin
- Buttons: Change, Back

**Validation:**
- Both fields required
- PINs must match
- PIN must be 4 digits
- Update PIN in: `login`, `payments` (if stored), `member` tables
- Show success dialog
- Return to Dashboard with new PIN

---

## 🗄️ DATABASE SCHEMA

### Database: `gym_management_system`

### Table 1: `login`
| Column | Type | Constraint | Description |
|--------|------|------------|-------------|
| form_no | VARCHAR(10) | PRIMARY KEY | Registration form number |
| member_id | VARCHAR(15) | UNIQUE, NOT NULL | Auto-generated 10-digit ID |
| pin | VARCHAR(10) | NOT NULL | 4-digit PIN |

### Table 2: `member`
| Column | Type | Constraint | Description |
|--------|------|------------|-------------|
| form_no | VARCHAR(10) | PRIMARY KEY | Links to other tables |
| full_name | VARCHAR(100) | NOT NULL | Member's full name |
| phone | VARCHAR(15) | NOT NULL | 10-digit phone |
| dob | VARCHAR(20) | NOT NULL | Date of birth |
| gender | VARCHAR(20) | NOT NULL | Male/Female/Other |
| email | VARCHAR(100) | NOT NULL | Email address |
| emergency_contact | VARCHAR(15) | NOT NULL | Emergency contact |
| address | VARCHAR(200) | NOT NULL | Full address |
| city | VARCHAR(50) | NOT NULL | City |
| state | VARCHAR(50) | NOT NULL | State |
| pincode | VARCHAR(10) | NOT NULL | 6-digit pincode |

### Table 3: `member_health`
| Column | Type | Constraint | Description |
|--------|------|------------|-------------|
| form_no | VARCHAR(10) | PRIMARY KEY | Links to member |
| blood_group | VARCHAR(5) | NOT NULL | A+, B+, etc. |
| height_cm | VARCHAR(10) | NOT NULL | Height in cm |
| weight_kg | VARCHAR(10) | NOT NULL | Weight in kg |
| bmi | VARCHAR(10) | - | Auto-calculated |
| medical_conditions | VARCHAR(200) | - | Comma-separated conditions |
| previous_injuries | VARCHAR(200) | - | Free text |
| fitness_goal | VARCHAR(50) | NOT NULL | Weight Loss, etc. |
| experience_level | VARCHAR(30) | NOT NULL | Beginner/Intermediate/Advanced |
| preferred_time | VARCHAR(50) | NOT NULL | Morning/Afternoon/Evening |
| allergies | VARCHAR(200) | - | Allergy information |

### Table 4: `membership`
| Column | Type | Constraint | Description |
|--------|------|------------|-------------|
| form_no | VARCHAR(10) | PRIMARY KEY | Links to member |
| plan_type | VARCHAR(30) | NOT NULL | Monthly/Quarterly/Semi-Annual/Annual |
| member_id | VARCHAR(15) | UNIQUE, NOT NULL | Auto-generated |
| pin | VARCHAR(10) | NOT NULL | 4-digit PIN |
| services | VARCHAR(300) | - | Selected services (comma-separated) |
| join_date | DATE | - | Auto-set on registration |
| expiry_date | DATE | - | Calculated from plan |
| declaration | VARCHAR(20) | - | "Agreed" |

### Table 5: `payments`
| Column | Type | Constraint | Description |
|--------|------|------------|-------------|
| member_id | VARCHAR(15) | NOT NULL | FK to membership |
| transaction_date | DATETIME | NOT NULL | Timestamp of transaction |
| transaction_type | VARCHAR(20) | NOT NULL | Credit / Debit |
| description | VARCHAR(100) | NOT NULL | Purpose of transaction |
| amount | INT | NOT NULL | Amount in Rupees |

### Table 6: `bookings`
| Column | Type | Constraint | Description |
|--------|------|------------|-------------|
| booking_id | INT | AUTO_INCREMENT, PK | Unique booking ID |
| member_id | VARCHAR(15) | NOT NULL | FK to membership |
| booking_date | DATETIME | NOT NULL | When booking was made |
| class_type | VARCHAR(50) | NOT NULL | Yoga, Zumba, HIIT, etc. |
| amount | INT | NOT NULL | Class fee |
| status | VARCHAR(20) | DEFAULT 'Confirmed' | Confirmed / Cancelled |

---

## 🔄 USER FLOWS

### Flow 1: New Member Registration

```
┌──────────────────────────────────────────────────────────────┐
│                    NEW MEMBER JOURNEY                        │
│                                                              │
│  ┌─────────┐     ┌──────────┐     ┌───────────┐             │
│  │  Login  │────▶│  SignUp  │────▶│ SignUpTwo │             │
│  │ Screen  │     │ (Page 1) │     │ (Page 2)  │             │
│  │         │     │ Personal │     │  Health   │             │
│  └─────────┘     └──────────┘     └───────────┘             │
│      ▲                                    │                 │
│      │              ┌───────────┐         │                 │
│      │              │  SignUp   │         ▼                 │
│      │              │  Cancel   │    ┌───────────┐          │
│      │              └───────────┘    │SignUpThree│          │
│      │                              │ (Page 3)  │          │
│      │                              │ Membership│          │
│      │                              └───────────┘          │
│      │                                    │                │
│      │              ┌──────────┐          ▼                │
│      │◀─────────────│ Dashboard│◀──── ┌──────────┐        │
│      │              │          │      │ AddFunds │        │
│      └──────────────└──────────┘      │(Initial  │        │
│                                       │ Payment) │        │
│                                       └──────────┘        │
└──────────────────────────────────────────────────────────────┘
```

**Steps:**
1. User clicks "NEW MEMBER" on Login
2. Fills personal details → gets Form No (auto-generated)
3. Fills health details → BMI auto-calculated
4. Selects membership plan + services → gets Member ID + PIN
5. Makes initial payment (mandatory)
6. Reaches Dashboard → can now use all features

### Flow 2: Existing Member Login & Activities

```
┌──────────────────────────────────────────────────────────────┐
│                   EXISTING MEMBER JOURNEY                    │
│                                                              │
│  ┌─────────┐     ┌───────────┐     ┌──────────────┐        │
│  │  Login  │────▶│ Dashboard │────▶│   Activity   │        │
│  │(Member  │     │   (Hub)   │     │   Selection  │        │
│  │ ID+PIN) │     │           │     │              │        │
│  └─────────┘     └───────────┘     └──────┬───────┘        │
│                                           │                │
│                    ┌──────────┬───────────┼───────────┐    │
│                    ▼          ▼           ▼           ▼    │
│               ┌────────┐ ┌────────┐ ┌────────┐ ┌────────┐ │
│               │ Add    │ │ Quick  │ │Payment │ │ Wallet │ │
│               │ Funds  │ │ Book   │ │History │ │Balance │ │
│               └────────┘ └────────┘ └────────┘ └────────┘ │
└──────────────────────────────────────────────────────────────┘
```

### Flow 3: Quick Booking a Class

```
┌──────────────────────────────────────────────────────────────┐
│                    QUICK BOOKING FLOW                        │
│                                                              │
│  User clicks "Quick Book Class"                              │
│       │                                                      │
│       ▼                                                      │
│  Select class (Yoga ₹300, Zumba ₹400, etc.)                 │
│       │                                                      │
│       ▼                                                      │
│  System checks wallet balance                                │
│       │                                                      │
│    ┌─┴─┐                                                     │
│    │   │                                                      │
│  Yes  No                                                     │
│    │   │                                                      │
│    ▼   ▼                                                     │
│ Deduct  Show "Insufficient Balance"                          │
│ amount  Return to Dashboard                                  │
│    │                                                         │
│    ▼                                                         │
│ Insert into bookings table                                   │
│ Insert into payments table (Debit)                           │
│    │                                                         │
│    ▼                                                         │
│ Show "Class Booked Successfully"                             │
│ Return to Dashboard                                          │
└──────────────────────────────────────────────────────────────┘
```

### Flow 4: Wallet Top-Up

```
┌──────────────────────────────────────────────────────────────┐
│                    ADD FUNDS FLOW                            │
│                                                              │
│  User clicks "Add Funds"                                     │
│       │                                                      │
│       ▼                                                      │
│  Enter amount (₹100 - ₹50,000)                              │
│       │                                                      │
│       ▼                                                      │
│  Validate: numeric, positive, within limit                  │
│       │                                                      │
│       ▼                                                      │
│  Insert into payments table (Credit)                        │
│       │                                                      │
│       ▼                                                      │
│  Show "₹X Added Successfully"                               │
│       │                                                      │
│       ▼                                                      │
│  Return to Dashboard                                         │
└──────────────────────────────────────────────────────────────┘
```

### Flow 5: Change Password

```
┌──────────────────────────────────────────────────────────────┐
│                   CHANGE PASSWORD FLOW                       │
│                                                              │
│  User clicks "Change Password"                               │
│       │                                                      │
│       ▼                                                      │
│  Enter new PIN (4 digits)                                    │
│       │                                                      │
│       ▼                                                      │
│  Re-enter PIN                                                │
│       │                                                      │
│       ▼                                                      │
│  Validate: match, not empty, 4 digits                       │
│       │                                                      │
│       ▼                                                      │
│  BEGIN TRANSACTION                                           │
│    UPDATE login SET pin = newPin WHERE member_id = current  │
│    UPDATE membership SET pin = newPin WHERE member_id = ... │
│  COMMIT TRANSACTION                                          │
│       │                                                      │
│       ▼                                                      │
│  Show "Password Changed Successfully"                        │
│       │                                                      │
│       ▼                                                      │
│  Return to Dashboard with new PIN                            │
└──────────────────────────────────────────────────────────────┘
```

---

## 🔌 INTEGRATIONS & APIs

### 1. JDBC (MySQL Connector/J)
- **Purpose:** Database connectivity
- **Version:** 9.3.0
- **Driver:** `com.mysql.cj.jdbc.Driver`
- **Connection:** via environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`)

### 2. JDateChooser (JCalendar 1.4)
- **Purpose:** Date of birth selection in SignUp Page 1
- **Note:** Will be used as-is (future upgrade recommended)

### 3. No External APIs
This is a **standalone desktop application** with no REST APIs or third-party integrations. All logic is local.

### Future API Integrations (Stretch Goals)
| API | Purpose | Priority |
|-----|---------|----------|
| SMS Gateway | Send booking confirmations | Low |
| Email Service | Send payment receipts | Low |
| Payment Gateway (Razorpay) | Online payments | Medium |
| WhatsApp API | Booking reminders | Low |

---

## 🎨 UI/UX SPECIFICATIONS

### Design System

| Element | Value |
|---------|-------|
| **Primary Color** | `#ff6b35` (Orange) |
| **Secondary Color** | `#1a1a2e` (Dark Navy) |
| **Background (Forms)** | `#ffffff` (White) |
| **Background (Dashboards)** | Gym image overlay |
| **Text Color (Dark BG)** | `#ffffff` (White) |
| **Text Color (Light BG)** | `#000000` (Black) |
| **Success** | `#28a745` (Green) |
| **Error** | `#dc3545` (Red) |
| **Warning** | `#ffc107` (Yellow) |

### Fonts
| Usage | Font | Size | Weight |
|-------|------|------|--------|
| Headings | "Segoe UI" | 28px | Bold |
| Sub-headings | "Segoe UI" | 20px | Bold |
| Labels | "Segoe UI" | 15px | Bold |
| Body Text | "Segoe UI" | 15px | Regular |
| Buttons | "Segoe UI" | 15px | Bold |

### Frame Sizes
| Screen | Width | Height |
|--------|-------|--------|
| Login | 700 | 400 |
| SignUp (3 pages) | 850 | 750 |
| Dashboard & Activities | 780 | 737 |
| Payment History | 400 | 600 |

### Icon Resources
| File | Usage |
|------|-------|
| `icons/gym_logo.png` | Login screen header |
| `icons/gym_background.jpg` | Dashboard & activity screens background |

---

## 📊 BUSINESS LOGIC RULES

### Membership Plans
| Plan | Duration | Price | Auto Debit |
|------|----------|-------|------------|
| Monthly | 30 days | ₹1,500 | Yes (on registration) |
| Quarterly | 90 days | ₹4,000 | Yes |
| Semi-Annual | 180 days | ₹7,000 | Yes |
| Annual | 365 days | ₹12,000 | Yes |

### Wallet Rules
- Minimum top-up: ₹100
- Maximum top-up: ₹50,000 per transaction
- No negative balance allowed
- Refund requests processed same day

### Booking Rules
- Classes booked via Quick Book are **confirmed instantly**
- No cancellation/refund for booked classes (simplified)
- Maximum 5 bookings per day (configurable)

### BMI Categories
| BMI Range | Category |
|-----------|----------|
| < 18.5 | Underweight |
| 18.5 - 24.9 | Normal |
| 25.0 - 29.9 | Overweight |
| ≥ 30.0 | Obese |

---

## 🔐 SECURITY SPECIFICATIONS

### Current (College Project Level)
- PIN stored as plain text (acceptable for demo)
- No brute force protection (acceptable for demo)
- Environment variables for DB credentials

### Recommended Improvements (If Time Permits)
1. Use `PreparedStatement` instead of `Statement`
2. Hash PIN using SHA-256
3. Add input validation on all fields
4. Add transaction management for PIN updates

---

## 📁 PROJECT STRUCTURE (Post-Migration)

```
Gym_Management_System/
├── docs/
│   ├── 01-prd.md                    # This file
│   ├── 02-tech-stack-mapping.md     # Tech stack changes
│   ├── 03-test-cases.md             # TDD test cases
│   └── 04-e2e-test-report.md        # E2E test results
├── src/
│   ├── gym_system/
│   │   ├── main/
│   │   │   └── Main.java            # Entry point
│   │   └── repository/
│   │       ├── Conn.java            # DB connection
│   │       ├── Login.java           # Auth screen
│   │       ├── Dashboard.java       # Main hub
│   │       ├── SignUp.java          # Page 1: Personal
│   │       ├── SignUpTwo.java       # Page 2: Health
│   │       ├── SignUpThree.java     # Page 3: Membership
│   │       ├── AddFunds.java        # Wallet top-up
│   │       ├── RequestRefund.java   # Refund request
│   │       ├── QuickBook.java       # Quick class booking
│   │       ├── WalletBalance.java   # Balance check
│   │       ├── PaymentHistory.java  # Transaction log
│   │       └── ChangePassword.java  # PIN update
├── icons/
│   ├── gym_logo.png
│   └── gym_background.jpg
├── lib/
│   ├── mysql-connector-j-9.3.0.jar
│   └── jcalendar-1.4.jar
├── screenshot/                      # UI screenshots
├── Banking_System.iml               # (Rename to Gym_System.iml)
└── README.md                        # Updated documentation
```

---

## 📋 ACCEPTANCE CRITERIA

### Must Have (MVP)
- [x] 3-page member registration with validation
- [x] Login with Member ID + PIN
- [x] Dashboard with 7 action buttons
- [x] Wallet add funds (with validation)
- [x] Quick book classes (with balance check)
- [x] Payment history view
- [x] Wallet balance display
- [x] Password change (with confirmation)
- [x] All data stored in MySQL

### Should Have
- [x] BMI auto-calculation in SignUpTwo
- [x] Membership expiry date calculation
- [x] Masked Member ID in payment history
- [x] Input validation on all forms

### Nice to Have (If Time)
- [ ] PreparedStatement usage (SQL injection fix)
- [ ] PIN hashing
- [ ] Profile view screen
- [ ] Membership status display on dashboard

---

## 🚀 DEPLOYMENT CHECKLIST

### Pre-Deployment
- [ ] MySQL server running
- [ ] Database `gym_management_system` created
- [ ] All 6 tables created with correct schema
- [ ] Environment variables set (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`)
- [ ] JAR files in `lib/` folder
- [ ] Icon files in `icons/` folder

### Deployment Steps
1. Clone repository
2. Create MySQL database and tables
3. Set environment variables
4. Open in IntelliJ IDEA
5. Mark `src` as Sources Root
6. Add library dependencies
7. Run `Main.java`

### Demo Script (For College Presentation)
1. **Show Login screen** → Explain UI
2. **Register new member** → Complete 3-page signup
3. **Show generated credentials** → Member ID + PIN
4. **Make initial payment** → Add funds
5. **Login with new member** → Show dashboard
6. **Quick book a class** → Show balance deduction
7. **View payment history** → Show transaction log
8. **Check wallet balance** → Show calculation
9. **Change password** → Show PIN update flow

---

## 📞 CONTACT & SUPPORT

- **Project Type:** College Academic Project
- **Framework:** Java Swing + MySQL
- **Estimated Complexity:** Medium (11 screens, 6 tables, CRUD operations)
- **Demo Duration:** 10-15 minutes
