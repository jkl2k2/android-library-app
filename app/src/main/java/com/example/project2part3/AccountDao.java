package com.example.project2part3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccountDao {
    @Insert
    void addAccount(Account a);

    @Query("SELECT COUNT(*) FROM accountList")
    int count();

    @Query("SELECT * FROM accountList")
    List<Account> getAll();

    @Query("select * from accountList where id = :id")
    Account findById(int id);

    @Update
    void update(Account a);
}
