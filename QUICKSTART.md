# 🚀 Quick Start Guide - FitZone Gym Management

## 5-Minute Setup

### Step 1: Database Setup (2 minutes)
```bash
# Open MySQL or MySQL Workbench
mysql -u root -p

# Run the schema file
source D:\Coding\Banking_System\database_schema.sql

# Verify tables
USE gym_management_system;
SHOW TABLES;
# Should show: login, member, member_health, membership, payments, bookings
```

### Step 2: Environment Variables (1 minute)

**Windows PowerShell:**
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/gym_management_system"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_mysql_password"
```

**Windows CMD:**
```cmd
set DB_URL=jdbc:mysql://localhost:3306/gym_management_system
set DB_USERNAME=root
set DB_PASSWORD=your_mysql_password
```

### Step 3: IntelliJ Setup (2 minutes)

1. **Open Project**
   - File → Open → Select `D:\Coding\Banking_System`

2. **Add Libraries**
   - File → Project Structure → Libraries → `+` → Java
   - Select `mysql-connector-j-9.3.0.jar` from your local path
   - Select `jcalendar-1.4.jar` from your local path

3. **Mark Source Root**
   - Right-click `src` folder → Mark Directory as → Sources Root

4. **Update Library Paths** (if broken)
   - Go to `.idea/libraries/mysql_connector_j_9_3.xml`
   - Change URL to: `file://$PROJECT_DIR$/lib/` (if you create lib folder)
   - Or keep your existing absolute path

### Step 4: Add Icon Files (Optional, 1 minute)

Create placeholder images or download:
- `icons/gym_logo.png` (100x100px)
- `icons/gym_background.jpg` (780x737px or larger)

**Quick placeholder creation:**
- Use Paint or any image editor
- Save files in `icons/` folder

### Step 5: Run Application

1. Open `src/gym_system/main/Main.java`
2. Right-click → Run 'Main.main()'
3. **OR** use terminal:
   ```bash
   # Compile
   javac -d out -cp "lib/*" src/gym_system/main/Main.java src/gym_system/repository/*.java
   
   # Run
   java -cp "out;lib/*" gym_system.main.Main
   ```

---

## Quick Demo Flow (10 minutes)

### 1. Registration (3 min)
- Click "NEW MEMBER"
- Fill Page 1: John Doe, 9876543210, DOB, Male, email, address
- Fill Page 2: O+, 175cm, 70kg (BMI auto-calculates to 22.86), Weight Loss, Beginner
- Fill Page 3: Quarterly plan, check Yoga + Personal Trainer, agree declaration
- **Note down:** Member ID (10 digits) and PIN (4 digits)

### 2. Initial Deposit (1 min)
- Add ₹10,000
- See success message

### 3. Login (1 min)
- Close and re-run app
- Login with Member ID + PIN

### 4. Dashboard Tour (3 min)
- **Wallet Balance:** Should show ₹10,000
- **Quick Book:** Book Yoga (₹300) + HIIT (₹500)
- **Wallet Balance:** Should show ₹9,200
- **Payment History:** Shows 3 transactions (1 Credit, 2 Debits)
- **Change Password:** Change PIN, exit, re-login with new PIN

### 5. Show Off Features (2 min)
- Input validation (try invalid phone/email)
- Balance check (try booking with insufficient funds)
- BMI auto-calculation on Page 2
- Masked Member ID in Payment History

---

## Common Issues & Fixes

### ❌ "Database connection failed"
**Fix:** Check environment variables are set correctly
```powershell
echo $env:DB_URL
echo $env:DB_USERNAME
echo $env:DB_PASSWORD
```

### ❌ "Table doesn't exist"
**Fix:** Run `database_schema.sql` in MySQL
```sql
CREATE DATABASE IF NOT EXISTS gym_management_system;
USE gym_management_system;
-- Then run CREATE TABLE statements from database_schema.sql
```

### ❌ "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Fix:** MySQL Connector JAR not in classpath
- Verify JAR is added to project libraries
- Check `.idea/libraries/mysql_connector_j_9_3.xml` path is valid

### ❌ "icons/gym_logo.png not found"
**Fix:** Create placeholder images or comment out icon loading:
```java
// Temporarily replace with empty ImageIcon
ImageIcon i1 = new ImageIcon(); // Instead of ClassLoader.getSystemResource(...)
```

### ❌ Compilation errors in SignUpTwo
**Fix:** JCalendar library not added
- Add `jcalendar-1.4.jar` to libraries
- Verify import: `import com.toedter.calendar.JDateChooser;`

---

## File Checklist

Before running, verify these exist:

### Source Files (13 total)
- [x] `src/gym_system/main/Main.java`
- [x] `src/gym_system/repository/Conn.java`
- [x] `src/gym_system/repository/Login.java`
- [x] `src/gym_system/repository/Dashboard.java`
- [x] `src/gym_system/repository/SignUp.java`
- [x] `src/gym_system/repository/SignUpTwo.java`
- [x] `src/gym_system/repository/SignUpThree.java`
- [x] `src/gym_system/repository/AddFunds.java`
- [x] `src/gym_system/repository/RequestRefund.java`
- [x] `src/gym_system/repository/QuickBook.java`
- [x] `src/gym_system/repository/WalletBalance.java`
- [x] `src/gym_system/repository/PaymentHistory.java`
- [x] `src/gym_system/repository/ChangePassword.java`

### Configuration Files
- [x] `database_schema.sql`
- [x] `Gym_System.iml`
- [ ] `icons/gym_logo.png` (create or download)
- [ ] `icons/gym_background.jpg` (create or download)

### Documentation
- [x] `docs/01-prd.md` - Product Requirements
- [x] `docs/02-tech-stack-mapping.md` - Migration Guide
- [x] `docs/03-test-cases.md` - 50 Test Cases
- [x] `docs/04-e2e-test-report.md` - E2E Testing Template
- [x] `README_GYM.md` - Full Documentation
- [x] `QUICKSTART.md` - This file

---

## Next Steps After Setup

1. ✅ Run all E2E tests from `docs/04-e2e-test-report.md`
2. 📸 Take screenshots of each screen
3. 🎬 Practice demo 3-5 times
4. 📝 Prepare presentation slides
5. 🎓 Ace your college presentation!

---

## Need Help?

Refer to:
- **Full Documentation:** `docs/01-prd.md`
- **Code Analysis:** `docs/02-tech-stack-mapping.md`
- **Testing:** `docs/03-test-cases.md` and `docs/04-e2e-test-report.md`
- **README:** `README_GYM.md`

Good luck! 🏋️‍♂️💪
