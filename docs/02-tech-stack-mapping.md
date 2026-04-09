# Tech Stack Mapping: Banking → Gym Domain

## 📊 Overview

This document maps every component from the existing Banking System to the new Gym & Fitness Center Management System. Each file, table, field, and UI element is tracked with the exact changes needed.

---

## 🗂️ FILE MAPPING

### Java Source Files

| Banking File | → | Gym File | Status | Changes Required |
|-------------|---|----------|--------|-----------------|
| `Main.java` | → | `Main.java` | ✏️ Refactor | Package rename, launch `Login` |
| `Conn.java` | → | `Conn.java` | ✅ Reuse | No changes (generic DB connector) |
| `Login.java` | → | `Login.java` | ✏️ Refactor | Labels, fields, messages to gym domain |
| `Transaction.java` | → | `Dashboard.java` | ✏️ Refactor | Rename, button labels, background |
| `SignUp.java` | → | `SignUp.java` | ✏️ Refactor | Fields: personal → member details |
| `SignUpTwo.java` | → | `SignUpTwo.java` | ✏️ Refactor | Fields: additional → health info |
| `SignUpThree.java` | → | `SignUpThree.java` | ✏️ Refactor | Fields: account → membership plan |
| `Deposit.java` | → | `AddFunds.java` | ✏️ Refactor | Labels, messages to wallet domain |
| `Withdrawal.java` | → | `RequestRefund.java` | ✏️ Refactor | Labels, add balance display |
| `FastCash.java` | → | `QuickBook.java` | ✏️ Refactor | Buttons: amounts → class types |
| `BalanceEnquiry.java` | → | `WalletBalance.java` | ✏️ Refactor | Labels, messages to wallet domain |
| `MiniStatement.java` | → | `PaymentHistory.java` | ✏️ Refactor | Layout, labels, data source |
| `PinChange.java` | → | `ChangePassword.java` | ✏️ Refactor | Labels, messages to password domain |

### Resource Files

| Banking Resource | → | Gym Resource | Status |
|-----------------|---|-------------|--------|
| `icons/atmicon.jpg` | → | `icons/gym_logo.png` | 🔴 Create New |
| `icons/banksystem.jpg` | → | `icons/gym_background.jpg` | 🔴 Create New |
| `Banking_System.iml` | → | `Gym_System.iml` | ✏️ Rename + Edit |
| `.idea/` configs | → | `.idea/` configs | ✏️ Update paths |

---

## 🗄️ DATABASE MAPPING

### Database Name
| Current | → | New |
|---------|---|-----|
| `bank_management_system` | → | `gym_management_system` |

### Table Mapping

| Banking Table | → | Gym Table | Action |
|--------------|---|-----------|--------|
| `login` | → | `login` | ✏️ Modify columns |
| `signup` | → | `member` | 🔴 Drop + Recreate |
| `signuptwo` | → | `member_health` | 🔴 Drop + Recreate |
| `signupthree` | → | `membership` | 🔴 Drop + Recreate |
| `bank` | → | `payments` + `bookings` | 🔴 Split into 2 tables |

### Column-Level Mapping

#### Table: `login`
| Banking Column | → | Gym Column | Change |
|---------------|---|------------|--------|
| `form_no` | → | `form_no` | ✅ Keep (same) |
| `card_no` | → | `member_id` | ✏️ Rename |
| `pin` | → | `pin` | ✅ Keep (same) |

**SQL:**
```sql
-- Banking
CREATE TABLE login (form_no VARCHAR(10), card_no VARCHAR(20), pin VARCHAR(10));

-- Gym
CREATE TABLE login (form_no VARCHAR(10), member_id VARCHAR(15), pin VARCHAR(10));
```

#### Table: `signup` → `member`
| Banking Column | → | Gym Column | Change |
|---------------|---|------------|--------|
| `form_no` | → | `form_no` | ✅ Keep |
| `name` | → | `full_name` | ✏️ Rename |
| `father_name` | → | `phone` | 🔴 Replace |
| `dob` | → | `dob` | ✅ Keep |
| `gender` | → | `gender` | ✅ Keep |
| `email` | → | `email` | ✅ Keep |
| `marital_status` | → | `emergency_contact` | 🔴 Replace |
| `address` | → | `address` | ✅ Keep |
| `city` | → | `city` | ✅ Keep |
| `state` | → | `state` | ✅ Keep |
| `pincode` | → | `pincode` | ✅ Keep |

