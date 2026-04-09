# TDD Test Cases - Gym Management System

## Testing Strategy

Since this is a **Java Swing desktop application** with MySQL backend, our testing approach covers:

| Test Type | Method | Tools |
|-----------|--------|-------|
| **Unit Tests** | Manual validation functions | JUnit 5 (optional) |
| **Integration Tests** | Database query validation | MySQL queries |
| **UI Validation Tests** | Input field checks | Manual test scripts |
| **E2E Tests** | Complete user flows | Test runner script |

This document defines **all test cases** that must pass before the project is considered complete.

---

## 🧪 TEST CASE INVENTORY

| ID | Category | Test Case | Priority | Status |
|----|----------|-----------|----------|--------|
| TC-01 | Auth | Login with valid credentials | 🔴 Critical | ⬜ |
| TC-02 | Auth | Login with invalid credentials | 🔴 Critical | ⬜ |
| TC-03 | Auth | Login with empty fields | 🔴 Critical | ⬜ |
| TC-04 | Auth | Clear button resets fields | 🟡 High | ⬜ |
| TC-05 | Reg-P1 | SignUp Page 1 - all valid fields | 🔴 Critical | ⬜ |
| TC-06 | Reg-P1 | SignUp Page 1 - missing required fields | 🔴 Critical | ⬜ |
| TC-07 | Reg-P1 | SignUp Page 1 - invalid email format | 🟡 High | ⬜ |
| TC-08 | Reg-P1 | SignUp Page 1 - invalid phone number | 🟡 High | ⬜ |
| TC-09 | Reg-P1 | SignUp Page 1 - invalid pincode | 🟡 High | ⬜ |
| TC-10 | Reg-P1 | Form number auto-generated | 🟡 High | ⬜ |
| TC-11 | Reg-P2 | SignUp Page 2 - all valid fields | 🔴 Critical | ⬜ |
| TC-12 | Reg-P2 | SignUp Page 2 - BMI auto-calculated | 🔴 Critical | ⬜ |
| TC-13 | Reg-P2 | SignUp Page 2 - missing required fields | 🔴 Critical | ⬜ |
| TC-14 | Reg-P2 | SignUp Page 2 - invalid height/weight | 🟡 High | ⬜ |
| TC-15 | Reg-P3 | SignUp Page 3 - select plan + services | 🔴 Critical | ⬜ |
| TC-16 | Reg-P3 | SignUp Page 3 - Member ID generated (10-digit) | 🔴 Critical | ⬜ |
| TC-17 | Reg-P3 | SignUp Page 3 - PIN generated (4-digit) | 🔴 Critical | ⬜ |
| TC-18 | Reg-P3 | SignUp Page 3 - data in all 3 tables | 🔴 Critical | ⬜ |
| TC-19 | Reg-P3 | SignUp Page 3 - no plan selected | 🟡 High | ⬜ |
| TC-20 | Reg-P3 | SignUp Page 3 - no services selected | 🟡 High | ⬜ |
| TC-21 | Wallet | Add funds - valid amount | 🔴 Critical | ⬜ |
| TC-22 | Wallet | Add funds - empty amount | 🔴 Critical | ⬜ |
| TC-23 | Wallet | Add funds - negative amount | 🟡 High | ⬜ |
| TC-24 | Wallet | Add funds - non-numeric input | 🟡 High | ⬜ |
| TC-25 | Wallet | Add funds - exceeds limit (₹50,001) | 🟡 High | ⬜ |
| TC-26 | Wallet | Add funds - record in payments table | 🔴 Critical | ⬜ |
| TC-27 | Refund | Request refund - valid amount | 🔴 Critical | ⬜ |
| TC-28 | Refund | Request refund - exceeds balance | 🔴 Critical | ⬜ |
| TC-29 | Refund | Request refund - empty amount | 🟡 High | ⬜ |
| TC-30 | Refund | Request refund - shows current balance | 🟡 High | ⬜ |
| TC-31 | Booking | Quick book - valid class selection | 🔴 Critical | ⬜ |
| TC-32 | Booking | Quick book - insufficient balance | 🔴 Critical | ⬜ |
| TC-33 | Booking | Quick book - record in bookings table | 🔴 Critical | ⬜ |
| TC-34 | Booking | Quick book - record in payments table | 🔴 Critical | ⬜ |
| TC-35 | Balance | Wallet balance - correct calculation | 🔴 Critical | ⬜ |
| TC-36 | Balance | Wallet balance - zero balance | 🟡 High | ⬜ |
| TC-37 | History | Payment history - shows all transactions | 🔴 Critical | ⬜ |
| TC-38 | History | Payment history - masked Member ID | 🟡 High | ⬜ |
| TC-39 | History | Payment history - shows balance footer | 🟡 High | ⬜ |
| TC-40 | Security | Change password - matching PINs | 🔴 Critical | ⬜ |
| TC-41 | Security | Change password - non-matching PINs | 🔴 Critical | ⬜ |
| TC-42 | Security | Change password - empty fields | 🟡 High | ⬜ |
| TC-43 | Security | Change password - updated in all tables | 🔴 Critical | ⬜ |
| TC-44 | DB | All 6 tables exist | 🔴 Critical | ⬜ |
| TC-45 | DB | Foreign key relationships valid | 🟡 High | ⬜ |
| TC-46 | E2E | Complete new member registration flow | 🔴 Critical | ⬜ |
| TC-47 | E2E | Existing member login + add funds | 🔴 Critical | ⬜ |
| TC-48 | E2E | Quick book class + balance deduction | 🔴 Critical | ⬜ |
| TC-49 | E2E | Payment history reflects all actions | 🔴 Critical | ⬜ |
| TC-50 | E2E | Change password + login with new PIN | 🔴 Critical | ⬜ |

