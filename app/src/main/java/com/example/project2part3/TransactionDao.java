package com.example.project2part3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void addTransaction(Transaction t);

    @Query("SELECT COUNT(*) FROM transactionList")
    int count();

    @Query("SELECT * FROM transactionList")
    List<Transaction> getAll();

    @Query("select * from transactionList where id = :id")
    Transaction findById(int id);

    @Update
    void update(Transaction t);
}
