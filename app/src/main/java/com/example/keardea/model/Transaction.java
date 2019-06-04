package com.example.keardea.model;

import java.util.Date;

public class Transaction {

    private String title;
    private Date date;
    private double sum;
    private String accountNumber;
    private String regNumber;
    private String source;

    public Transaction(String title, Date date, double sum, String accountNumber, String regNumber, String source) {
        this.title = title;
        this.date = date;
        this.sum = sum;
        this.accountNumber = accountNumber;
        this.regNumber = regNumber;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public double getSum() {
        return sum;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getSource() {
        return source;
    }
}