---

## 📋 DETAILED TEST CASES

### TC-01: Login with Valid Credentials

| Attribute | Value |
|-----------|-------|
| **Category** | Authentication |
| **Priority** | 🔴 Critical |
| **Precondition** | Member exists in `login` table |
| **Steps** | 1. Open Login screen<br>2. Enter valid Member ID<br>3. Enter correct PIN<br>4. Click LOGIN |
| **Expected** | Dashboard opens with member's ID |
| **DB Check** | None (read-only) |

```sql
-- Precondition: Verify member exists
SELECT * FROM login WHERE member_id = '1234567890' AND pin = '1234';
```

---

### TC-02: Login with Invalid Credentials

| Attribute | Value |
|-----------|-------|
| **Category** | Authentication |
| **Priority** | 🔴 Critical |
| **Precondition** | Login screen open |
| **Steps** | 1. Enter wrong Member ID<br>2. Enter any PIN<br>3. Click LOGIN |
| **Expected** | Error dialog: "Invalid Member ID or PIN" |
| **DB Check** | None |

---

### TC-03: Login with Empty Fields

| Attribute | Value |
|-----------|-------|
| **Category** | Authentication |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Leave both fields empty<br>2. Click LOGIN |
| **Expected** | Error dialog: "Please enter Member ID and PIN" |
| **DB Check** | No query executed |

---

### TC-04: Clear Button Resets Fields

| Attribute | Value |
|-----------|-------|
| **Category** | Authentication |
| **Priority** | 🟡 High |
| **Steps** | 1. Enter some text in both fields<br>2. Click CLEAR |
| **Expected** | Both fields are empty |

---

### TC-05: SignUp Page 1 - All Valid Fields

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 1 |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Open SignUp<br>2. Fill all required fields with valid data<br>3. Click NEXT |
| **Expected** | SignUpTwo opens with same form_no |
| **DB Check** | Record inserted into `member` table |

