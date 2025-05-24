/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Media {
    private int id;
    private int uploaderId;
    private String filePath;
    private String description;
    private String uploadDate;
    private String visibility;

    public Media(int uploaderId, String filePath, String description, String uploadDate, String visibility) {
        this.uploaderId = uploaderId;
        this.filePath = filePath;
        this.description = description;
        this.uploadDate = uploadDate;
        this.visibility = visibility;
    }

    public Media(int id, int uploaderId, String filePath, String description, String uploadDate, String visibility) {
        this.id = id;
        this.uploaderId = uploaderId;
        this.filePath = filePath;
        this.description = description;
        this.uploadDate = uploadDate;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
    }

    public int getUploaderId() {
        return uploaderId;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getDescription() {
        return description;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getVisibility() {
        return visibility;
    }
}
