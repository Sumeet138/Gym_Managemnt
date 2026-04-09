# E2E Test Report - FitZone Gym Management System

## Test Execution Summary

| Attribute | Value |
|-----------|-------|
| **Test Date** | [Execute and fill] |
| **Tester** | [Your Name] |
| **Environment** | Windows + IntelliJ IDEA + MySQL 8.0+ |
| **JDK Version** | OpenJDK 21 (recommended) or 24 |
| **Total Tests** | 50 |
| **Passed** | [Fill after execution] |
| **Failed** | [Fill after execution] |
| **Pass Rate** | [Calculate: Passed/Total × 100] |

---

## Pre-Execution Checklist

- [ ] MySQL server is running
- [ ] Database `gym_management_system` created (ran `database_schema.sql`)
- [ ] All 6 tables verified: `login`, `member`, `member_health`, `membership`, `payments`, `bookings`
- [ ] Environment variables set:
  - `DB_URL=jdbc:mysql://localhost:3306/gym_management_system`
  - `DB_USERNAME=root`
  - `DB_PASSWORD=your_password`
- [ ] MySQL Connector/J 9.3.0 added to project libraries
- [ ] JCalendar 1.4 added to project libraries
- [ ] `src` folder marked as Sources Root in IntelliJ
- [ ] Icon files present (can be placeholders for testing):
  - `icons/gym_logo.png`
  - `icons/gym_background.jpg`
- [ ] All 13 Java files compile without errors:
  - `gym_system/main/Main.java`
  - `gym_system/repository/Conn.java`
  - `gym_system/repository/Login.java`
  - `gym_system/repository/Dashboard.java`
  - `gym_system/repository/SignUp.java`
  - `gym_system/repository/SignUpTwo.java`
  - `gym_system/repository/SignUpThree.java`
  - `gym_system/repository/AddFunds.java`
  - `gym_system/repository/RequestRefund.java`
  - `gym_system/repository/QuickBook.java`
  - `gym_system/repository/WalletBalance.java`
  - `gym_system/repository/PaymentHistory.java`
  - `gym_system/repository/ChangePassword.java`

---

## E2E Test Scenarios

### E2E-01: Complete New Member Registration Flow

**Priority:** 🔴 Critical  
**Estimated Time:** 5 minutes

#### Steps:

1. **Launch Application**
   - Run `Main.java`
   - ✅ Expected: Login screen opens with "FITZONE GYM & FITNESS" title

2. **Start Registration**
   - Click "NEW MEMBER" button
   - ✅ Expected: SignUp Page 1 opens with "NEW MEMBER REGISTRATION: XXXX" header

3. **Fill Page 1 (Personal Details)**
   ```
   Full Name: John Doe
   Phone: 9876543210
   DOB: 06/15/1995 (use date picker)
   Gender: Male
   Email: john.doe@example.com
   Emergency Contact: 9876543211
   Address: 123 Fitness Street
   City: Mumbai
   State: Maharashtra
   Pincode: 400001
   ```
   - Click "Next"
   - ✅ Expected: No validation errors, SignUpTwo opens

4. **Fill Page 2 (Health Details)**
   ```
   Blood Group: O+
   Height: 175 cm
   Weight: 70 kg
   BMI: Should auto-show 22.86 (verify calculation)
   Medical Conditions: Leave unchecked (none)
   Previous Injuries: None
   Fitness Goal: Weight Loss
   Experience Level: Beginner
   Preferred Time: Morning (6-10 AM)
   Allergies: None
   ```
   - Click "Next"
   - ✅ Expected: BMI shows 22.86, SignUpThree opens

5. **Fill Page 3 (Membership Plan)**
   ```
   Plan: Quarterly (₹4,000)
   Services: Check "Personal Trainer" and "Yoga Classes"
   Declaration: Check the agreement checkbox
   ```
   - Click "Submit"
   - ✅ Expected: Dialog shows:
     ```
     Registration Successful!
     
     Member ID: 5040936789 (10 digits)
     PIN: 1234 (4 digits)
     
     Please make an initial deposit to activate your account.
     ```
   - ✅ Expected: AddFunds screen opens