```sql
-- Verify data
SELECT * FROM member WHERE form_no = '<auto_generated_form_no>';
-- Verify all columns match input
```

**Test Data:**
```
Full Name: John Doe
Phone: 9876543210
DOB: 1995-06-15
Gender: Male
Email: john@example.com
Emergency Contact: 9876543211
Address: 123 Main Street
City: Mumbai
State: Maharashtra
Pincode: 400001
```

---

### TC-06: SignUp Page 1 - Missing Required Fields

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 1 |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Leave Name empty<br>2. Fill other fields<br>3. Click NEXT |
| **Expected** | Error dialog: "Name and Address are required" |
| **DB Check** | No record inserted |

---

### TC-07: SignUp Page 1 - Invalid Email Format

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 1 |
| **Priority** | 🟡 High |
| **Steps** | 1. Enter email: "invalid-email"<br>2. Click NEXT |
| **Expected** | Error dialog: "Please enter a valid email address" |
| **DB Check** | No record inserted |

---

### TC-08: SignUp Page 1 - Invalid Phone Number

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 1 |
| **Priority** | 🟡 High |
| **Steps** | 1. Enter phone: "12345"<br>2. Click NEXT |
| **Expected** | Error dialog: "Please enter a valid 10-digit phone number" |
| **DB Check** | No record inserted |

---

### TC-09: SignUp Page 1 - Invalid Pincode

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 1 |
| **Priority** | 🟡 High |
| **Steps** | 1. Enter pincode: "123"<br>2. Click NEXT |
| **Expected** | Error dialog: "Please enter a valid 6-digit pincode" |
| **DB Check** | No record inserted |

---

### TC-10: Form Number Auto-Generated

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 1 |
| **Priority** | 🟡 High |
| **Steps** | 1. Open SignUp |
| **Expected** | Form number displayed (4-digit random number) |
| **Validation** | Number is between 1000-9999 |

---

### TC-11: SignUp Page 2 - All Valid Fields

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 2 |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Fill all health fields with valid data<br>2. Click NEXT |
| **Expected** | SignUpThree opens with same form_no |
| **DB Check** | Record inserted into `member_health` table |

**Test Data:**
```
Blood Group: O+
Height: 175 cm
Weight: 70 kg
Medical Conditions: None (unchecked all)
Previous Injuries: None
Fitness Goal: Weight Loss
Experience Level: Beginner
Preferred Time: Morning (6-10 AM)
Allergies: None
```

---

### TC-12: SignUp Page 2 - BMI Auto-Calculated

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 2 |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Enter Height: 175 cm<br>2. Enter Weight: 70 kg<br>3. Observe BMI field |
| **Expected** | BMI displays: 22.86 (70 / 1.75²) |
| **Validation** | BMI = weight / (height_in_meters)² |

```java
// Expected calculation
double heightM = 175 / 100.0;  // 1.75
double bmi = 70 / (heightM * heightM);  // 22.86
```

---

### TC-13: SignUp Page 2 - Missing Required Fields

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 2 |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Leave Blood Group empty (impossible with dropdown)<br>2. Leave Height empty<br>3. Click NEXT |
| **Expected** | Error dialog: "Height and Weight are required" |
| **DB Check** | No record inserted |

---

### TC-14: SignUp Page 2 - Invalid Height/Weight

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 2 |
| **Priority** | 🟡 High |
| **Steps** | 1. Enter Height: "abc"<br>2. Click NEXT |
| **Expected** | Error dialog: "Please enter valid height (100-250 cm)" |
| **DB Check** | No record inserted |

---

### TC-15: SignUp Page 3 - Select Plan + Services

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 3 |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Select plan: Quarterly (₹4,000)<br>2. Select services: Personal Trainer, Yoga<br>3. Check declaration<br>4. Click SUBMIT |
| **Expected** | 1. Dialog shows Member ID + PIN<br>2. AddFunds screen opens |
| **DB Check** | Records in `membership` AND `login` tables |

