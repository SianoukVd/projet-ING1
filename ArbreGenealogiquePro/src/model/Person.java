/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Person {
    private int id;
    private int userId; // owner of the tree
    private String name;
    private String birthDate;
    private String relation; // e.g. father, mother, brother...
    private Integer registeredUserId; // null if not linked to a real user
    private String visibility; // public / private / protected

    // Constructor for new person (no ID yet)
    public Person(int userId, String name, String birthDate, String relation,
                  Integer registeredUserId, String visibility) {
        this.userId = userId;
        this.name = name;
        this.birthDate = birthDate;
        this.relation = relation;
        this.registeredUserId = registeredUserId;
        this.visibility = visibility;
    }

    // Constructor with ID (from database)
    public Person(int id, int userId, String name, String birthDate, String relation,
                  Integer registeredUserId, String visibility) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.birthDate = birthDate;
        this.relation = relation;
        this.registeredUserId = registeredUserId;
        this.visibility = visibility;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getRelation() {
        return relation;
    }

    public Integer getRegisteredUserId() {
        return registeredUserId;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
