package com.example.keardea.model;

import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList<Account> accounts;
    //private recent =

    public void addAccount(String accountName, String accountType) {
        Account account = new Account(accountName, accountType, 0);
        this.accounts.add(account);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public User(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }
}
