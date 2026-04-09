# 💪 FitZone - Gym & Fitness Center Management System

The **FitZone Gym Management System** is a comprehensive desktop application built in Java that provides gym owners and members with essential membership management, payment tracking, and class booking features.

## ✨ Features

### 🔐 Member Authentication
- **Secure Login**: Member ID + PIN authentication
- **3-Step Registration**: Personal → Health → Membership plan selection
- **Auto-Generated Credentials**: Unique 10-digit Member ID + 4-digit PIN

### 💰 Wallet & Payments
- **💵 Add Funds**: Top-up your gym wallet
- **💸 Request Refund**: Withdraw funds with balance validation
- **👛 Wallet Balance**: Check current wallet balance
- **📋 Payment History**: View complete transaction history

### 📅 Class Booking
- **⚡ Quick Book**: Instant booking for popular classes:
  - Yoga (₹300), Zumba (₹400), HIIT Workout (₹500)
  - Personal Training (₹600), CrossFit (₹350), Cardio (₹250)
- **Balance Check**: Automatic balance validation before booking
- **Booking Records**: Complete class booking history

### 🏋️ Membership Management
- **Multiple Plans**: Monthly (₹1,500), Quarterly (₹4,000), Semi-Annual (₹7,000), Annual (₹12,000)
- **Additional Services**: Personal Trainer, Yoga, Zumba, Diet Plan, Locker, Steam & Sauna, Parking
- **Health Tracking**: BMI calculation, medical conditions, fitness goals
- **Password Change**: Update PIN with transaction safety

## 🏗️ System Architecture

```
FitZone Gym Management System
│
├── 🖥️ GUI Layer (Java Swing)
│   ├── Main.java (Entry Point)
│   ├── Login.java → Dashboard.java (Existing Members)
│   ├── SignUp.java → SignUpTwo.java → SignUpThree.java → AddFunds.java (New Members)
│   └── Dashboard Operations:
│       ├── AddFunds.java
│       ├── RequestRefund.java
│       ├── QuickBook.java
│       ├── WalletBalance.java
│       ├── PaymentHistory.java
│       └── ChangePassword.java
│
├── 🔄 Database Connection Layer
│   └── Conn.java (Database Connectivity with PreparedStatement)
│
└── 🗄️ Data Layer (MySQL Database)
    └── gym_management_system DB
        ├── login table (Authentication)
        ├── member table (Personal details)
        ├── member_health table (Health info + BMI)
        ├── membership table (Plans + Services)
        ├── payments table (Wallet transactions)
        └── bookings table (Class bookings)
```

## 🗃️ Database Schema

### Database: `gym_management_system`

#### Tables (Run `database_schema.sql` to create)

1. **🔑 login** - Member authentication (form_no, member_id, pin)
2. **👤 member** - Personal & contact details (name, phone, email, address, etc.)
3. **🏥 member_health** - Health info (blood group, height, weight, BMI, goals, etc.)
4. **🎫 membership** - Plan details (plan_type, member_id, pin, services, dates)
5. **💰 payments** - Wallet transactions (member_id, date, type, description, amount)
6. **📅 bookings** - Class bookings (member_id, date, class_type, amount, status)

## 🚀 Installation

### Prerequisites
- ☕ Java Development Kit (JDK 21 recommended)
- 🗄️ MySQL Database Server (8.0+)
- 💻 IDE (IntelliJ IDEA recommended)
- 📦 MySQL Connector/J 9.3.0
- 📅 JCalendar 1.4 (for date picker)

### Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Banking_System
   ```

2. **Database Setup**
   ```bash
   mysql -u root -p < database_schema.sql
   ```
   Or manually run the SQL in `database_schema.sql` in MySQL Workbench.

3. **Configure Environment Variables**
   ```bash
   # Windows (PowerShell)
   $env:DB_URL="jdbc:mysql://localhost:3306/gym_management_system"
   $env:DB_USERNAME="root"
   $env:DB_PASSWORD="your_password"

   # Windows (CMD)
   set DB_URL=jdbc:mysql://localhost:3306/gym_management_system
   set DB_USERNAME=root
   set DB_PASSWORD=your_password
   ```

4. **Add Libraries**
   - Place `mysql-connector-j-9.3.0.jar` in `lib/` folder
   - Place `jcalendar-1.4.jar` in `lib/` folder
   - In IntelliJ: File → Project Structure → Libraries → Add JARs

5. **Add Icon Files**
   - Place `gym_logo.png` (100x100) in `icons/` folder
   - Place `gym_background.jpg` (780x737+) in `icons/` folder

6. **Compile and Run**
   ```bash
   # Using IntelliJ - just run Main.java
   # Or manually:
   javac -d out -cp "lib/*" src/gym_system/main/Main.java src/gym_system/repository/*.java
   java -cp "out;lib/*" gym_system.main.Main
   ```

## 📱 Usage

### 🆕 New Member Journey
1. **Sign Up Process** 📝
   - Page 1: Personal & Contact Details (name, phone, email, address)
   - Page 2: Health & Medical Info (blood group, height, weight, BMI auto-calc, goals)
   - Page 3: Membership Plan Selection (plan type, additional services)
   - System generates Member ID + PIN

2. **Initial Deposit** 💰
   - New members must add funds to activate their account
   - Wallet balance enables class bookings and services

### 🔄 Existing Member Journey
1. **Login** 🔐
   - Enter Member ID + PIN
   - Successful login → Dashboard

2. **Dashboard Operations** ⚙️
   - **💰 Add Funds**: Top-up wallet (₹100 - ₹50,000)
   - **💸 Request Refund**: Withdraw funds (with balance check)
   - **⚡ Quick Book Class**: Instant class booking (6 preset options)
   - **📋 Payment History**: View all transactions with dates
   - **🔑 Change Password**: Update PIN (with transaction safety)
   - **👛 Wallet Balance**: Check current balance with breakdown
   - **🚪 Exit**: Close application

## 🛠️ Technologies Used

- **Programming Language**: ☕ Java (OpenJDK 21 recommended)
- **GUI Framework**: 🖥️ Java Swing
- **Database**: 🗄️ MySQL 8.0+
- **JDBC**: MySQL Connector/J 9.3.0
- **Date Picker**: JCalendar 1.4
- **IDE**: 💻 IntelliJ IDEA

## 🔒 Security Improvements (vs Original Banking System)

✅ **SQL Injection Prevention**: All queries use `PreparedStatement`  
✅ **Input Validation**: Phone numbers, email, amounts, PIN format  
✅ **Balance Validation**: Refunds and bookings check sufficient funds  
✅ **Transaction Management**: PIN updates use commit/rollback  
✅ **Error Handling**: Proper try-catch with user-friendly messages  
✅ **Field Validation**: Required fields checked before database insertion  

## 📁 Project Structure

```
Banking_System/
├── docs/                           # Documentation & audits
│   ├── 01-prd.md                   # Product Requirements Document
│   ├── 02-tech-stack-mapping.md    # Banking → Gym migration guide
│   ├── 03-test-cases.md            # TDD test cases (50 tests)
│   └── 04-e2e-test-report.md       # E2E test results
├── src/
│   ├── gym_system/                 # NEW: Gym management code
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
│   └── banking_system/             # ORIGINAL: Banking system (keep for reference)
├── icons/
│   ├── gym_logo.png                # Login screen logo
│   └── gym_background.jpg          # Dashboard background
├── lib/
│   ├── mysql-connector-j-9.3.0.jar
│   └── jcalendar-1.4.jar
├── database_schema.sql             # MySQL schema + sample data
├── README.md                       # This file
└── docs/                           # Audit reports
```

## 🎯 Demo Script (For College Presentation)

1. **Show Login screen** → Explain UI and theme
2. **Register new member** → Complete 3-page signup with BMI calc
3. **Show generated credentials** → Member ID + PIN
4. **Make initial payment** → Add ₹10,000 to wallet
5. **Login with new member** → Show dashboard
6. **Quick book classes** → Book Yoga + HIIT (show balance deduction)
7. **View payment history** → Show all transactions
8. **Check wallet balance** → Show calculation (₹10,000 - ₹300 - ₹500 = ₹9,200)
9. **Change password** → Show PIN update with transaction safety
10. **Request refund** → Show balance validation

## 📊 Key Features Highlighted in Demo

| Feature | What It Shows |
|---------|--------------|
| **BMI Auto-Calculation** | Real-time calculation from height/weight |
| **Wallet System** | Credit/Debit tracking like digital wallet |
| **Balance Validation** | Prevents overdrafts in bookings/refunds |
| **Quick Booking** | Preset class options with instant confirmation |
| **Payment History** | Complete audit trail with color-coded transactions |
| **Transaction Safety** | PreparedStatement, commit/rollback for PIN updates |
| **Input Validation** | Phone, email, amount, PIN format checks |

## 🐛 Known Differences from Banking System

| Banking System | Gym System | Improvement |
|---------------|-----------|-------------|
| `Statement` (SQL injection risk) | `PreparedStatement` | ✅ Secure |
| No input validation | Full validation | ✅ Robust |
| Withdrawal without balance check | Refund with balance check | ✅ Safe |
| PIN update without transaction | PIN update with commit/rollback | ✅ Consistent |
| Services bug (else-if chain) | Independent if statements | ✅ All services captured |
| BalanceEnquiry title = "PIN CHANGE" | WalletBalance title = "WALLET BALANCE" | ✅ Fixed bug |

---

**⭐ Don't forget to star this repository if you found it helpful!**

**💪 Happy Fitness Journeying! 🏋️‍♂️✨**