```sql
-- Verify membership
SELECT * FROM membership WHERE form_no = '<form_no>';
-- Verify login credentials
SELECT * FROM login WHERE form_no = '<form_no>';
```

---

### TC-16: SignUp Page 3 - Member ID Generated (10-digit)

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 3 |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Complete SignUp Page 3<br>2. Observe generated Member ID |
| **Expected** | Member ID is exactly 10 digits |
| **Validation** | `memberId.matches("\\d{10}")` |

---

### TC-17: SignUp Page 3 - PIN Generated (4-digit)

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 3 |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Complete SignUp Page 3<br>2. Observe generated PIN |
| **Expected** | PIN is exactly 4 digits |
| **Validation** | `pin.matches("\\d{4}")` |

---

### TC-18: SignUp Page 3 - Data in All 3 Tables

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 3 |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Complete registration<br>2. Query all 3 tables |
| **Expected** | Data present in `member`, `member_health`, `membership`, `login` |

```sql
-- Check all tables for same form_no
SELECT * FROM member WHERE form_no = '<form_no>';
SELECT * FROM member_health WHERE form_no = '<form_no>';
SELECT * FROM membership WHERE form_no = '<form_no>';
SELECT * FROM login WHERE form_no = '<form_no>';
```

---

### TC-19: SignUp Page 3 - No Plan Selected

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 3 |
| **Priority** | 🟡 High |
| **Steps** | 1. Don't select any plan<br>2. Click SUBMIT |
| **Expected** | Error dialog: "Please select a membership plan" |
| **DB Check** | No records inserted |

---

### TC-20: SignUp Page 3 - No Services Selected

| Attribute | Value |
|-----------|-------|
| **Category** | Registration - Page 3 |
| **Priority** | 🟡 High |
| **Steps** | 1. Select plan but no services<br>2. Check declaration<br>3. Click SUBMIT |
| **Expected** | Either error OR accept (decide policy) |
| **DB Check** | `services` column = "" or NULL |

---

### TC-21: Add Funds - Valid Amount

| Attribute | Value |
|-----------|-------|
| **Category** | Wallet |
| **Priority** | 🔴 Critical |
| **Precondition** | Logged in member |
| **Steps** | 1. Click "Add Funds"<br>2. Enter: 5000<br>3. Click "Add Funds" |
| **Expected** | 1. Success: "₹5000 Added Successfully"<br>2. Return to Dashboard |
| **DB Check** | Record in `payments` table |

```sql
SELECT * FROM payments WHERE member_id = '<member_id>' ORDER BY transaction_date DESC LIMIT 1;
-- Expected: type='Credit', amount=5000, description='Wallet Top-Up'
```

---

### TC-22: Add Funds - Empty Amount

| Attribute | Value |
|-----------|-------|
| **Category** | Wallet |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Leave amount empty<br>2. Click "Add Funds" |
| **Expected** | Error: "Please enter an amount" |
| **DB Check** | No record inserted |

---

### TC-23: Add Funds - Negative Amount

| Attribute | Value |
|-----------|-------|
| **Category** | Wallet |
| **Priority** | 🟡 High |
| **Steps** | 1. Enter: -500<br>2. Click "Add Funds" |
| **Expected** | Error: "Please enter a valid positive amount" |
| **DB Check** | No record inserted |

---

### TC-24: Add Funds - Non-Numeric Input

| Attribute | Value |
|-----------|-------|
| **Category** | Wallet |
| **Priority** | 🟡 High |
| **Steps** | 1. Enter: "abc"<br>2. Click "Add Funds" |
| **Expected** | Error: "Please enter a valid number" |
| **DB Check** | No record inserted |

---

### TC-25: Add Funds - Exceeds Limit

