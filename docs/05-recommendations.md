# Findings Summary & Recommendations

## Priority Matrix

### 🔴 Critical (Fix Immediately)

| # | Issue | File(s) | Impact | Effort |
|---|-------|---------|--------|--------|
| 1 | **SQL Injection** | All files with DB queries | Complete system compromise | Low |
| 2 | **Plain text PIN storage** | `login`, `signupthree`, `bank` tables | Credential theft on breach | Medium |
| 3 | **No build system** | Project config | Cannot reproduce builds | Medium |
| 4 | **Withdrawal without balance check** | `Withdrawal.java` | Unlimited overdraft | Low |

### 🟡 High (Fix Soon)

| # | Issue | File(s) | Impact | Effort |
|---|-------|---------|--------|--------|
| 5 | **No input validation** | `Deposit`, `Withdrawal`, `FastCash` | Invalid data, crashes | Low |
| 6 | **No brute force protection** | `Login.java` | Account compromise | Low |
| 7 | **Inconsistent PIN update** | `PinChange.java` | Data inconsistency | Low |
| 8 | **JDK 24 (non-LTS)** | Project config | Stability, support | Low |
| 9 | **Abandoned JCalendar library** | `SignUp.java` | Compatibility risk | Medium |
| 10 | **Hardcoded library paths** | `.idea/libraries/*.xml` | Not portable | Low |

### 🟢 Medium (Plan for Future)

| # | Issue | File(s) | Impact | Effort |
|---|-------|---------|--------|--------|
| 11 | **Null layout management** | All UI files | Resolution/DPI issues | High |
| 12 | **No error handling framework** | All files | Information leakage | Low |
| 13 | **No audit logging** | Entire app | No forensic capability | Medium |
| 14 | **Integer overflow risk** | `BalanceEnquiry`, `FastCash` | Incorrect balances | Low |
| 15 | **Card/PIN collision** | `SignUpThree.java` | Duplicate credentials | Low |
| 16 | **Services checkbox bug** | `SignUpThree.java` | Only first service saved | Low |
| 17 | **No transaction management** | `PinChange.java`, all writes | Partial updates | Low |

---

## Quick Wins (Low Effort, High Impact)

### 1. Fix SQL Injection (1 hour per file)
Replace all `Statement` with `PreparedStatement`:
```java
// Before
String query = "SELECT * FROM login WHERE card_no = '" + cardNo + "' AND pin = '" + pinNo + "'";
ResultSet rs = statement.executeQuery(query);

// After
String query = "SELECT * FROM login WHERE card_no = ? AND pin = ?";
PreparedStatement pstmt = connection.prepareStatement(query);
pstmt.setString(1, cardNo);
pstmt.setString(2, pinNo);
ResultSet rs = pstmt.executeQuery();
```

### 2. Add Input Validation (30 mins)
```java
// In Deposit.java, Withdrawal.java
String amount = amountTextField.getText().trim();
if (!amount.matches("\\d+") || amount.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
    return;
}
int amt = Integer.parseInt(amount);
if (amt <= 0) {
    JOptionPane.showMessageDialog(null, "Amount must be greater than 0.");
    return;
}
```

### 3. Add Balance Check to Withdrawal (15 mins)
Copy logic from `FastCash.java`:
```java
ResultSet rs = conn.statement.executeQuery("SELECT * FROM bank WHERE pin = '"+pinNo+"'");
int balance = 0;
while(rs.next()) {
    if(rs.getString("transaction_type").equals("Deposit"))
        balance += Integer.parseInt(rs.getString("amount"));
    else
        balance -= Integer.parseInt(rs.getString("amount"));
}
if (balance < withdrawAmount) {
    JOptionPane.showMessageDialog(null, "Insufficient Balance");
    return;
}
```

### 4. Fix Hardcoded Paths (15 mins)
- Place JARs in `lib/` folder
- Update `.idea/libraries/*.xml` to use `$PROJECT_DIR$/lib/`

---

## Medium Effort Improvements

### 5. Add Maven Build System
```bash
# At project root
mvn archetype:generate -DgroupId=com.banking -DartifactId=banking-system -DarchetypeArtifactId=maven-archetype-quickstart
```
Then add dependencies to `pom.xml` and move source files.

### 6. Downgrade to JDK 21
1. Install JDK 21
2. Update `.idea/misc.xml`:
   ```xml
   <component name="ProjectRootManager" version="2" languageLevel="JDK_21" 
              project-jdk-name="21" project-jdk-type="JavaSDK">
   ```
3. Update IDE settings

### 7. Replace JCalendar with LGoodDatePicker
1. Add Maven dependency:
   ```xml
   <dependency>
       <groupId>com.github.lgooddatepicker</groupId>
       <artifactId>LGoodDatePicker</artifactId>
       <version>11.2.1</version>
   </dependency>
   ```
2. Replace in `SignUp.java`:
   ```java
   // Before
   JDateChooser dateChooser = new JDateChooser();
   String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();

   // After
   DatePicker datePicker = new DatePicker();
   LocalDate dob = datePicker.getDate();
   ```

### 8. Add Transaction Management to PIN Change
```java
try {
    connection.setAutoCommit(false);
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

## Long-term Architectural Changes

### 9. Migrate to JavaFX
- Modern UI framework (still supported by Oracle/OpenJFX)
- CSS styling
- FXML for separation of UI and logic
- Better DPI awareness

### 10. Add Proper Logging
Replace `printStackTrace()` with SLF4J + Logback:
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.9</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.4.11</version>
</dependency>
```

### 11. Implement Service Layer
Separate concerns:
```
UI Layer (Swing/JavaFX)
    ↓
Service Layer (Business Logic)
    ↓
DAO Layer (Data Access)
    ↓
Database (MySQL)
```

### 12. Add Unit Tests
Use JUnit 5 + Mockito:
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

---

## Risk Assessment

| Risk Category | Current Level | After Quick Wins | After All Fixes |
|---------------|--------------|------------------|-----------------|
| **Security** | 🔴 Critical | 🟡 Medium | 🟢 Low |
| **Stability** | 🟡 Medium | 🟢 Low | 🟢 Low |
| **Maintainability** | 🔴 Critical | 🟡 Medium | 🟢 Low |
| **Portability** | 🔴 Critical | 🟡 Medium | 🟢 Low |
| **Compliance** | 🔴 Critical | 🟡 Medium | 🟢 Low |

---

## Files Modified/Created in This Audit

| File | Purpose |
|------|---------|
| `docs/01-project-overview.md` | Architecture, schema, flows |
| `docs/02-code-analysis.md` | Component-level code review |
| `docs/03-security-audit.md` | Vulnerability assessment |
| `docs/04-tech-stack-audit.md` | Dependency & tooling review |
| `docs/05-recommendations.md` | This file - prioritized action items |

---

## Unverified Items (Requires Investigation)

- [ ] `/src/Main.java` - Root-level file contents unknown
- [ ] Database column types and constraints
- [ ] Actual MySQL server version in use
- [ ] Icon files (`icons/atmicon.jpg`, `icons/banksystem.jpg`)
- [ ] Runtime behavior (crashes, UI glitches)
- [ ] Missing screenshots (`Transaction.PNG`, `Withdrawal.PNG`, `SignUpTwo.PNG`)
