package com.example.ss9.model;

public class Customer {
    private Long id;
    private String username;
    private String phone;
    private String address;
    private String gender;
    private String email;

    public Customer() {}

    public Customer(Long id, String username, String phone, String address, String gender, String email) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