| Attribute | Value |
|-----------|-------|
| **Category** | Wallet |
| **Priority** | 🟡 High |
| **Steps** | 1. Enter: 50001<br>2. Click "Add Funds" |
| **Expected** | Error: "Maximum ₹50,000 per transaction" |
| **DB Check** | No record inserted |

---

### TC-26: Add Funds - Record in Payments Table

| Attribute | Value |
|-----------|-------|
| **Category** | Wallet |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Add ₹5000<br>2. Query database |
| **Expected** | All fields populated correctly |

```sql
SELECT member_id, transaction_type, amount, description 
FROM payments 
WHERE member_id = '<member_id>' 
AND transaction_type = 'Credit' 
AND amount = 5000;
```

---

### TC-27: Request Refund - Valid Amount

| Attribute | Value |
|-----------|-------|
| **Category** | Refund |
| **Priority** | 🔴 Critical |
| **Precondition** | Wallet balance ≥ ₹5000 |
| **Steps** | 1. Click "Request Refund"<br>2. Enter: 2000<br>3. Click "Request Refund" |
| **Expected** | 1. Success: "₹2000 Refund Requested"<br>2. Return to Dashboard |
| **DB Check** | Debit record in `payments` |

```sql
SELECT * FROM payments WHERE member_id = '<member_id>' 
AND transaction_type = 'Debit' AND amount = 2000;
```

---

### TC-28: Request Refund - Exceeds Balance

| Attribute | Value |
|-----------|-------|
| **Category** | Refund |
| **Priority** | 🔴 Critical |
| **Precondition** | Wallet balance = ₹3000 |
| **Steps** | 1. Click "Request Refund"<br>2. Enter: 5000<br>3. Click "Request Refund" |
| **Expected** | Error: "Insufficient Wallet Balance" |
| **DB Check** | No record inserted |

---

### TC-29: Request Refund - Empty Amount

| Attribute | Value |
|-----------|-------|
| **Category** | Refund |
| **Priority** | 🟡 High |
| **Steps** | 1. Leave amount empty<br>2. Click "Request Refund" |
| **Expected** | Error: "Please enter an amount" |

---

### TC-30: Request Refund - Shows Current Balance

| Attribute | Value |
|-----------|-------|
| **Category** | Refund |
| **Priority** | 🟡 High |
| **Steps** | 1. Open Request Refund screen |
| **Expected** | Label displays: "Available Balance: Rs. X" (correct value) |
| **DB Check** | Balance matches calculated value from payments table |

```sql
SELECT 
  SUM(CASE WHEN transaction_type = 'Credit' THEN amount ELSE -amount END) as balance
FROM payments WHERE member_id = '<member_id>';
```

---

### TC-31: Quick Book - Valid Class Selection

| Attribute | Value |
|-----------|-------|
| **Category** | Booking |
| **Priority** | 🔴 Critical |
| **Precondition** | Wallet balance ≥ ₹500 |
| **Steps** | 1. Click "Quick Book Class"<br>2. Click "HIIT Workout - ₹500"<br>3. Confirm |
| **Expected** | 1. Success: "Class Booked!"<br>2. Return to Dashboard |
| **DB Check** | Record in `bookings` AND `payments` |

```sql
-- Check bookings
SELECT * FROM bookings WHERE member_id = '<member_id>' AND class_type = 'HIIT Workout';
-- Check payments
SELECT * FROM payments WHERE member_id = '<member_id>' AND transaction_type = 'Debit' AND amount = 500;
```

---

### TC-32: Quick Book - Insufficient Balance

| Attribute | Value |
|-----------|-------|
| **Category** | Booking |
| **Priority** | 🔴 Critical |
| **Precondition** | Wallet balance = ₹200 |
| **Steps** | 1. Click "Quick Book Class"<br>2. Click "HIIT Workout - ₹500" |
| **Expected** | Error: "Insufficient Wallet Balance" |
| **DB Check** | No records inserted |

---

### TC-33: Quick Book - Record in Bookings Table

