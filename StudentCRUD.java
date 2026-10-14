
import java.sql.*;
import java.util.Scanner;

public class StudentCRUD {

    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";
    static final String PASSWORD = "Ycce*2024";

    static Connection con;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("MySQL Connected Successfully!");

            int choice;

            do {
                System.out.println("\n----- STUDENT CRUD MENU -----");
                System.out.println("1. Insert Student");
                System.out.println("2. Display Student");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        insertStudent();
                        break;
                    case 2:
                        displayStudent();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }

            } while (choice != 5);

            con.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // CREATE: Insert a student
    static void insertStudent() throws SQLException {
        System.out.print("Enter Student Roll No: ");
        int roll = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Age: ");
        int age = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO student VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setInt(3, age);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Student inserted successfully!");
        }
    }

    // READ: Display all students
    static void displayStudent() throws SQLException {
        String sql = "SELECT * FROM student";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nRoll No\tName\t\tAge");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("roll_no") + "\t" +
                    rs.getString("name") + "\t\t" +
                    rs.getInt("age")
                );
            }
        }
    }

    // UPDATE: Update student details
    static void updateStudent() throws SQLException {
        System.out.print("Enter Roll No to update: ");
        int roll = Integer.parseInt(sc.nextLine());

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Age: ");
        int age = Integer.parseInt(sc.nextLine());

        String sql =
            "UPDATE student SET name = ?, age = ? WHERE roll_no = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, roll);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student updated successfully!");
            else
                System.out.println("Student not found!");
        }
    }

    // DELETE: Delete a student
    static void deleteStudent() throws SQLException {
        System.out.print("Enter Roll No to delete: ");
        int roll = Integer.parseInt(sc.nextLine());

        String sql = "DELETE FROM student WHERE roll_no = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roll);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student deleted successfully!");
            else
                System.out.println("Student not found!");
        }
    }
}
