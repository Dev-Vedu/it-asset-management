package com.itasset.management.model;

import jakarta.persistence.*;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // ✅ PRIMARY KEY FIXED

    private String description;
    private String status; // OPEN / RESOLVED
    private String type;   // HARDWARE / NETWORK / SOFTWARE

    private Long userId;   // ✅ store employee id separately

    @ManyToOne
    private User employee;

    @ManyToOne
    private User worker;


    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public User getEmployee() {
        return employee;
    }

    public User getWorker() {
        return worker;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }
}
