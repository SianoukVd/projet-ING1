/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Media;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaDAO {

    
    
    public static boolean deleteMedia(int mediaId) {
    String sql = "DELETE FROM media WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, mediaId);
        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("❌ Error deleting media: " + e.getMessage());
        return false;
    }
}
    
    
    public static boolean addMedia(Media media) {
        String sql = "INSERT INTO media (uploader_id, file_path, description, upload_date, visibility) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, media.getUploaderId());
            stmt.setString(2, media.getFilePath());
            stmt.setString(3, media.getDescription());
            stmt.setString(4, media.getUploadDate());
            stmt.setString(5, media.getVisibility());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error adding media: " + e.getMessage());
            return false;
        }
    }

    public static List<Media> getMediaByUser(int userId) {
        List<Media> mediaList = new ArrayList<>();
        String sql = "SELECT * FROM media WHERE uploader_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                mediaList.add(new Media(
                    rs.getInt("id"),
                    rs.getInt("uploader_id"),
                    rs.getString("file_path"),
                    rs.getString("description"),
                    rs.getString("upload_date"),
                    rs.getString("visibility")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error loading media: " + e.getMessage());
        }

        return mediaList;
    }
}
