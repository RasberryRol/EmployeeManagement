package com.rolson.employeemanagement.models;

import java.util.Random;

public class Employee {
    private int empID;
    private String name;
    private String email;
    private String phone;
    private String role = "user";
    private String username;
    private String password;

    public Employee(){}


    public Employee(String name){
        this.name = name;
    }

    public Employee(int empID, String name, String email, String phone, String role, String username, String password) {
        this.empID = empID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "Employee{" +
                "empID='" + empID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

