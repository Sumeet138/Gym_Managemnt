# Project Overview - Banking System

## Abstract
A Java Swing-based ATM/Banking Management System that simulates ATM operations with a GUI frontend and MySQL database backend.

## Tech Stack
| Component | Technology | Version | Status |
|-----------|-----------|---------|--------|
| **Language** | Java (OpenJDK) | 24 | ⚠️ Non-LTS, bleeding edge |
| **GUI** | Java Swing + AWT | Legacy | ⚠️ Deprecated |
| **Database** | MySQL | 8.0+ | ✅ Stable |
| **JDBC Driver** | MySQL Connector/J | 9.3.0 | ✅ Latest |
| **Date Picker** | JCalendar | 1.4 (2008) | ❌ Abandoned |
| **IDE** | IntelliJ IDEA | - | ✅ OK |
| **Build System** | None (Manual) | - | ❌ Missing |

## Project Structure
```
Banking_System/
├── docs/                          # Audit & documentation (NEW)
├── src/
│   ├── banking_system/
│   │   ├── main/
│   │   │   └── Main.java          # Entry point
│   │   └── repository/
│   │       ├── Conn.java          # Database connection
│   │       ├── Login.java         # Authentication screen
│   │       ├── Transaction.java   # Main dashboard
│   │       ├── SignUp.java        # Registration page 1
│   │       ├── SignUpTwo.java     # Registration page 2
│   │       ├── SignUpThree.java   # Registration page 3
│   │       ├── Deposit.java       # Deposit screen
│   │       ├── Withdrawal.java    # Withdrawal screen
│   │       ├── FastCash.java      # Quick withdrawal
│   │       ├── BalanceEnquiry.java # Balance check
│   │       ├── MiniStatement.java # Transaction history
│   │       └── PinChange.java     # PIN update screen
│   └── Main.java                  # (Unverified - root level)
├── screenshot/                    # UI screenshots
├── .idea/                         # IntelliJ config
├── Banking_System.iml             # Module file
└── README.md                      # Project documentation
```

## User Flows

### New User Registration
```
Login → Sign Up → SignUpTwo → SignUpThree → [Auto-generate Card/PIN] → Deposit → Transaction Dashboard
```

### Existing User
```
Login (Card No + PIN) → Transaction Dashboard → [Banking Operations]
```

## Database Schema

### Tables
| Table | Purpose | Key Columns |
|-------|---------|-------------|
| `login` | Authentication | form_no, card_no, pin |
| `signup` | Personal details | form_no, name, father_name, dob, gender, email, marital_status, address, city, state, pincode |
| `signuptwo` | Additional details | form_no, religion, category, income, education, occupation, pan, aadhar, senior_citizen, existing_account |
| `signupthree` | Account preferences | form_no, account_type, card_no, pin, facilities, declaration |
| `bank` | Transactions | pin, date, transaction_type, amount |

### Balance Calculation
No dedicated balance column. Balance is **computed dynamically**:
```java
if (type.equals("Deposit")) balance += amount;
else balance -= amount;
```

## Dependencies

### External Libraries
1. **MySQL Connector/J 9.3.0**
   - Location: `../../../Default/mysql-connector-j-9.3.0` (hardcoded absolute path)
   - Driver: `com.mysql.cj.jdbc.Driver`

2. **JCalendar 1.4**
   - Location: `$USER_HOME$/Riddhi/lib/jcalendar-1.4.jar` (hardcoded user path)
   - Used for: `JDateChooser` in SignUp.java

### Environment Variables
- `DB_URL` - Database connection string
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password
