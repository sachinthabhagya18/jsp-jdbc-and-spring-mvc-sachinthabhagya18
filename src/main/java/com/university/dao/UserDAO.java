package com.university.dao;

import com.university.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    static final String DB_URL = "jdbc:mysql://localhost:3306/university_db";
    static final String USER = "root"; 
    static final String PASS = "Kom2254772@sltbb"; 

    // 1. Validate user credentials
    public static boolean validate(String email, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM students WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Get all courses
    public static List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM courses";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Course c = new Course(
                    rs.getInt("course_id"),
                    rs.getString("name"),
                    rs.getString("instructor"),
                    rs.getInt("credits")
                );
                courses.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    // 3. Register course
    public static void registerCourse(String email, int courseId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Get student_id from email
            String getStudent = "SELECT student_id FROM students WHERE email = ?";
            PreparedStatement ps1 = conn.prepareStatement(getStudent);
            ps1.setString(1, email);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                int studentId = rs.getInt("student_id");

                // Check if already registered
                String check = "SELECT * FROM registrations WHERE student_id = ? AND course_id = ?";
                PreparedStatement psCheck = conn.prepareStatement(check);
                psCheck.setInt(1, studentId);
                psCheck.setInt(2, courseId);
                ResultSet checkRs = psCheck.executeQuery();
                if (checkRs.next()) {
                    return; // Already registered
                }

                // Register
                String sql = "INSERT INTO registrations(student_id, course_id, date) VALUES (?, ?, NOW())";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, studentId);
                stmt.setInt(2, courseId);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
