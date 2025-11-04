package controller;

import java.sql.*;
import model.Student;

public class StudentController {
    private static final String URL = "jdbc:mysql://localhost:3306/schooldb";
    private static final String USER = "root";
    private static final String PASS = "yourpassword";

    public void addStudent(Student s) {
        String sql = "INSERT INTO Student VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getStudentID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDepartment());
            ps.setDouble(4, s.getMarks());
            ps.executeUpdate();
            System.out.println("✅ Student added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewStudents() {
        String sql = "SELECT * FROM Student";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nID\tName\tDept\tMarks");
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t%.2f\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(int id, double newMarks) {
        String sql = "UPDATE Student SET Marks=? WHERE StudentID=?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, newMarks);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Updated!" : "⚠️ Student not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Deleted!" : "⚠️ Student not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
