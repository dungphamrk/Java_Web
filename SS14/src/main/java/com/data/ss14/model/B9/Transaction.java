package com.data.ss14.model.B9;

public class Transaction {
    private String description;
    private double amount;
    private String type; // "thu" hoặc "chi"

    public Transaction() {}

    public Transaction(String description, double amount, String type) {
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}

