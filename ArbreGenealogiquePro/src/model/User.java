/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class User {
    private int id;
    private String ssn;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String nationality;
    private String password;
    private String publicCode;
    private String privateCode;
    private boolean isVerified;

    // Constructor for new user (without ID)
    public User(String ssn, String firstName, String lastName, String birthDate,
                String nationality, String password, String publicCode, String privateCode) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.password = password;
        this.publicCode = publicCode;
        this.privateCode = privateCode;
        this.isVerified = false;
    }

    // Constructor with ID (from database)
    public User(int id, String ssn, String firstName, String lastName, String birthDate,
                String nationality, String password, String publicCode, String privateCode, boolean isVerified) {
        this.id = id;
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.password = password;
        this.publicCode = publicCode;
        this.privateCode = privateCode;
        this.isVerified = isVerified;
    }

    // Getters and setters
    // model.User.java
    public String getFullName() {
        return firstName + " " + lastName;
    }
    public int getId() {
        return id;
    }

    public String getSsn() {
        return ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPassword() {
        return password;
    }

    public String getPublicCode() {
        return publicCode;
    }

    public String getPrivateCode() {
        return privateCode;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