6. **Make Initial Deposit**
   - Enter amount: 10000
   - Click "Add Funds"
   - ✅ Expected: Dialog "₹10000 Added to Wallet Successfully"
   - ✅ Expected: Dashboard opens

7. **Verify Database**
   ```sql
   -- Check all tables have data for this member
   SELECT * FROM member WHERE full_name = 'John Doe';
   -- Get form_no, verify in all tables:
   SELECT * FROM member_health WHERE form_no = '<form_no>';
   SELECT * FROM membership WHERE form_no = '<form_no>';
   SELECT * FROM login WHERE member_id = '<member_id>';
   SELECT * FROM payments WHERE member_id = '<member_id>';
   ```
   - ✅ Expected: All queries return records

**Result:** ⬜ PASS / ⬜ FAIL  
**Notes:** ___________________________

---

### E2E-02: Existing Member Login + Add Funds

**Priority:** 🔴 Critical  
**Precondition:** Member from E2E-01 exists  
**Estimated Time:** 2 minutes

#### Steps:

1. **Login**
   - Close and re-run application
   - Enter Member ID: (from E2E-01)
   - Enter PIN: (from E2E-01)
   - Click "LOGIN"
   - ✅ Expected: Dashboard opens with "Welcome to FitZone" message

2. **Add Funds**
   - Click "Add Funds"
   - Enter amount: 5000
   - Click "Add Funds"
   - ✅ Expected: Success dialog
   - ✅ Expected: Returns to Dashboard

3. **Verify Wallet Balance**
   - Click "Wallet Balance"
   - ✅ Expected: Shows "Your Wallet Balance: Rs. 15000" (10000 + 5000)
   - ✅ Expected: Shows "Total Credits: Rs. 15000" and "Total Debits: Rs. 0"

4. **Verify Database**
   ```sql
   SELECT COUNT(*) FROM payments 
   WHERE member_id = '<member_id>' AND transaction_type = 'Credit';
   -- Expected: 2 records
   SELECT SUM(amount) FROM payments 
   WHERE member_id = '<member_id>' AND transaction_type = 'Credit';
   -- Expected: 15000
   ```

**Result:** ⬜ PASS / ⬜ FAIL  
**Notes:** ___________________________

---

### E2E-03: Quick Book Classes + Balance Deduction

**Priority:** 🔴 Critical  
**Precondition:** Wallet balance = ₹15,000  
**Estimated Time:** 3 minutes

#### Steps:

1. **Book Yoga Class**
   - From Dashboard, click "Quick Book Class"
   - Click "Yoga - ₹300"
   - ✅ Expected: Dialog "Class Booked Successfully!"
   - ✅ Expected: Returns to Dashboard

2. **Book HIIT Class**
   - Click "Quick Book Class"
   - Click "HIIT Workout - ₹500"
   - ✅ Expected: Success dialog
   - ✅ Expected: Returns to Dashboard

3. **Check Balance**
   - Click "Wallet Balance"
   - ✅ Expected: Balance = 15000 - 300 - 500 = **₹14,200**
   - ✅ Expected: Total Credits: ₹15,000, Total Debits: ₹800

4. **Verify Database**
   ```sql
   -- Check bookings
   SELECT * FROM bookings WHERE member_id = '<member_id>';
   -- Expected: 2 records (Yoga ₹300, HIIT ₹500)
   
   -- Check payments
   SELECT * FROM payments WHERE member_id = '<member_id>' 
   AND transaction_type = 'Debit';
   -- Expected: 2 records with amounts 300 and 500
   ```

5. **View Payment History**
   - Click "Payment History"
   - ✅ Expected: Shows 4 transactions (2 Credits, 2 Debits)
   - ✅ Expected: Member ID masked as "XXXX••••1234"
   - ✅ Expected: Footer shows "Current Balance: Rs. 14200"

