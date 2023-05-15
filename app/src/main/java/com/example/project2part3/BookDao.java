package com.example.project2part3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void addBook(Book b);

    @Query("SELECT COUNT(*) FROM bookList")
    int count();

    @Query("SELECT * FROM bookList")
    List<Book> getAll();

    @Query("SELECT * FROM bookList WHERE id = :id")
    Book findById(int id);

    @Query("SELECT * FROM bookList WHERE title = :title")
    Book findByTitle(String title);

    @Query("UPDATE bookList SET available = NOT available WHERE id = :id")
    void flipAvailable(int id);

    @Update
    void update(Book b);
}
