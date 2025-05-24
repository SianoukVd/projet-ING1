/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    
    public static Integer findUserIdBySSN(String ssn) {
    String sql = "SELECT id FROM users WHERE ssn = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, ssn);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }

    } catch (SQLException e) {
        System.out.println("❌ Error finding user by SSN: " + e.getMessage());
    }

    return null;
}
    
    
    // Delete a person from the database by ID
public static boolean deletePerson(int personId) {
    String sql = "DELETE FROM persons WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, personId);
        int rows = stmt.executeUpdate();
        return rows > 0;

    } catch (SQLException e) {
        System.out.println("❌ Error deleting person: " + e.getMessage());
        return false;
    }
}
    
    // Update an existing person in the database
public static boolean updatePerson(Person person) {
    String sql = "UPDATE persons SET name = ?, birth_date = ?, relation = ?, visibility = ? WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, person.getName());
        stmt.setString(2, person.getBirthDate());
        stmt.setString(3, person.getRelation());
        stmt.setString(4, person.getVisibility());
        stmt.setInt(5, person.getId());

        int rows = stmt.executeUpdate();
        return rows > 0;

    } catch (SQLException e) {
        System.out.println("❌ Error updating person: " + e.getMessage());
        return false;
    }
}
    
    
    // Add a new person to the user's tree
    public static boolean addPerson(Person person) {
        String sql = "INSERT INTO persons (user_id, name, birth_date, relation, registered_user_id, visibility) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, person.getUserId());
            stmt.setString(2, person.getName());
            stmt.setString(3, person.getBirthDate());
            stmt.setString(4, person.getRelation());
            
            // Handle nullable registered_user_id
            if (person.getRegisteredUserId() != null) {
                stmt.setInt(5, person.getRegisteredUserId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.setString(6, person.getVisibility());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error adding person: " + e.getMessage());
            return false;
        }
    }

    // Get all persons in the user's tree
    public static List<Person> getPersonsByUser(int userId) {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM persons WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Person p = new Person(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("birth_date"),
                    rs.getString("relation"),
                    rs.getObject("registered_user_id") != null ? rs.getInt("registered_user_id") : null,
                    rs.getString("visibility")
                );
                persons.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving persons: " + e.getMessage());
        }

        return persons;
    }
}
