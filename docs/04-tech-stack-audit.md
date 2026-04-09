# Tech Stack Audit Report

## JDK (Java Development Kit)

| Attribute | Current | Recommended |
|-----------|---------|-------------|
| **Version** | OpenJDK 24 | OpenJDK 21 LTS |
| **Release Date** | March 18, 2025 | September 2023 |
| **Support End** | September 2025 (non-LTS) | September 2031 (LTS) |
| **Stability** | ⚠️ Bleeding edge | ✅ Production proven |
| **Vendor Support** | Limited | All major vendors |

### Issues with JDK 24
- Non-LTS release (short support window)
- Contains preview/experimental features
- Minimal enterprise adoption
- May have undiscovered bugs
- JEP 503 removed 32-bit x86 port (compatibility concern for older systems)

### Recommendation
Downgrade to **JDK 21 LTS** for:
- Long-term support (until 2031)
- Enterprise vendor backing
- Proven stability in production
- Wider community testing

---

## MySQL Connector/J

| Attribute | Current Status |
|-----------|---------------|
| **Version** | 9.3.0 (April 15, 2025) |
| **Status** | ✅ Latest GA release |
| **Compatibility** | MySQL 8.0+ |
| **Driver Class** | `com.mysql.cj.jdbc.Driver` (correct) |

### Issues
1. **Hardcoded absolute path** in IntelliJ library config:
   ```
   $PROJECT_DIR$/../../../Default/mysql-connector-j-9.3.0
   ```
   - Not portable across machines
   - Breaks for other developers

2. **No local JAR in project**
   - No `lib/` folder exists
   - Build depends on external file system location

3. **No version pinning**
   - No Maven/Gradle dependency management
   - Manual updates required

### Recommendation
Add to Maven `pom.xml`:
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.3.0</version>
</dependency>
```

---

## JCalendar

| Attribute | Current Status |
|-----------|---------------|
| **Version** | 1.4 |
| **Last Updated** | 2008 (17+ years ago) |
| **Maintenance** | ❌ Abandoned |
| **Maven** | `com.toedter:jcalendar:1.4` |
| **Usage** | `JDateChooser` in `SignUp.java` for DOB selection |

### Issues
1. **No Java 9+ module support**
2. **No security audits** for over a decade
3. **Swing-based** (legacy UI framework)
4. **Potential compatibility issues** with modern JDK
5. **Hardcoded user-specific path:**
   ```
   $USER_HOME$/Riddhi/lib/jcalendar-1.4.jar
   ```

### Recommended Replacements

| Library | Maven Coordinate | Status |
|---------|-----------------|--------|
| **LGoodDatePicker** | `com.github.lgooddatepicker:LGoodDatePicker:11.2.1` | ✅ Actively maintained |
| **SwingX JXDatePicker** | `org.swinglabs.swingx:swingx-all:1.6.5-1` | ⚠️ Less active |
| **DatePicker** | `org.tuxdevelop:spring-boot-lite-jpa-demo:0.1` | ✅ Modern |

### Migration Example
```java
// Old (JCalendar)
JDateChooser dateChooser = new JDateChooser();
String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();

// New (LGoodDatePicker)
DatePicker datePicker = new DatePicker();
LocalDate dob = datePicker.getDate();
```

---

## Build System

| Aspect | Current State |
|--------|--------------|
| **Maven** | ❌ Not present |
| **Gradle** | ❌ Not present |
| **Dependency Management** | ❌ Manual JAR references |
| **Reproducibility** | ❌ Poor |

### Critical Issues
1. No automated build process
2. Dependencies referenced via absolute machine-specific paths
3. No CI/CD integration possible
4. No dependency version management
5. Cannot build on another machine without manual setup
6. No test execution framework

### Recommendation: Add Maven

**`pom.xml` structure:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.banking</groupId>
    <artifactId>banking-system</artifactId>
    <version>1.0.0</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.3.0</version>
        </dependency>
        <dependency>
            <groupId>com.github.lgooddatepicker</groupId>
            <artifactId>LGoodDatePicker</artifactId>
            <version>11.2.1</version>
        </dependency>
    </dependencies>
</project>
```

---

## GUI Framework (Java Swing)

| Aspect | Assessment |
|--------|-----------|
| **Framework** | Java Swing (AWT) |
| **Status** | Legacy (effectively deprecated since Java 8, 2014) |
| **Layout** | ❌ `null` layout (absolute positioning) |
| **Portability** | ❌ Poor (breaks on different resolutions/DPI) |

### Issues Found

1. **No layout manager** - Every component uses `setBounds(x, y, width, height)`
   ```java
   label.setBounds(50, 40, 100, 100);
   button.setBounds(80, 292, 150, 30);
   ```

2. **Fixed frame sizes**
   ```java
   setSize(700, 400);    // Login
   setSize(850, 750);    // SignUp
   setSize(780, 737);    // Transaction screens
   ```

3. **No DPI awareness** - Will look broken on 4K/HiDPI displays

4. **Hardcoded fonts** - Not all systems have "Arial", "Railway", "Osward"
   ```java
   text.setFont(new Font("Osward", Font.BOLD, 38));
   ```

5. **Undecorated frames** - `setUndecorated(true)` removes window controls

### Recommended Layout Managers
| Scenario | Layout Manager |
|----------|---------------|
| Simple vertical forms | `BoxLayout` (Y_AXIS) |
| Grid of buttons | `GridLayout` |
| Complex forms | `GridBagLayout` |
| Modern approach | `MigLayout` (third-party) |

### Long-term Recommendation
Migrate to **JavaFX** or **web-based UI** (React/Spring Boot)

---

## Dependency Summary

| Dependency | Version | Status | Action | Priority |
|------------|---------|--------|--------|----------|
| OpenJDK | 24 | ⚠️ Non-LTS | Downgrade to 21 LTS | 🟡 High |
| MySQL Connector/J | 9.3.0 | ✅ Latest | Fix hardcoded path, add to Maven | 🟡 High |
| JCalendar | 1.4 (2008) | ❌ Abandoned | Replace with LGoodDatePicker | 🟡 High |
| Java Swing | Legacy | ⚠️ Deprecated | Migrate to JavaFX (long-term) | 🟢 Low |
| Build System | None | ❌ Missing | Add Maven/Gradle | 🔴 Critical |

---

## IntelliJ IDEA Configuration Issues

### `.idea/libraries/mysql_connector_j_9_3.xml`
```xml
<root url="file://$PROJECT_DIR$/../../../Default/mysql-connector-j-9.3.0" />
```
**Problem:** Machine-specific absolute path

### `.idea/libraries/jcalendar_1_4.xml`
```xml
<root url="jar://$USER_HOME$/Riddhi/lib/jcalendar-1.4.jar!/" />
```
**Problem:** User-specific hardcoded path

### `misc.xml`
```xml
<component name="ProjectRootManager" version="2" languageLevel="JDK_24" 
           default="true" project-jdk-name="openjdk-24" project-jdk-type="JavaSDK">
```
**Problem:** Points to non-LTS JDK
