# ============================================
# FitZone Gym System - PowerShell Setup Script
# Run this AFTER installing XAMPP
# ============================================

Write-Host "============================================" -ForegroundColor Cyan
Write-Host "  FitZone Gym System - Automated Setup" -ForegroundColor Cyan
Write-Host "============================================" -ForegroundColor Cyan
Write-Host ""

# Step 1: Check XAMPP
Write-Host "[1/6] Checking XAMPP installation..." -ForegroundColor Yellow
$xamppMySQL = "C:\xampp\mysql\bin\mysql.exe"
if (Test-Path $xamppMySQL) {
    Write-Host "  OK: XAMPP MySQL found" -ForegroundColor Green
    & $xamppMySQL --version
} else {
    Write-Host "  ERROR: XAMPP MySQL not found!" -ForegroundColor Red
    Write-Host ""
    Write-Host "Please install XAMPP first:" -ForegroundColor Yellow
    Write-Host "  1. Download: https://www.apachefriends.org/download.html" -ForegroundColor White
    Write-Host "  2. Install with default settings" -ForegroundColor White
    Write-Host "  3. Open XAMPP Control Panel and START MySQL" -ForegroundColor White
    Write-Host "  4. Re-run this script" -ForegroundColor White
    Write-Host ""
    Read-Host "Press Enter to exit"
    exit 1
}

# Step 2: Check MySQL Service
Write-Host ""
Write-Host "[2/6] Checking MySQL service status..." -ForegroundColor Yellow
$xamppService = Get-Service -Name "mysql*" -ErrorAction SilentlyContinue
if ($xamppService -and $xamppService.Status -eq "Running") {
    Write-Host "  OK: MySQL is RUNNING" -ForegroundColor Green
} else {
    Write-Host "  WARNING: MySQL is not running!" -ForegroundColor Red
    Write-Host "  Please open XAMPP Control Panel and click START next to MySQL" -ForegroundColor Yellow
    Write-Host ""
    $continue = Read-Host "If MySQL is now running, type 'y' to continue (or 'n' to exit)"
    if ($continue -ne "y") { exit 1 }
}

