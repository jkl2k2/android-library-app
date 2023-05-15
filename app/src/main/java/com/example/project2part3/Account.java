package com.example.project2part3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "accountList")
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String username;

    @ColumnInfo
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Account) {
            Account compare = (Account) o;
            return compare.username.equals(username) && compare.password.equals(password);
        } else {
            return false;
        }
    }
}
