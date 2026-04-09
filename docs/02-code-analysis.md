# Code Analysis - Component Details

## Entry Point: `Main.java`
```java
public static void main(String[] args) {
    Login.startApplication();
}
```
- Launches `Login` frame directly
- No initialization, config loading, or splash screen

---

## Database Layer: `Conn.java`

### How It Works
1. Loads MySQL JDBC driver via `Class.forName("com.mysql.cj.jdbc.Driver")`
2. Reads credentials from environment variables
3. Creates `Connection` and `Statement` objects
4. Both `connection` and `statement` are package-private (accessible to other classes in same package)

### Issues
- ❌ Uses generic `Statement` instead of `PreparedStatement`
- ❌ No connection pooling
- ❌ No connection validation before use
- ❌ Exception handling only prints to console
- ❌ Connection never closed in Conn class (left to callers)
- ⚠️ If `DriverManager.getConnection()` fails, `connection` remains `null` but code continues

---

## Authentication: `Login.java`

### Flow
1. User enters Card No + PIN
2. Query: `SELECT * FROM login WHERE card_no = 'X' AND pin = 'Y'`
3. If `resultSet.next()` → success → open `Transaction(pinNo)`
4. If no match → show error dialog

### Issues
- 🔴 **SQL Injection**: String concatenation in query
- 🔴 **No brute force protection**: Unlimited attempts
- ⚠️ **Connection leak**: `c.connection.close()` only on success path
- ⚠️ **PIN passed in plaintext** to Transaction frame
- ✅ Clear button clears both fields
- ✅ Sign Up button opens registration flow

---

## Registration Flow

### `SignUp.java` (Page 1 - Personal Details)
- Generates random 4-digit **form number**
- Collects: Name, Father's Name, DOB (JDateChooser), Gender, Email, Marital Status, Address, City, State, Pincode
- Validates: Name and Address are required
- Inserts into `signup` table
- Opens `SignUpTwo` with form number

**Issues:**
- ⚠️ Uses `JDateChooser` from abandoned JCalendar library
- ⚠️ Date extraction: `((JTextField)dateChooser.getDateEditor().getUiComponent()).getText()` - fragile
- ❌ No email format validation
- ❌ No pincode format validation (should be numeric)

### `SignUpTwo.java` (Page 2 - Additional Details)
- Collects: Religion, Category, Income, Education, Occupation, PAN, Aadhar, Senior Citizen, Existing Account
- Uses `JComboBox` dropdowns for categorical fields
- Uses `JRadioButton` for boolean fields
- Validates: Aadhar, PAN, Religion, Existing Account are required
- Inserts into `signuptwo` table
- Opens `SignUpThree` with form number

**Issues:**
- ❌ No PAN format validation (ABCDE1234F)
- ❌ No Aadhar format validation (12 digits)
- ❌ No duplicate check for PAN/Aadhar

### `SignUpThree.java` (Page 3 - Account Details)
- User selects Account Type (Saving/Fixed/Current/Recurring)
- **Auto-generates:**
  - 16-digit Card Number: `Math.abs((random.nextLong() % 90000000L) + 5040936000000000L)`
  - 4-digit PIN: `Math.abs((random.nextLong() % 9000L) + 1000L)`
- Checkboxes for services (ATM Card, Internet Banking, etc.)
- Inserts into **both** `signupthree` and `login` tables
- Shows generated credentials via `JOptionPane`
- Redirects to `Deposit` for mandatory initial deposit

**Issues:**
- 🔴 **Card number collision not checked** - could generate duplicate
- 🔴 **PIN collision not checked** - could generate duplicate
- ❌ Services facility uses `else if` chain - only **first** selected checkbox is captured
- ⚠️ Card/PIN displayed in plain text dialog (security risk)
- ❌ `reqStatement` checkbox (declaration) validation is flawed: `if(accountType.equals("")|| facility.equals("") && reqState.equals(""))`

---

## Transaction Operations

### `Transaction.java` (Dashboard)
- Central hub after login
- Displays 7 operation buttons + Exit
- Passes `pinNo` to all child screens
- Undecorated frame (no title bar) - `setUndecorated(true)`
- Background image from `icons/banksystem.jpg`

**Issues:**
- ⚠️ Has a `main` method for standalone testing: `new Transaction("")`
- ⚠️ PIN passed as constructor argument (insecure)

### `Deposit.java`
- User enters amount → inserts `Deposit` record into `bank` table
- Records: `(pinNo, currentDate, "Deposit", amount)`
- Returns to Transaction dashboard on success

**Issues:**
- ❌ No validation for negative amounts
- ❌ No validation for non-numeric input
- ❌ No maximum deposit limit
- ⚠️ Uses `java.util.Date` (deprecated for JDBC - should use `java.sql.Date` or `LocalDateTime`)

### `Withdrawal.java`
- User enters amount → inserts `Withdraw` record into `bank` table
- Records: `(pinNo, currentDate, "Withdraw", amount)`

**Issues:**
- 🔴 **NO BALANCE CHECK** - User can withdraw more than balance (unlike FastCash)
- ❌ No validation for negative amounts
- ❌ No validation for non-numeric input
- ❌ No daily withdrawal limit

### `FastCash.java`
- Preset buttons: ₹100, ₹500, ₹1000, ₹2000, ₹5000, ₹10000
- **Calculates balance** by iterating all transactions
- **Validates sufficient balance** before withdrawal
- Extracts amount from button text: `getText().substring(4)` (removes "Rs. ")

**Issues:**
- ⚠️ Amount parsing: `Integer.parseInt()` - will throw on non-numeric (though hardcoded buttons prevent this)
- ⚠️ Balance calculation uses `int` - could overflow for large transaction volumes
- ✅ Only file with proper balance validation

### `BalanceEnquiry.java`
- Queries all transactions for the PIN
- Calculates balance: +amount for Deposits, -amount for Withdrawals
- Displays balance in JLabel
- Single "Exit" button returns to Transaction

**Issues:**
- ❌ Balance uses `int` - potential overflow
- ❌ No error handling if query fails (just prints to console)

### `MiniStatement.java`
- Shows bank name header
- Displays card number (masked: first 4 + "XXXXXXXX" + last 4)
- Lists all transactions with date, type, amount
- Calculates and shows total balance
- Uses HTML in JLabel: `<html>...<br>...</html>`
- No back button (separate window)
- Opens as standalone window (doesn't hide Transaction)

**Issues:**
- ⚠️ Two separate database connections (one for card, one for transactions)
- ❌ Uses `substring(0,4)` and `substring(12)` - will crash if card_no is not 16 chars
- ⚠️ HTML rendering in JLabel may not handle large transaction lists well

### `PinChange.java`
- User enters new PIN twice
- Validates: PINs must match, not empty
- Updates PIN in **3 tables**: `bank`, `login`, `signupthree`
- Returns to Transaction with new PIN

**Issues:**
- 🔴 **Partial update risk** - if second/third UPDATE fails, PIN is inconsistent across tables
- ❌ No transaction management (no rollback on failure)
- ❌ No old PIN verification (any logged-in user can change without knowing current PIN)
- ❌ No PIN format validation (should be 4 digits)

---

## Data Flow Summary

```
User Action → UI Input → String Concatenation → SQL Query → ResultSet → UI Update
```

All operations follow this pattern. No DTOs, no service layer, no DAO pattern.