| Attribute | Value |
|-----------|-------|
| **Category** | Booking |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Book a Yoga class<br>2. Query bookings table |
| **Expected** | Correct class_type, amount, status='Confirmed' |

```sql
SELECT * FROM bookings WHERE member_id = '<member_id>' 
AND class_type = 'Yoga' AND status = 'Confirmed';
```

---

### TC-34: Quick Book - Record in Payments Table

| Attribute | Value |
|-----------|-------|
| **Category** | Booking |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Book a class<br>2. Query payments table |
| **Expected** | Debit record with description |

```sql
SELECT * FROM payments WHERE member_id = '<member_id>' 
AND transaction_type = 'Debit' AND description LIKE '%Yoga%';
```

---

### TC-35: Wallet Balance - Correct Calculation

| Attribute | Value |
|-----------|-------|
| **Category** | Balance |
| **Priority** | 🔴 Critical |
| **Precondition** | Known transaction history |
| **Steps** | 1. Open Wallet Balance<br>2. Compare displayed balance with manual calculation |
| **Expected** | Balance = Credits - Debits |

```sql
-- Manual calculation
SELECT 
  SUM(CASE WHEN transaction_type = 'Credit' THEN amount ELSE -amount END) as expected_balance
FROM payments WHERE member_id = '<member_id>';
```

---

### TC-36: Wallet Balance - Zero Balance

| Attribute | Value |
|-----------|-------|
| **Category** | Balance |
| **Priority** | 🟡 High |
| **Precondition** | No transactions OR Credits = Debits |
| **Steps** | 1. Open Wallet Balance |
| **Expected** | Displays: "Your Wallet Balance: Rs. 0" |

---

### TC-37: Payment History - Shows All Transactions

| Attribute | Value |
|-----------|-------|
| **Category** | History |
| **Priority** | 🔴 Critical |
| **Precondition** | Member has ≥ 3 transactions |
| **Steps** | 1. Open Payment History |
| **Expected** | All transactions listed with date, description, amount, type |
| **DB Check** | Count matches payments table |

```sql
SELECT COUNT(*) FROM payments WHERE member_id = '<member_id>';
-- Should match number of rows displayed
```

---

### TC-38: Payment History - Masked Member ID

| Attribute | Value |
|-----------|-------|
| **Category** | History |
| **Priority** | 🟡 High |
| **Steps** | 1. Open Payment History |
| **Expected** | Member ID displayed as: "XXXX••••1234" (first 4 + masked + last 4) |

---

### TC-39: Payment History - Shows Balance Footer

| Attribute | Value |
|-----------|-------|
| **Category** | History |
| **Priority** | 🟡 High |
| **Steps** | 1. Open Payment History |
| **Expected** | Footer shows: "Current Balance: Rs. X" (correct value) |

---

### TC-40: Change Password - Matching PINs

| Attribute | Value |
|-----------|-------|
| **Category** | Security |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Enter new PIN: 5678<br>2. Re-enter: 5678<br>3. Click "Update" |
| **Expected** | Success: "Password Updated" → Dashboard with new PIN |
| **DB Check** | PIN updated in `login` AND `membership` tables |

```sql
SELECT pin FROM login WHERE member_id = '<member_id>';  -- Should be 5678
SELECT pin FROM membership WHERE member_id = '<member_id>';  -- Should be 5678
```

---

### TC-41: Change Password - Non-Matching PINs

| Attribute | Value |
|-----------|-------|
| **Category** | Security |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Enter new PIN: 5678<br>2. Re-enter: 1234<br>3. Click "Update" |
| **Expected** | Error: "PINs do not match" |
| **DB Check** | No changes |

---

### TC-42: Change Password - Empty Fields

| Attribute | Value |
|-----------|-------|
| **Category** | Security |
| **Priority** | 🟡 High |
| **Steps** | 1. Leave both fields empty<br>2. Click "Update" |
| **Expected** | Error: "Please enter a PIN" |

