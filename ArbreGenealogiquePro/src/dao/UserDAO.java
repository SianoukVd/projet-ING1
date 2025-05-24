/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.User;

import java.sql.*;

public class UserDAO {

    // Insert a new user into the database
    public static boolean registerUser(User user) {
        String sql = "INSERT INTO users (ssn, first_name, last_name, birth_date, nationality, password, public_code, private_code, is_verified) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getSsn());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getBirthDate());
            stmt.setString(5, user.getNationality());
            stmt.setString(6, user.getPassword());
            stmt.setString(7, user.getPublicCode());
            stmt.setString(8, user.getPrivateCode());
            stmt.setBoolean(9, user.isVerified());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error registering user: " + e.getMessage());
            return false;
        }
    }

    // Authenticate user using private code and password
    public static User loginUser(String privateCode, String password) {
        String sql = "SELECT * FROM users WHERE private_code = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, privateCode);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("ssn"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("birth_date"),
                    rs.getString("nationality"),
                    rs.getString("password"),
                    rs.getString("public_code"),
                    rs.getString("private_code"),
                    rs.getBoolean("is_verified")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error logging in: " + e.getMessage());
        }

        return null;
    }
}