#### Table: `signuptwo` → `member_health`
| Banking Column | → | Gym Column | Change |
|---------------|---|------------|--------|
| `form_no` | → | `form_no` | ✅ Keep |
| `religion` | → | `blood_group` | 🔴 Replace |
| `category` | → | `height_cm` | 🔴 Replace |
| `income` | → | `weight_kg` | 🔴 Replace |
| `education` | → | `bmi` | 🔴 Replace |
| `occupation` | → | `medical_conditions` | 🔴 Replace |
| `pan` | → | `previous_injuries` | 🔴 Replace |
| `aadhar` | → | `fitness_goal` | 🔴 Replace |
| `senior_citizen` | → | `experience_level` | 🔴 Replace |
| `existing_account` | → | `preferred_time` | 🔴 Replace |
| *(new)* | → | `allergies` | ➕ Add |

#### Table: `signupthree` → `membership`
| Banking Column | → | Gym Column | Change |
|---------------|---|------------|--------|
| `form_no` | → | `form_no` | ✅ Keep |
| `account_type` | → | `plan_type` | ✏️ Rename |
| `card_no` | → | `member_id` | ✏️ Rename |
| `pin` | → | `pin` | ✅ Keep |
| `facilities` | → | `services` | ✏️ Rename |
| `declaration` | → | `declaration` | ✅ Keep |
| *(new)* | → | `join_date` | ➕ Add |
| *(new)* | → | `expiry_date` | ➕ Add |

#### Table: `bank` → `payments`
| Banking Column | → | Gym Column | Change |
|---------------|---|------------|--------|
| `pin` | → | `member_id` | ✏️ Rename + purpose change |
| `date` | → | `transaction_date` | ✏️ Rename |
| `transaction_type` | → | `transaction_type` | ✅ Keep (values: Credit/Debit) |
| `amount` | → | `amount` | ✅ Keep |
| *(new)* | → | `description` | ➕ Add |

#### Table: *(new)* → `bookings`
| Column | Type | Purpose |
|--------|------|---------|
| `booking_id` | INT AUTO_INCREMENT PK | Unique booking reference |
| `member_id` | VARCHAR(15) | FK to membership |
| `booking_date` | DATETIME | When booking was made |
| `class_type` | VARCHAR(50) | Yoga, Zumba, HIIT, etc. |
| `amount` | INT | Class fee |
| `status` | VARCHAR(20) | Confirmed / Cancelled |

---

## 🔄 CODE-LEVEL CHANGES

### 1. `Conn.java`
```java
// ✅ NO CHANGES NEEDED
// Generic database connector, works for any domain
```

### 2. `Login.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Window Title | "AUTOMATED TELLER MACHINE" | → | "FITZONE GYM & FITNESS" |
| Header Label | "WELCOME TO ATM" | → | "WELCOME TO FITZONE" |
| Label 1 | "Card No: " | → | "Member ID: " |
| Field 1 | `cardTextField` | → | `memberIdField` |
| Label 2 | "PIN: " | → | "PIN: " |
| Field 2 | `pinTextField` | → | `pinTextField` |
| Button 1 | "LOGIN" | → | "LOGIN" |
| Button 2 | "CLEAR" | → | "CLEAR" |
| Button 3 | "SIGN UP" | → | "NEW MEMBER" |
| Success Action | `new Transaction(pinNo)` | → | `new Dashboard(memberId)` |
| Query Column | `card_no` | → | `member_id` |
| Icon | `icons/atmicon.jpg` | → | `icons/gym_logo.png` |

