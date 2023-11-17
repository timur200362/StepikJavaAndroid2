package com.example.dogs;

public class DogImage {
    private String message;
    private String status;

    public DogImage(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