# Step 3: Create Database
Write-Host ""
Write-Host "[3/6] Creating gym_management_system database..." -ForegroundColor Yellow
$dbSqlPath = Join-Path $PSScriptRoot "database_schema.sql"
if (Test-Path $dbSqlPath) {
    $proc = Start-Process -FilePath $xamppMySQL -ArgumentList "-u root -e `'source `"$dbSqlPath`"`'" -NoNewWindow -Wait -PassThru
    if ($proc.ExitCode -eq 0) {
        Write-Host "  OK: Database created successfully" -ForegroundColor Green
    } else {
        Write-Host "  Trying alternative method..." -ForegroundColor Yellow
        # Try without source command
        $tempSql = @"
CREATE DATABASE IF NOT EXISTS gym_management_system;
USE gym_management_system;
"@
        $tempFile = Join-Path $env:TEMP "temp_create_db.sql"
        Set-Content -Path $tempFile -Value $tempSql
        Start-Process -FilePath $xamppMySQL -ArgumentList "-u root < `"$tempFile`"" -NoNewWindow -Wait
        Remove-Item $tempFile -ErrorAction SilentlyContinue
        Write-Host "  OK: Database creation attempted" -ForegroundColor Green
    }
} else {
    Write-Host "  WARNING: database_schema.sql not found at: $dbSqlPath" -ForegroundColor Red
}

# Verify database
Write-Host "  Verifying database..." -ForegroundColor Yellow
$verifyQuery = '"USE gym_management_system; SHOW TABLES;"'
$verifyProc = Start-Process -FilePath $xamppMySQL -ArgumentList "-u root -e $verifyQuery" -NoNewWindow -Wait -PassThru -RedirectStandardOutput (Join-Path $env:TEMP "mysql_output.txt")
if (Test-Path (Join-Path $env:TEMP "mysql_output.txt")) {
    $tables = Get-Content (Join-Path $env:TEMP "mysql_output.txt")
    Write-Host "  Tables found: $tables" -ForegroundColor Green
    Remove-Item (Join-Path $env:TEMP "mysql_output.txt") -ErrorAction SilentlyContinue
}

# Step 4: Check Java
Write-Host ""
Write-Host "[4/6] Checking Java installation..." -ForegroundColor Yellow
$javaVersion = java -version 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "  OK: $javaVersion" -ForegroundColor Green
} else {
    Write-Host "  ERROR: Java not found!" -ForegroundColor Red
    exit 1
}

# Step 5: Check Libraries
Write-Host ""
Write-Host "[5/6] Checking library files..." -ForegroundColor Yellow
$libPath = Join-Path $PSScriptRoot "lib"
if (-not (Test-Path $libPath)) {
    New-Item -ItemType Directory -Path $libPath -Force | Out-Null
    Write-Host "  Created: lib\ folder" -ForegroundColor Yellow
}

$mysqlJar = Get-ChildItem -Path $libPath -Filter "mysql-connector*.jar" -ErrorAction SilentlyContinue
$jcalJar = Get-ChildItem -Path $libPath -Filter "jcalendar*.jar" -ErrorAction SilentlyContinue

if ($mysqlJar) {
    Write-Host "  OK: $($mysqlJar.Name) found" -ForegroundColor Green
} else {
    Write-Host "  MISSING: MySQL Connector JAR" -ForegroundColor Red
    Write-Host "    Download from: https://dev.mysql.com/downloads/connector/j/" -ForegroundColor Yellow
    Write-Host "    Place in: $libPath\" -ForegroundColor Yellow
}

if ($jcalJar) {
    Write-Host "  OK: $($jcalJar.Name) found" -ForegroundColor Green
} else {
    Write-Host "  MISSING: JCalendar 1.4 JAR" -ForegroundColor Red
    Write-Host "    Search Google: 'jcalendar-1.4.jar download'" -ForegroundColor Yellow
    Write-Host "    Place in: $libPath\" -ForegroundColor Yellow
}

# Step 6: Set Environment Variables
Write-Host ""
Write-Host "[6/6] Setting environment variables..." -ForegroundColor Yellow

# For current session
$env:DB_URL = "jdbc:mysql://localhost:3306/gym_management_system"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = ""

# For future sessions (User level)
[System.Environment]::SetEnvironmentVariable("DB_URL", "jdbc:mysql://localhost:3306/gym_management_system", "User")
[System.Environment]::SetEnvironmentVariable("DB_USERNAME", "root", "User")
[System.Environment]::SetEnvironmentVariable("DB_PASSWORD", "", "User")

Write-Host "  OK: Environment variables set" -ForegroundColor Green
Write-Host "    DB_URL = jdbc:mysql://localhost:3306/gym_management_system" -ForegroundColor Gray
Write-Host "    DB_USERNAME = root" -ForegroundColor Gray
Write-Host "    DB_PASSWORD = (empty)" -ForegroundColor Gray

# Summary
Write-Host ""
Write-Host "============================================" -ForegroundColor Cyan
Write-Host "  Setup Summary" -ForegroundColor Cyan
Write-Host "============================================" -ForegroundColor Cyan

$allReady = $true
if (Test-Path $xamppMySQL) { Write-Host "  [OK] XAMPP MySQL" -ForegroundColor Green } else { $allReady = false; Write-Host "  [X] XAMPP MySQL - NOT INSTALLED" -ForegroundColor Red }
if ($javaVersion) { Write-Host "  [OK] Java" -ForegroundColor Green } else { $allReady = false; Write-Host "  [X] Java - NOT INSTALLED" -ForegroundColor Red }
if ($mysqlJar) { Write-Host "  [OK] MySQL Connector JAR" -ForegroundColor Green } else { $allReady = false; Write-Host "  [X] MySQL Connector JAR - MISSING" -ForegroundColor Red }
if ($jcalJar) { Write-Host "  [OK] JCalendar JAR" -ForegroundColor Green } else { $allReady = false; Write-Host "  [X] JCalendar JAR - MISSING" -ForegroundColor Red }

Write-Host ""
if ($allReady) {
    Write-Host "  Everything is ready! Next steps:" -ForegroundColor Green
    Write-Host ""
    Write-Host "  1. Open IntelliJ IDEA" -ForegroundColor White
    Write-Host "  2. Open project: D:\Coding\Banking_System" -ForegroundColor White
    Write-Host "  3. File > Project Structure > Libraries > +" -ForegroundColor White
    Write-Host "     Add all .jar files from lib\ folder" -ForegroundColor White
    Write-Host "  4. Right-click src > Mark Directory as > Sources Root" -ForegroundColor White
    Write-Host "  5. Run: src/gym_system/main/Main.java" -ForegroundColor White
} else {
    Write-Host "  Some items need attention. Fix red items above, then re-run this script." -ForegroundColor Yellow
}

Write-Host ""
Read-Host "Press Enter to exit"