### 3. `Transaction.java` → `Dashboard.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Class Name | `Transaction` | → | `Dashboard` |
| Window Title | "TRANSACTIONS" | → | "MEMBER DASHBOARD" |
| Header | "Please select your Transaction" | → | "Welcome to FitZone - Select Your Action" |
| Background | `icons/banksystem.jpg` | → | `icons/gym_background.jpg` |
| Button 1 | "Deposit" | → | "💰 Add Funds" |
| Button 2 | "Cash Withdraw" | → | "💸 Request Refund" |
| Button 3 | "Fast Cash" | → | "⚡ Quick Book Class" |
| Button 4 | "Mini Statement" | → | "📋 Payment History" |
| Button 5 | "Pin Change" | → | "🔑 Change Password" |
| Button 6 | "Balance Enquiry" | → | "👛 Wallet Balance" |
| Button 7 | "Exit" | → | "🚪 Exit" |
| Variable `pinNo` | `pinNo` | → | `memberId` |
| Constructor | `Transaction(String pinNo)` | → | `Dashboard(String memberId)` |
| Navigate to | `new Deposit(pinNo)` | → | `new AddFunds(memberId)` |
| Navigate to | `new Withdrawal(pinNo)` | → | `new RequestRefund(memberId)` |
| Navigate to | `new FastCash(pinNo)` | → | `new QuickBook(memberId)` |
| Navigate to | `new MiniStatement(pinNo)` | → | `new PaymentHistory(memberId)` |
| Navigate to | `new PinChange(pinNo)` | → | `new ChangePassword(memberId)` |
| Navigate to | `new BalanceEnquiry(pinNo)` | → | `new WalletBalance(memberId)` |

### 4. `SignUp.java` → `SignUp.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Window Title | "NEW ACCOUNT APPLICATION FORM" | → | "NEW MEMBER REGISTRATION" |
| Sub Header | "Page 1: Personal Details" | → | "Page 1: Personal & Contact Details" |
| Label "Full Name" | ✅ Keep | → | "Full Name: " |
| Label "Father's Name" | ❌ Remove | → | "Phone Number: " |
| Label "Date Of Birth" | ✅ Keep | → | "Date Of Birth: " |
| Label "Gender" | ✅ Keep | → | "Gender: " |
| Label "Email" | ✅ Keep | → | "Email Address: " |
| Label "Marital Status" | ❌ Remove | → | "Emergency Contact: " |
| Label "Address" | ✅ Keep | → | "Address: " |
| Label "City" | ✅ Keep | → | "City: " |
| Label "State" | ✅ Keep | → | "State: " |
| Label "Pin Code" | ✅ Keep | → | "Pin Code: " |
| DB Table | `signup` | → | `member` |
| Navigate to | `new SignUpTwo(formNo)` | → | `new SignUpTwo(formNo)` |
| Validation | `name.equals("") \|\| address.equals("")` | → | Same + phone validation |

### 5. `SignUpTwo.java` → `SignUpTwo.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Header | "Page 2: Additional Details" | → | "Page 2: Health & Medical Information" |
| Label "Religion" | ❌ | → | "Blood Group: " |
| Widget | `JComboBox` | → | `JComboBox` (A+, B+, O+, etc.) |
| Label "Category" | ❌ | → | "Height (cm): " |
| Widget | `JComboBox` | → | `JTextField` |
| Label "Income" | ❌ | → | "Weight (kg): " |
| Widget | `JComboBox` | → | `JTextField` |
| Label "Education" | ❌ | → | "BMI: " |
| Widget | `JComboBox` | → | `JLabel` (auto-calculated, display only) |
| Label "Occupation" | ❌ | → | "Medical Conditions: " |
| Widget | `JComboBox` | → | `JCheckBox[]` |
| Label "Pan Number" | ❌ | → | "Previous Injuries: " |
| Widget | `JTextField` | → | `JTextField` |
| Label "Aadhar Number" | ❌ | → | "Fitness Goal: " |
| Widget | `JTextField` | → | `JRadioButton[]` |
| Label "Senior Citizen" | ❌ | → | "Experience Level: " |
| Widget | `JRadioButton` | → | `JRadioButton[]` |
| Label "Existing Account" | ❌ | → | "Preferred Workout Time: " |
| Widget | `JRadioButton` | → | `JComboBox` |
| *(new)* | - | → | "Allergies: " + `JTextField` |
| DB Table | `signuptwo` | → | `member_health` |
| Validation | `aadhar.equals("") \|\| pan.equals("")` | → | `height`, `weight`, `blood_group` required |
| **NEW** | - | → | BMI auto-calculation on height/weight input |

