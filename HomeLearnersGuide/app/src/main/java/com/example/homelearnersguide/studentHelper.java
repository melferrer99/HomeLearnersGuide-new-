package com.example.homelearnersguide;

public class studentHelper {

    String email, learningSystem, password, section, userName,userType,year,studentFullName;

    public studentHelper() {
    }

    public studentHelper(String email, String learningSystem, String password, String section, String userName, String userType, String year, String studentFullName) {
        this.email = email;
        this.learningSystem = learningSystem;
        this.password = password;
        this.section = section;
        this.userName = userName;
        this.userType = userType;
        this.year = year;
        this.studentFullName = studentFullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLearningSystem() {
        return learningSystem;
    }

    public void setLearningSystem(String learningSystem) {
        this.learningSystem = learningSystem;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }
}
