@echo off
echo ============================================
echo   FitZone Gym System - Setup Script
echo ============================================
echo.

echo [Step 1/5] Checking Java installation...
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java not found!
    echo Please install JDK from: https://adoptium.net/
    pause
    exit /b 1
)
echo OK: Java is installed
for /f "tokens=3" %%v in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    echo     Version: %%v
)
echo.

echo [Step 2/5] Checking MySQL installation...
where mysql >nul 2>&1
if not errorlevel 1 (
    echo OK: MySQL command found
) else (
    echo WARNING: MySQL not found in PATH
    echo.
    echo Please do ONE of the following:
    echo.
    echo Option A - Install MySQL Server ^(Recommended^):
    echo   1. Download: https://dev.mysql.com/downloads/installer/
    echo   2. Install MySQL Server 8.0+
    echo   3. Remember your root password!
    echo.
    echo Option B - Use XAMPP ^(Easier^):
    echo   1. Download: https://www.apachefriends.org/download.html
    echo   2. Install XAMPP ^(includes MySQL^)
    echo   3. Start MySQL from XAMPP Control Panel
    echo.
    echo Option C - Already installed but not in PATH:
    echo   Add MySQL to your system PATH variable
    echo   Common paths:
    echo     C:\Program Files\MySQL\MySQL Server 8.0\bin
    echo     C:\xampp\mysql\bin
    echo.
    pause
    exit /b 1
)
echo.

echo [Step 3/5] Downloading required libraries...
echo.
echo Please download these files manually ^(script cannot auto-download^):
echo.
echo 1. MySQL Connector/J:
echo    - Go to: https://dev.mysql.com/downloads/connector/j/
echo    - Select: Platform Independent
echo    - Download: mysql-connector-j-9.3.0.zip
echo    - Extract the .jar file to: D:\Coding\Banking_System\lib\
echo.
echo 2. JCalendar 1.4:
echo    - Search Google for: "jcalendar-1.4.jar download"
echo    - Or get from Maven: com.toedter:jcalendar:1.4
echo    - Place the .jar file in: D:\Coding\Banking_System\lib\
echo.
pause

echo [Step 4/5] Creating lib directory...
if not exist "lib" mkdir lib
echo OK: lib\ directory ready
echo.

echo [Step 5/5] Setting environment variables for this session...
echo.
set /p DB_URL_INPUT="Enter MySQL connection URL (e.g., jdbc:mysql://localhost:3306/gym_management_system): "
set DB_URL=%DB_URL_INPUT%

set /p DB_USER_INPUT="Enter MySQL username (usually 'root'): "
set DB_USERNAME=%DB_USER_INPUT%

set /p DB_PASS_INPUT="Enter MySQL password: "
set DB_PASSWORD=%DB_PASS_INPUT%

echo.
echo Environment variables set for this session!
echo.

echo ============================================
echo   Setup Complete! Next Steps:
echo ============================================
echo.
echo 1. Make sure MySQL is RUNNING
echo 2. Create the database:
echo    mysql -u %DB_USERNAME% -p ^< database_schema.sql
echo.
echo 3. Open project in IntelliJ IDEA
echo 4. Add these libraries in IntelliJ:
echo    - File ^> Project Structure ^> Libraries ^>+
echo    - Select all .jar files from lib\ folder
echo 5. Right-click src ^> Mark Directory as ^> Sources Root
echo 6. Run: src/gym_system/main/Main.java
echo.
echo For persistent environment variables ^(after reboot^):
echo   - Search "Environment Variables" in Windows Start
echo   - Add DB_URL, DB_USERNAME, DB_PASSWORD as User variables
echo.
pause