### 6. `SignUpThree.java` → `SignUpThree.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Header | "Page 3: Account Details" | → | "Page 3: Membership Plan Selection" |
| Label "Account Type" | → | "Select Membership Plan: " |
| Radio 1 | "Saving Account" | → | "Monthly (₹1,500)" |
| Radio 2 | "Fixed Deposit Account" | → | "Quarterly (₹4,000)" |
| Radio 3 | "Current Account" | → | "Semi-Annual (₹7,000)" |
| Radio 4 | "Recurring Deposit Account" | → | "Annual (₹12,000)" |
| Label "Card Number" | ❌ | → | "Your Member ID" |
| Label "PIN" | ❌ | → | "Your PIN" |
| Generated 16-digit | Card Number | → | 10-digit Member ID |
| Generated 4-digit | PIN | → | 4-digit PIN (keep same logic) |
| Label "Services Required" | → | "Additional Services: " |
| Checkbox 1 | "ATM CARD" | → | "Personal Trainer (₹3,000/mo)" |
| Checkbox 2 | "Internet Banking" | → | "Yoga Classes (₹500/mo)" |
| Checkbox 3 | "Mobile Banking" | → | "Zumba Classes (₹500/mo)" |
| Checkbox 4 | "EMAIL AND SMS Alerts" | → | "Diet Plan (₹1,000/mo)" |
| Checkbox 5 | "Cheque Book" | → | "Locker Facility (₹300/mo)" |
| Checkbox 6 | "E-Statement" | → | "Steam & Sauna (₹400/mo)" |
| *(new)* | - | → | "Parking (₹200/mo)" |
| Declaration | "I hearby declares..." | → | Same text (fix grammar: "I hereby declare...") |
| DB Table 1 | `signupthree` | → | `membership` |
| DB Table 2 | `login` | → | `login` |
| Navigate to | `new Deposit(pinNo)` | → | `new AddFunds(memberId)` |
| Dialog | "Card Number : X\nPin : Y" | → | "Member ID: X\nPIN: Y" |

### 7. `Deposit.java` → `AddFunds.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Class Name | `Deposit` | → | `AddFunds` |
| Window Title | "DEPOSIT" | → | "ADD FUNDS" |
| Label | "Enter the amount you want to deposit: " | → | "Enter amount to add to your wallet:" |
| TextField | `amountTextField` | → | `amountTextField` (keep) |
| Button 1 | "Deposit" | → | "Add Funds" |
| Button 2 | "Exit" | → | "Back" |
| Success Dialog | "Amount X Deposited Successfully" | → | "₹X Added to Wallet Successfully" |
| DB Table | `bank` | → | `payments` |
| DB Column | `pin` | → | `member_id` |
| Transaction Type | `"Deposit"` | → | `"Credit"` |
| Query | `INSERT INTO bank VALUES(...)` | → | `INSERT INTO payments (member_id, date, type, description, amount) VALUES(...)` |
| Variable | `pinNo` | → | `memberId` |
| Navigate to | `new Transaction(pinNo)` | → | `new Dashboard(memberId)` |

### 8. `Withdrawal.java` → `RequestRefund.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Class Name | `Withdrawal` | → | `RequestRefund` |
| Window Title | "DEPOSIT" (bug!) | → | "REQUEST REFUND" |
| Label | "Enter the amount you want to withdraw: " | → | "Enter refund amount:" |
| TextField | `amtTextField` | → | `amtTextField` |
| Button 1 | "Withdraw" | → | "Request Refund" |
| Button 2 | "Exit" | → | "Back" |
| Success Dialog | "Amount X Withdraw Successfully" | → | "₹X Refund Requested Successfully" |
| DB Table | `bank` | → | `payments` |
| Transaction Type | `"Withdraw"` | → | `"Debit"` |
| **NEW** | - | → | Display current balance above input |
| **NEW** | - | → | Validate amount ≤ balance |
| Variable | `pinNo` | → | `memberId` |