---

### TC-43: Change Password - Updated in All Tables

| Attribute | Value |
|-----------|-------|
| **Category** | Security |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Change PIN from 1234 to 5678<br>2. Query all tables |
| **Expected** | PIN updated in `login` AND `membership` (and `payments` if stored) |

```sql
-- All should return 5678
SELECT pin FROM login WHERE member_id = '<member_id>';
SELECT pin FROM membership WHERE member_id = '<member_id>';
```

---

### TC-44: All 6 Tables Exist

| Attribute | Value |
|-----------|-------|
| **Category** | Database |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Query database schema |
| **Expected** | All 6 tables exist: `login`, `member`, `member_health`, `membership`, `payments`, `bookings` |

```sql
SHOW TABLES FROM gym_management_system;
-- Expected: login, member, member_health, membership, payments, bookings
```

---

### TC-45: Foreign Key Relationships Valid

| Attribute | Value |
|-----------|-------|
| **Category** | Database |
| **Priority** | 🟡 High |
| **Steps** | 1. Check form_no consistency across member tables<br>2. Check member_id consistency across transaction tables |
| **Expected** | All foreign keys reference valid records |

```sql
-- Every member record should have matching login record
SELECT m.form_no FROM member m 
LEFT JOIN login l ON m.form_no = l.form_no 
WHERE l.form_no IS NULL;  -- Should return 0 rows

-- Every payment should have matching membership
SELECT p.member_id FROM payments p 
LEFT JOIN membership ms ON p.member_id = ms.member_id 
WHERE ms.member_id IS NULL;  -- Should return 0 rows
```

---

### TC-46: E2E - Complete New Member Registration Flow

| Attribute | Value |
|-----------|-------|
| **Category** | E2E |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Open app<br>2. Click "NEW MEMBER"<br>3. Complete Page 1 (personal)<br>4. Complete Page 2 (health)<br>5. Complete Page 3 (membership)<br>6. Note Member ID + PIN<br>7. Make initial payment (₹10,000)<br>8. Reach Dashboard |
| **Expected** | All screens flow correctly, data persisted |
| **DB Check** | Records in all 4 registration tables + 1 payment record |

```sql
-- Verify complete registration
SELECT * FROM member WHERE form_no = '<form_no>';
SELECT * FROM member_health WHERE form_no = '<form_no>';
SELECT * FROM membership WHERE form_no = '<form_no>';
SELECT * FROM login WHERE member_id = '<generated_member_id>';
SELECT * FROM payments WHERE member_id = '<generated_member_id>';
```

---

### TC-47: E2E - Existing Member Login + Add Funds

| Attribute | Value |
|-----------|-------|
| **Category** | E2E |
| **Priority** | 🔴 Critical |
| **Steps** | 1. Open app<br>2. Enter Member ID + PIN<br>3. Login → Dashboard<br>4. Add Funds ₹5,000<br>5. Check Wallet Balance |
| **Expected** | Balance = initial payment + ₹5,000 |
| **DB Check** | Two Credit records in payments |

```sql
SELECT SUM(amount) FROM payments WHERE member_id = '<member_id>' AND transaction_type = 'Credit';
```

---

### TC-48: E2E - Quick Book Class + Balance Deduction

| Attribute | Value |
|-----------|-------|
| **Category** | E2E |
| **Priority** | 🔴 Critical |
| **Precondition** | Wallet balance = ₹10,000 |
| **Steps** | 1. Quick Book: Yoga (₹300)<br>2. Quick Book: HIIT (₹500)<br>3. Check Wallet Balance |
| **Expected** | Balance = ₹10,000 - ₹300 - ₹500 = ₹9,200 |
| **DB Check** | 2 booking records + 2 Debit payment records |

