# Security Audit Report

## 🔴 CRITICAL Vulnerabilities

### 1. SQL Injection (All Files with DB Queries)
**Severity:** CRITICAL  
**Location:** `Login.java`, `SignUp.java`, `SignUpTwo.java`, `SignUpThree.java`, `Deposit.java`, `Withdrawal.java`, `BalanceEnquiry.java`, `MiniStatement.java`, `FastCash.java`, `PinChange.java`

**Vulnerable Pattern:**
```java
// Login.java - Line ~112
String query = "SELECT * FROM login WHERE card_no = '" + cardNo + "' AND pin = '" + pinNo + "'";
```

**Attack Vector:**
```
Card No: ' OR '1'='1' --
PIN: [anything]
Result: Query becomes SELECT * FROM login WHERE card_no = '' OR '1'='1' --' AND pin = ''
```

**Impact:**
- Bypass authentication
- Extract/delete/modify all data
- DROP tables

**Fix:**
```java
String query = "SELECT * FROM login WHERE card_no = ? AND pin = ?";
PreparedStatement pstmt = connection.prepareStatement(query);
pstmt.setString(1, cardNo);
pstmt.setString(2, pinNo);
ResultSet rs = pstmt.executeQuery();
```

---

### 2. Plain Text PIN Storage
**Severity:** CRITICAL  
**Location:** `login`, `signupthree`, `bank` tables

**Issue:**
- PINs stored as plain strings in database
- No hashing (bcrypt, SHA-256, PBKDF2)
- Visible in `SELECT *` queries
- Exposed in MiniStatement logs

**Impact:**
- Database breach = all credentials compromised
- Insider threat (DBA can see all PINs)
- No compliance with security standards

**Fix:**
```java
// Hash PIN before storage
MessageDigest digest = MessageDigest.getInstance("SHA-256");
byte[] hash = digest.digest(pin.getBytes(StandardCharsets.UTF_8));
String hashedPin = Base64.getEncoder().encodeToString(hash);

// Verify by comparing hashes
```

---

## 🟡 HIGH Severity Issues

### 3. No Input Validation
**Location:** `Deposit.java`, `Withdrawal.java`, `FastCash.java`

**Issues:**
- Negative amounts accepted: `-5000`
- Non-numeric input: `abc` (causes SQL error or Integer.parseInt crash)
- No maximum limit: `999999999`
- Zero amount deposits/withdrawals allowed

**Fix:**
```java
String amount = amountTextField.getText().trim();
if (!amount.matches("\\d+")) {
    JOptionPane.showMessageDialog(null, "Please enter a valid positive number.");
    return;
}
int amt = Integer.parseInt(amount);
if (amt <= 0 || amt > 100000) {
    JOptionPane.showMessageDialog(null, "Amount must be between 1 and 100,000.");
    return;
}
```

---

### 4. No Session Management
**Location:** `Transaction.java` and all child screens

**Issue:**
- PIN passed as constructor argument between screens
- Stored in plain String field: `String pinNo;`
- No session timeout
- No re-authentication for sensitive operations (PIN change)

**Impact:**
- If screen object is serialized/logged, PIN exposed
- No automatic logout

---

### 5. No Rate Limiting / Brute Force Protection
**Location:** `Login.java`

**Issue:**
- Unlimited login attempts
- No account lockout
- No CAPTCHA
- No delay between attempts

**Impact:**
- Automated brute force attack possible
- 4-digit PIN = max 10,000 attempts

**Fix:**
```java
// Track failed attempts per card
private static Map<String, Integer> failedAttempts = new HashMap<>();

if (failedAttempts.getOrDefault(cardNo, 0) >= 5) {
    JOptionPane.showMessageDialog(null, "Account locked. Too many failed attempts.");
    return;
}
// On failure:
failedAttempts.put(cardNo, failedAttempts.getOrDefault(cardNo, 0) + 1);
```

---

### 6. Inconsistent PIN Update (No Transaction Management)
**Location:** `PinChange.java`

**Issue:**
```java
String query = "UPDATE bank SET pin = '"+rePin+"' WHERE pin = '"+pinNo+"'";
String query1 = "UPDATE login SET pin = '"+rePin+"' WHERE pin = '"+pinNo+"'";
String query2 = "UPDATE signupThree SET pin = '"+rePin+"' WHERE pin = '"+pinNo+"'";

conn.statement.executeUpdate(query);   // ✅ succeeds
conn.statement.executeUpdate(query1);  // ✅ succeeds
conn.statement.executeUpdate(query2);  // ❌ fails - inconsistent state!
```

**Impact:**
- If 3rd UPDATE fails, PIN is different across tables
- User locked out (login table has new PIN, bank table has old)

**Fix:**
```java
connection.setAutoCommit(false);
try {
    statement.executeUpdate(query);
    statement.executeUpdate(query1);
    statement.executeUpdate(query2);
    connection.commit();
} catch (Exception e) {
    connection.rollback();
    throw e;
} finally {
    connection.setAutoCommit(true);
}
```

---

## 🟢 MEDIUM Severity Issues

### 7. Error Information Exposure
**Location:** All catch blocks

**Issue:**
```java
catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
}
```

**Impact:**
- Stack traces exposed to users
- SQL error messages reveal table structure
- Helpful for attackers

**Fix:**
```java
catch (Exception e) {
    e.printStackTrace(); // Log to file only
    JOptionPane.showMessageDialog(null, "An unexpected error occurred. Please contact support.");
}
```

---

### 8. No Audit Trail
**Location:** Entire application

**Issue:**
- No logging framework
- No record of who did what and when
- Failed login attempts not tracked
- No transaction ID for operations

**Recommendation:**
Add SLF4J + Logback or java.util.logging:
```java
private static final Logger logger = LoggerFactory.getLogger(Login.class);

logger.info("Login attempt for card: {}", cardNo);
logger.error("Authentication failed: invalid credentials");
```

---

## Security Checklist

| Control | Status | Priority |
|---------|--------|----------|
| SQL Injection Prevention | ❌ Missing | 🔴 CRITICAL |
| Password/PIN Hashing | ❌ Missing | 🔴 CRITICAL |
| Input Validation | ❌ Missing | 🟡 HIGH |
| Rate Limiting | ❌ Missing | 🟡 HIGH |
| Transaction Management | ❌ Missing | 🟡 HIGH |
| Session Timeout | ❌ Missing | 🟡 HIGH |
| Secure Error Handling | ❌ Missing | 🟢 MEDIUM |
| Audit Logging | ❌ Missing | 🟢 MEDIUM |
| HTTPS/TLS (DB connection) | ⚠️ Depends on MySQL config | 🟢 MEDIUM |
| Principle of Least Privilege (DB user) | ⚠️ Unknown | 🟢 MEDIUM |