### 9. `FastCash.java` → `QuickBook.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Class Name | `FastCash` | → | `QuickBook` |
| Window Title | "FAST CASH" | → | "QUICK BOOK CLASS" |
| Label | "Withdraw the Amount you want : " | → | "Select a class to book:" |
| Button 1 | "Rs. 100" | → | "Yoga - ₹300" |
| Button 2 | "Rs. 500" | → | "Zumba - ₹400" |
| Button 3 | "Rs. 1000" | → | "HIIT Workout - ₹500" |
| Button 4 | "Rs. 2000" | → | "Personal Training - ₹600" |
| Button 5 | "Rs. 5000" | → | "CrossFit - ₹350" |
| Button 6 | "Rs. 10000" | → | "Cardio Session - ₹250" |
| Button 7 | "Exit" | → | "Back" |
| Amount Extract | `.substring(4)` | → | Parse from button text (last digits) |
| DB Table 1 | `bank` | → | `bookings` |
| DB Table 2 | `bank` (for balance) | → | `payments` (for balance) |
| Transaction Type | `"Withdraw"` | → | `"Debit"` (in payments) |
| Balance Check | ✅ Keep same logic | → | ✅ Keep |
| Dialog | "Rs. X debited successfully" | → | "Class booked successfully!" |
| **NEW** | - | → | Also insert into `bookings` table |

### 10. `BalanceEnquiry.java` → `WalletBalance.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Class Name | `BalanceEnquiry` | → | `WalletBalance` |
| Window Title | "PIN CHANGE" (bug!) | → | "WALLET BALANCE" |
| Label | "Your Account Balance : Rs. X" | → | "Your Wallet Balance: Rs. X" |
| Button | "Exit" | → | "Back" |
| DB Table | `bank` | → | `payments` |
| Balance Column | `pin` | → | `member_id` |
| Transaction Type Check | `"Deposit"` | → | `"Credit"` |
| Transaction Type Check | else (Withdraw) | → | else (`"Debit"`) |
| Variable | `pinNo` | → | `memberId` |

### 11. `MiniStatement.java` → `PaymentHistory.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Class Name | `MiniStatement` | → | `PaymentHistory` |
| Window Title | "MINI STATEMENT" | → | "PAYMENT HISTORY" |
| Header | "SILVER VALLEY BANK" | → | "FITZONE GYM & FITNESS" |
| Card Label | "Card Number: XXXXXXXXXXXX1234" | → | "Member ID: XXXX••••1234" |
| Balance Label | "Your Account balance: Rs. X" | → | "Current Balance: Rs. X" |
| DB Table (card) | `login` | → | `membership` |
| DB Table (transactions) | `bank` | → | `payments` |
| Transaction Column | `pin` | → | `member_id` |
| Display Format | `date + type + amount` | → | `date + description + amount` |
| Variable | `pinNo` | → | `memberId` |

### 12. `PinChange.java` → `ChangePassword.java`

| Element | Banking Value | → | Gym Value |
|---------|--------------|---|-----------|
| Class Name | `PinChange` | → | `ChangePassword` |
| Window Title | "PIN CHANGE" | → | "CHANGE PASSWORD" |
| Label 1 | "CHANGE YOUR PIN: " | → | "Enter New PIN (4 digits): " |
| Label 2 | "RE-ENTER YOUR PIN: " | → | "Re-Enter PIN: " |
| Button 1 | "Change" | → | "Update" |
| Button 2 | "Exit" | → | "Back" |
| Error Dialog | "Entered Pin does not match." | → | "PINs do not match." |
| Success Dialog | "Pin Changed Successfully." | → | "Password Updated Successfully." |
| DB Tables Updated | `bank`, `login`, `signUpThree` | → | `payments`, `login`, `membership` |
| Variable | `pinNo` | → | `memberId` |
| Navigate to | `new Transaction(rePin)` | → | `new Dashboard(newPin)` |

---

## 🎨 UI THEME CHANGES

### Color Palette

| Element | Banking | → | Gym |
|---------|---------|---|-----|
| Login Background | `Color.BLACK` | → | `#1a1a2e` (Dark Navy) |
| Login Button | `Color.ORANGE` | → | `#ff6b35` (Custom Orange) |
| Form Background | `Color.WHITE` | → | `Color.WHITE` (same) |
| Dashboard BG | `icons/banksystem.jpg` | → | `icons/gym_background.jpg` |

### Font Changes

