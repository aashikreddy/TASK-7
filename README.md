# 🧠 Java JDBC Employee Database App

## 🎯 Objective
Build a **Java console-based Employee Management App** using **JDBC** to perform **CRUD operations** on a **MySQL database**.

---

## ⚙️ Features
- ➕ Add Employee  
- 📄 View All Employees  
- ✏️ Update Employee  
- ❌ Delete Employee  
- Uses **PreparedStatement** to prevent SQL Injection  
- Handles **SQL exceptions** and closes connections safely  

---

## 🧩 Technologies Used
- Java (Core)
- JDBC
- MySQL
- Scanner for user input

---

## 🗄️ Database Setup

1. Open MySQL and run the following commands:

```sql
CREATE DATABASE employee_db;
USE employee_db;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    department VARCHAR(50),
    salary DOUBLE
);
```

2. Update the credentials in `EmployeeDatabaseApp.java`:
```java
private static final String USER = "root";      // your MySQL username
private static final String PASSWORD = "root";  // your MySQL password
```

---

## ▶️ How to Run

1. Download and extract the MySQL Connector/J driver:  
   👉 https://dev.mysql.com/downloads/connector/j/

2. Copy the `.jar` file (e.g., `mysql-connector-j-9.5.0.jar`) into your project folder.

3. Compile and run using:

```bash
javac -cp ".;mysql-connector-j-9.5.0.jar" EmployeeDatabaseApp.java
java -cp ".;mysql-connector-j-9.5.0.jar" EmployeeDatabaseApp
```

4. You’ll see a menu like this:

```
✅ Connected to the database successfully!
=== Employee Database Menu ===
1. Add Employee
2. View All Employees
3. Update Employee
4. Delete Employee
5. Exit
```

## 👨‍💻 Author
**Aashik Reddy**  
📧 aashikreddythatiparthi03@gmail.com  

---

## 🪪 License
This project is licensed under the MIT License — see the `LICENSE` file for details.
