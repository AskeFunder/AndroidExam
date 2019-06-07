package com.example.keardea.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Parcelable {

    private String name;
    private ArrayList<Account> accounts;
    //private recent =

    protected User(Parcel in) {
        name = in.readString();
        accounts = in.createTypedArrayList(Account.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(accounts);
    }

    public Set<Transaction> getFrequentPersons() {

        List<Transaction> frequentPersonsArray = new ArrayList<>();

        for (Account account : this.accounts) {
            for (Transaction transaction : account.getTransactions()) {
                if (transaction.getAccountNumber() != null && transaction.getAccountNumber() != "") {
                    Log.d("User:", "getFrequentPersons: " + transaction.getAccountNumber());
                    if (!transaction.getRegNumber().equals("71")) {
                        frequentPersonsArray.add(transaction);
                    }
                }
            }
        }



        Set<Transaction> frequentPersonsSet = new HashSet<>(frequentPersonsArray);

        return frequentPersonsSet;
    }

    public Set<Transaction> getFrequentBills() {
        List<Transaction> frequentBillsArray = new ArrayList<>();

        for (Account account : this.accounts) {
            for (Transaction transaction : account.getTransactions()) {
                if (transaction.getAccountNumber() != null && transaction.getAccountNumber() != "") {
                    Log.d("User:", "getFrequentPersons: " + transaction.getAccountNumber());
                    if (transaction.getRegNumber().equals("71")) {
                        frequentBillsArray.add(transaction);
                    }
                }
            }
        }



        Set<Transaction> frequentBillsSet = new HashSet<>(frequentBillsArray);

        return frequentBillsSet;
    }
}
