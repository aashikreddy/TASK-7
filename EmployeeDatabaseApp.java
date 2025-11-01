import java.sql.*;
import java.util.*;

public class EmployeeDatabaseApp {
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root"; //your MySQL username
    private static final String PASSWORD = "901050"; //your MySQL password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ Connected to the database successfully!");
            int choice;

            do {
                System.out.println("\n=== Employee Database Menu ===");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addEmployee(conn, sc);
                        break;
                    case 2:
                        viewEmployees(conn);
                        break;
                    case 3:
                        updateEmployee(conn, sc);
                        break;
                    case 4:
                        deleteEmployee(conn, sc);
                        break;
                    case 5:
                        System.out.println("👋 Exiting...");
                        break;
                    default:
                        System.out.println("❌ Invalid choice. Try again!");
                }
            } while (choice != 5);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        sc.close();
    }

    // ➕ ADD EMPLOYEE
    private static void addEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        sc.nextLine(); // consume newline
        String name = sc.nextLine();
        System.out.print("Enter department: ");
        String dept = sc.nextLine();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        String query = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, dept);
            pstmt.setDouble(3, salary);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " employee(s) added successfully!");
        }
    }

    // 📄 VIEW EMPLOYEES
    private static void viewEmployees(Connection conn) throws SQLException {
        String query = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\n--- Employee List ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Dept: %s | Salary: %.2f%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("department"), rs.getDouble("salary"));
            }
        }
    }

    // ✏️ UPDATE EMPLOYEE
    private static void updateEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new department: ");
        String newDept = sc.nextLine();
        System.out.print("Enter new salary: ");
        double newSalary = sc.nextDouble();

        String query = "UPDATE employees SET department = ?, salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newDept);
            pstmt.setDouble(2, newSalary);
            pstmt.setInt(3, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " employee(s) updated successfully!");
        }
    }

    // ❌ DELETE EMPLOYEE
    private static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " employee(s) deleted successfully!");
        }
    }
}
