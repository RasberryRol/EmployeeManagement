package com.rolson.employeemanagement.models;

public class EmployeeMessage {
    String message;
    String status = "Pending";

    public EmployeeMessage(){}

    public EmployeeMessage(String message, String status){
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
