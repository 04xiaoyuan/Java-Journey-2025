package com.xy.sutdentsystem;

public class User {
    private String username;
    private String password;
    private String userID;
    private String pNumber;

    public User() {
    }

    public User(String username, String password, String userID, String pNumber) {
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.pNumber = pNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }
}