```sql
SELECT COUNT(*) FROM bookings WHERE member_id = '<member_id>';  -- Should be 2
SELECT SUM(CASE WHEN transaction_type='Credit' THEN amount ELSE -amount END) 
FROM payments WHERE member_id = '<member_id>';  -- Should be 9200
```

---

### TC-49: E2E - Payment History Reflects All Actions

| Attribute | Value |
|-----------|-------|
| **Category** | E2E |
| **Priority** | 🔴 Critical |
| **Precondition** | Member has done: initial payment, add funds, 2 bookings |
| **Steps** | 1. Open Payment History |
| **Expected** | 4 transactions listed (2 Credits, 2 Debits) with correct amounts |
| **DB Check** | Count matches payments table |

```sql
SELECT COUNT(*) FROM payments WHERE member_id = '<member_id>';  -- Should be 4
```

---

### TC-50: E2E - Change Password + Login with New PIN

| Attribute | Value |
|-----------|-------|
| **Category** | E2E |
| **Priority** | 🔴 Critical |
| **Precondition** | Logged in with PIN = 1234 |
| **Steps** | 1. Change Password → new PIN: 5678<br>2. Exit app<br>3. Login with same Member ID + PIN 5678<br>4. Verify Dashboard opens |
| **Expected** | Login succeeds with new PIN |
| **DB Check** | PIN = 5678 in `login` table |

```sql
SELECT pin FROM login WHERE member_id = '<member_id>';  -- Should be 5678
```

---

## ✅ PASS/FAIL CRITERIA

| Criteria | Threshold |
|----------|-----------|
| **Critical Tests (🔴)** | 100% must pass (35/35) |
| **High Tests (🟡)** | ≥ 80% must pass (12/15) |
| **Overall Pass Rate** | ≥ 94% (47/50) |

### Failure Handling
- 🔴 Critical failure = **BLOCKER** - cannot proceed
- 🟡 High failure = **Must fix before demo**
- Any SQL injection vulnerability = **IMMEDIATE FIX**

---

## 🧪 TEST EXECUTION ORDER

```
Phase 1: Database Setup
  └─ TC-44 (Tables exist)
  └─ TC-45 (Foreign keys)

Phase 2: Registration Flow
  └─ TC-05 (SignUp Page 1 valid)
  └─ TC-06 (SignUp Page 1 invalid)
  └─ TC-10 (Form number)
  └─ TC-11 (SignUp Page 2 valid)
  └─ TC-12 (BMI calc)
  └─ TC-15 (SignUp Page 3 valid)
  └─ TC-16 (Member ID)
  └─ TC-17 (PIN)
  └─ TC-18 (All tables)
  └─ TC-19 (No plan)

Phase 3: Authentication
  └─ TC-01 (Valid login)
  └─ TC-02 (Invalid login)
  └─ TC-03 (Empty fields)
  └─ TC-04 (Clear button)

Phase 4: Wallet Operations
  └─ TC-21 (Add funds valid)
  └─ TC-22 (Add funds empty)
  └─ TC-23 (Add funds negative)
  └─ TC-26 (Payments record)
  └─ TC-27 (Refund valid)
  └─ TC-28 (Refund exceeds balance)
  └─ TC-30 (Shows balance)
  └─ TC-35 (Balance calc)

Phase 5: Booking Operations
  └─ TC-31 (Quick book valid)
  └─ TC-32 (Insufficient balance)
  └─ TC-33 (Bookings record)
  └─ TC-34 (Payments record)

Phase 6: History & Security
  └─ TC-37 (Payment history)
  └─ TC-38 (Masked ID)
  └─ TC-40 (Change password valid)
  └─ TC-41 (PINs don't match)
  └─ TC-43 (All tables updated)

Phase 7: E2E Flows
  └─ TC-46 (Full registration)
  └─ TC-47 (Login + add funds)
  └─ TC-48 (Booking + deduction)
  └─ TC-49 (History reflects all)
  └─ TC-50 (Change password + re-login)
```
