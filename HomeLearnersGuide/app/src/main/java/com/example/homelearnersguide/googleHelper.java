package com.example.homelearnersguide;

public class googleHelper {

    String subjectCode, subjectTitle,subjectLink,subjectYear,subjectSection,learningSystem;

    public googleHelper() {
    }

    public googleHelper(String subjectCode, String subjectTitle, String subjectLink, String subjectYear, String subjectSection, String learningSystem) {
        this.subjectCode = subjectCode;
        this.subjectTitle = subjectTitle;
        this.subjectLink = subjectLink;
        this.subjectYear = subjectYear;
        this.subjectSection = subjectSection;
        this.learningSystem = learningSystem;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectLink() {
        return subjectLink;
    }

    public void setSubjectLink(String subjectLink) {
        this.subjectLink = subjectLink;
    }

    public String getSubjectYear() {
        return subjectYear;
    }

    public void setSubjectYear(String subjectYear) {
        this.subjectYear = subjectYear;
    }

    public String getSubjectSection() {
        return subjectSection;
    }

    public void setSubjectSection(String subjectSection) {
        this.subjectSection = subjectSection;
    }

    public String getLearningSystem() {
        return learningSystem;
    }

    public void setLearningSystem(String learningSystem) {
        this.learningSystem = learningSystem;
    }
}