**Result:** ⬜ PASS / ⬜ FAIL  
**Notes:** ___________________________

---

### E2E-04: Insufficient Balance Validation

**Priority:** 🔴 Critical  
**Precondition:** Wallet balance = ₹14,200  
**Estimated Time:** 1 minute

#### Steps:

1. **Attempt Expensive Booking**
   - Click "Quick Book Class"
   - Click "Personal Training - ₹600" (should work)
   - Repeat until balance < ₹250 (cheapest class)

2. **Try to Book with Insufficient Funds**
   - When balance < class amount
   - Click any class button
   - ✅ Expected: Error dialog "Insufficient Wallet Balance. Please add funds first."
   - ✅ Expected: No records inserted in database

3. **Verify Database**
   ```sql
   SELECT SUM(CASE WHEN transaction_type='Credit' THEN amount ELSE -amount END) 
   FROM payments WHERE member_id = '<member_id>';
   -- Should match displayed balance
   ```

**Result:** ⬜ PASS / ⬜ FAIL  
**Notes:** ___________________________

---

### E2E-05: Change Password + Re-Login

**Priority:** 🔴 Critical  
**Precondition:** Logged in member  
**Estimated Time:** 2 minutes

#### Steps:

1. **Change Password**
   - From Dashboard, click "Change Password"
   - Enter New PIN: 5678
   - Re-Enter PIN: 5678
   - Click "Update"
   - ✅ Expected: Dialog "Password Updated Successfully"
   - ✅ Expected: Returns to Dashboard

2. **Verify Database**
   ```sql
   SELECT pin FROM login WHERE member_id = '<member_id>';
   -- Expected: 5678
   SELECT pin FROM membership WHERE member_id = '<member_id>';
   -- Expected: 5678
   ```

3. **Re-Login with New PIN**
   - Exit application
   - Run again
   - Enter Member ID: (same)
   - Enter PIN: 5678
   - Click "LOGIN"
   - ✅ Expected: Dashboard opens successfully

4. **Test Invalid Login**
   - Exit and run again
   - Enter Member ID: (same)
   - Enter PIN: 1234 (old PIN)
   - ✅ Expected: Error "Invalid Member ID or PIN"

**Result:** ⬜ PASS / ⬜ FAIL  
**Notes:** ___________________________

---

### E2E-06: Request Refund with Balance Check

**Priority:** 🟡 High  
**Precondition:** Wallet balance = ₹14,200 (or current)  
**Estimated Time:** 2 minutes

#### Steps:

1. **Open Refund Screen**
   - Click "Request Refund"
   - ✅ Expected: Shows "Available Balance: Rs. 14200" (correct value)

2. **Request Valid Refund**
   - Enter amount: 2000
   - Click "Request Refund"
   - ✅ Expected: Success dialog
   - ✅ Expected: Returns to Dashboard

3. **Verify Balance Deduction**
   - Click "Wallet Balance"
   - ✅ Expected: New balance = 14200 - 2000 = **₹12,200**

4. **Verify Database**
   ```sql
   SELECT * FROM payments 
   WHERE member_id = '<member_id>' AND description = 'Refund Request';
   -- Expected: 1 record with amount 2000
   ```

5. **Test Excess Refund (Optional)**
   - Try to refund amount > balance
   - ✅ Expected: Error "Insufficient Wallet Balance"

**Result:** ⬜ PASS / ⬜ FAIL  
**Notes:** ___________________________

---

### E2E-07: Input Validation Tests

**Priority:** 🟡 High  
**Estimated Time:** 3 minutes

#### SignUp Page 1 Validations:

1. **Empty Name**
   - Leave "Full Name" empty, fill rest
   - ✅ Expected: Error "Name and Address are required"

2. **Invalid Phone**
   - Phone: "12345" (5 digits)
   - ✅ Expected: Error "Please enter a valid 10-digit phone number"

