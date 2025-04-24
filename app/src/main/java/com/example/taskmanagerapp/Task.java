package com.example.taskmanagerapp;

public class Task {
    private String title;
    private String description;
    private String date;

    public Task(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public String getDate() { return date; }

    public void setTitle(String title) { this.title = title; }

    public void setDescription(String description) { this.description = description; }

    public void setDate(String date) { this.date = date; }
}