| Banking Font | → | Gym Font |
|-------------|---|----------|
| "Osward" | → | "Segoe UI" |
| "Railway" | → | "Segoe UI" |
| "Arial" | → | "Segoe UI" |
| "System" | → | "Segoe UI" |

---

## 📦 LIBRARY DEPENDENCIES

| Library | Banking Usage | → | Gym Usage | Change |
|---------|--------------|---|-----------|--------|
| MySQL Connector/J 9.3.0 | DB connectivity | → | DB connectivity | ✅ Same |
| JCalendar 1.4 | DOB in SignUp | → | DOB in SignUp | ✅ Same |
| Java Swing | All UI | → | All UI | ✅ Same |
| Java AWT | Events, Layout | → | Events, Layout | ✅ Same |

---

## 🗑️ FILES TO DELETE

| File | Reason |
|------|--------|
| `screenshot/*.PNG` (old) | Banking screenshots, replace with gym screenshots |
| `icons/atmicon.jpg` | Replace with gym logo |
| `icons/banksystem.jpg` | Replace with gym background |
| `Banking_System.iml` | Rename to `Gym_System.iml` |

---

## ➕ FILES TO CREATE

| File | Purpose |
|------|---------|
| `icons/gym_logo.png` | Gym logo for login screen |
| `icons/gym_background.jpg` | Gym background for dashboard |
| `screenshot/Gym_Login.PNG` | New screenshot |
| `screenshot/Gym_Dashboard.PNG` | New screenshot |
| `screenshot/Gym_SignUp.PNG` | New screenshot |
| `Gym_System.iml` | Renamed module file |
| `docs/01-prd.md` | Product Requirements Document (✅ Created) |
| `docs/02-tech-stack-mapping.md` | This file (✅ Creating) |
| `docs/03-test-cases.md` | TDD test cases (Next) |
| `docs/04-e2e-test-report.md` | E2E test results (Last) |

---

## 🔧 CONFIGURATION CHANGES

### Environment Variables

| Variable | Banking Value | → | Gym Value |
|----------|--------------|---|-----------|
| `DB_URL` | `jdbc:mysql://localhost:3306/bank_management_system` | → | `jdbc:mysql://localhost:3306/gym_management_system` |
| `DB_USERNAME` | *(same)* | → | *(same)* |
| `DB_PASSWORD` | *(same)* | → | *(same)* |

### `.idea/libraries/mysql_connector_j_9_3.xml`
```xml
<!-- Change from hardcoded path to project-relative -->
<root url="file://$PROJECT_DIR$/lib/mysql-connector-j-9.3.0" />
```

### `.idea/libraries/jcalendar_1_4.xml`
```xml
<!-- Change from user-specific path to project-relative -->
<root url="jar://$PROJECT_DIR$/lib/jcalendar-1.4.jar!/" />
```

---

## 📊 CHANGE SUMMARY

| Category | Files to Modify | Files to Create | Files to Delete | Lines Changed (Est.) |
|----------|----------------|----------------|----------------|---------------------|
| **Java Source** | 12 | 0 | 0 | ~2,500 |
| **Resources** | 0 | 2 icons | 2 icons | - |
| **Config** | 3 | 1 (module) | 1 (module) | ~50 |
| **Database** | 5 tables modify | 1 table new | 0 | ~100 SQL |
| **Documentation** | 1 (README) | 4 docs | - | ~500 |

**Total Estimated Changes:** ~3,150 lines across 20 files

---

## ⚠️ KNOWN BANKING BUGS TO FIX

| Bug | Location | Fix |
|-----|----------|-----|
| `Withdrawal.java` title says "DEPOSIT" | `setTitle("DEPOSIT")` | Change to "REQUEST REFUND" |
| `BalanceEnquiry.java` title says "PIN CHANGE" | `setTitle("PIN CHANGE")` | Change to "WALLET BALANCE" |
| `SignUpThree.java` grammar: "hearby declares" | Declaration checkbox | Fix to "hereby declare" |
| `SignUpThree.java` services: `else if` chain | Facility checkboxes | Change to independent `if` statements |
| `Withdrawal.java` no balance check | Withdrawal flow | Add balance validation |
| SQL injection (all files) | All queries | Use `PreparedStatement` |