3. **Invalid Email**
   - Email: "notanemail"
   - ✅ Expected: Error "Please enter a valid email address"

4. **Invalid Pincode**
   - Pincode: "123" (3 digits)
   - ✅ Expected: Error "Please enter a valid 6-digit pincode"

#### Add Funds Validations:

1. **Empty Amount**
   - Leave amount empty, click "Add Funds"
   - ✅ Expected: Error "Please enter an amount"

2. **Non-Numeric**
   - Amount: "abc"
   - ✅ Expected: Error dialog (NumberFormatException handled)

3. **Negative Amount**
   - Amount: -500
   - ✅ Expected: Error "Please enter a valid positive amount"

4. **Exceeds Limit**
   - Amount: 50001
   - ✅ Expected: Error "Maximum ₹50,000 per transaction"

#### Change Password Validations:

1. **Empty Fields**
   - Leave both empty, click "Update"
   - ✅ Expected: Error "Please enter a PIN"

2. **Mismatched PINs**
   - New: 1234, Confirm: 5678
   - ✅ Expected: Error "PINs do not match"

3. **Invalid Length**
   - New: 123, Confirm: 123 (3 digits)
   - ✅ Expected: Error "PIN must be exactly 4 digits"

**Result:** ⬜ PASS / ⬜ FAIL  
**Notes:** ___________________________

---

### E2E-08: BMI Auto-Calculation

**Priority:** 🟡 High  
**Estimated Time:** 1 minute

#### Steps:

1. **Open SignUp Page 2**
   - Start new registration, fill Page 1, go to Page 2

2. **Enter Height and Weight**
   - Height: 175 cm
   - Weight: 70 kg
   - Click outside fields (trigger focus lost)
   - ✅ Expected: BMI label shows "22.86"

3. **Verify Categories**
   ```
   Test cases:
   - 160 cm, 50 kg → BMI = 19.53 (Normal - green)
   - 170 cm, 80 kg → BMI = 27.68 (Overweight - yellow/orange)
   - 175 cm, 100 kg → BMI = 32.65 (Obese - red)
   ```

4. **Verify Database**
   - Complete registration
   ```sql
   SELECT bmi FROM member_health WHERE form_no = '<form_no>';
   -- Expected: "22.86" (stored as string)
   ```

**Result:** ⬜ PASS / ⬜ FAIL  
**Notes:** ___________________________

---

## Bug Report Template

If any test fails, document using this template:

```
Bug ID: BUG-001
Test Case: E2E-XX
Severity: 🔴 Critical / 🟡 High / 🟢 Medium
Description: [What went wrong]
Steps to Reproduce:
1. 
2. 
3. 
Expected: [What should happen]
Actual: [What actually happened]
Screenshot: [Attach if UI issue]
Status: Open / Fixed / Won't Fix
```

---

## Final Sign-Off

| Checkpoint | Status |
|------------|--------|
| All 🔴 Critical tests passed | ⬜ Yes / ⬜ No |
| ≥ 80% 🟡 High tests passed | ⬜ Yes / ⬜ No |
| No SQL injection vulnerabilities | ⬜ Yes / ⬜ No |
| All database transactions consistent | ⬜ Yes / ⬜ No |
| UI renders correctly on different resolutions | ⬜ Yes / ⬜ No |
| Application starts without errors | ⬜ Yes / ⬜ No |
| All files compile without warnings | ⬜ Yes / ⬜ No |

### Overall Result: ⬜ PASS FOR DEMO / ⬜ NEEDS FIXES

**Tester Signature:** ___________________  
**Date:** ___________________  
**Reviewer:** ___________________

---

## Post-Test Actions

### If All Tests Pass:
1. Take screenshots of all screens for README
2. Record demo video (optional)
3. Prepare presentation slides
4. Practice demo script 3-5 times

### If Tests Fail:
1. Document all bugs in Bug Report section
2. Prioritize 🔴 Critical fixes
3. Re-test after fixes
4. Update this document with results
