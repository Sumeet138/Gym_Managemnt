# 📋 FITZONE SETUP CHECKLIST

Follow these steps IN ORDER. Check off each item as you complete it.

---

## STEP 1: Install XAMPP ⏱️ ~10 minutes

- [ ] Open browser
- [ ] Go to: https://www.apachefriends.org/download.html
- [ ] Click "Download" button
- [ ] Run the installer file
- [ ] Click "Next" through all default options
- [ ] Wait for installation to finish
- [ ] Open **XAMPP Control Panel** (should launch automatically)
- [ ] Click **"Start"** button next to **MySQL** (should turn GREEN)
- [ ] Verify: See "Running" status next to MySQL

**✅ XAMPP MySQL is installed and running**

---

## STEP 2: Download JAR Files ⏱️ ~5 minutes

### MySQL Connector/J
- [ ] Open browser
- [ ] Go to: https://dev.mysql.com/downloads/connector/j/
- [ ] Select Operating System: **"Platform Independent"**
- [ ] Click **"Download"** next to ZIP file
- [ ] Skip login → Click "No thanks, just start my download"
- [ ] Extract the ZIP file
- [ ] Find the `.jar` file inside (named like: `mysql-connector-j-9.3.0.jar`)
- [ ] **Copy it to:** `D:\Coding\Banking_System\lib\`

### JCalendar 1.4
- [ ] Open browser
- [ ] Go to: https://mvnrepository.com/artifact/com.toedter/jcalendar/1.4
- [ ] Click **"Download (590 KB)"** or **"Jar"** link
- [ ] Save the `.jar` file
- [ ] **Move it to:** `D:\Coding\Banking_System\lib\`

**✅ Both JARs are in the `lib\` folder**

---

## STEP 3: Run Automated Setup ⏱️ ~2 minutes

- [ ] Open **PowerShell** (search in Windows Start)
- [ ] Type: `cd D:\Coding\Banking_System`
- [ ] Type: `.\setup-automated.ps1`
- [ ] Press **Enter**
- [ ] If asked about execution policy, type: `Y`
- [ ] Read the output - it will tell you what's ready and what's missing
- [ ] If it says **"Everything is ready!"** → move to Step 4
- [ ] If it shows red items → fix them, then re-run the script

**✅ Setup script says everything is ready**

---

## STEP 4: Open in IntelliJ ⏱️ ~3 minutes

- [ ] Open **IntelliJ IDEA**
- [ ] Click **"Open"**
- [ ] Navigate to: `D:\Coding\Banking_System`
- [ ] Click **OK**
- [ ] Wait for project to load

### Add Libraries
- [ ] Press: `Ctrl + Alt + Shift + S` (opens Project Structure)
- [ ] Click **"Libraries"** on left side
- [ ] Click **"+"** button at top
- [ ] Select **"Java"**
- [ ] Navigate to: `D:\Coding\Banking_System\lib\`
- [ ] Select **BOTH .jar files** (hold Ctrl to select multiple)
- [ ] Click **OK**
- [ ] Click **Apply** then **OK**

### Mark Source Root
- [ ] In project panel (left side), find `src` folder
- [ ] **Right-click** on `src`
- [ ] Select: **"Mark Directory as"** → **"Sources Root"**
- [ ] The `src` folder should turn BLUE

**✅ IntelliJ is configured**

---

## STEP 5: Run the Application ⏱️ ~1 minute

- [ ] In project panel, navigate to:
  `src` → `gym_system` → `main` → `Main.java`
- [ ] **Double-click** to open `Main.java`
- [ ] **Right-click** anywhere in the code
- [ ] Select: **"Run 'Main.main()'"**
- [ ] Wait a few seconds
- [ ] **A window should appear!** ← This is the FitZone Login screen

**✅ The application is running!**

---

## STEP 6: Test Basic Functionality ⏱️ ~5 minutes

### Test Registration
- [ ] Click **"NEW MEMBER"** button
- [ ] Fill in Page 1 with test data:
  ```
  Name: Test User
  Phone: 9876543210
  DOB: Pick any date (use calendar widget)
  Gender: Male
  Email: test@test.com
  Emergency Contact: 9876543211
  Address: 123 Test Street
  City: Mumbai
  State: Maharashtra
  Pincode: 400001
  ```
- [ ] Click **"Next"**
- [ ] Fill in Page 2:
  ```
  Blood Group: O+
  Height: 175
  Weight: 70
  (BMI should auto-show 22.86)
  Fitness Goal: Weight Loss
  Experience: Beginner
  Preferred Time: Morning (6-10 AM)
  ```
- [ ] Click **"Next"**
- [ ] Page 3: Select **Quarterly** plan
- [ ] Check **"Personal Trainer"** service
- [ ] Check the **declaration** checkbox
- [ ] Click **"Submit"**
- [ ] **Note down:** Member ID and PIN from popup
- [ ] Add Funds screen should appear
- [ ] Enter: `10000`
- [ ] Click **"Add Funds"**
- [ ] Dashboard should appear! 🎉

**✅ Registration flow works**

### Test Login
- [ ] Close the application
- [ ] Run `Main.java` again
- [ ] Enter the Member ID and PIN you noted
- [ ] Click **"LOGIN"**
- [ ] Dashboard should appear! 🎉

**✅ Login works**

---

## FINAL CHECKLIST

- [ ] Application starts without errors
- [ ] Login screen appears
- [ ] New member registration works
- [ ] Login with new member works
- [ ] Dashboard loads
- [ ] Add Funds works
- [ ] Wallet Balance shows correct amount
- [ ] No crash errors in console

---

## IF SOMETHING GOES WRONG

### "Database connection failed"
→ Make sure MySQL is **RUNNING** in XAMPP (green status)

### "ClassNotFoundException"
→ JAR files not added to IntelliJ libraries properly

### "Table doesn't exist"
→ Run: `.\setup-automated.ps1` in PowerShell

### Application won't start
→ Check IntelliJ console for red error messages

---

**Need help?** Show the error message to your instructor or check `docs/` folder for troubleshooting guides.

---

**🎉 Once all checkboxes above are ticked → You're ready for your demo!**
