package com.carpooling.model;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String googleId;
    private String profilePicture;
    private String role;

    // Constructor (default and parameterized)
    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(int userId, String name, String email, String password, String googleId, String profilePicture, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.googleId = googleId;
        this.profilePicture = profilePicture;
        this.role = role;
    }

    // Getter and Setter methods
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
