package com.example.project2part3;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class, Account.class, Transaction.class}, version=7, exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase {

    private static LibraryDatabase sInstance;
    public abstract BookDao book();
    public abstract AccountDao account();
    public abstract TransactionDao transaction();
    public static int reservationNumber = 1;

    public static synchronized LibraryDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            LibraryDatabase.class, "library.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    public void populateInitialData() {
        runInTransaction(() -> {
            if (book().count() == 0) {
                book().addBook(new Book("Angela's Ashes", "Frank McCourt", "Memoir"));
                book().addBook(new Book("Strengthening Deep Neural Networks", "Katy Warr", "Computer Science"));
                book().addBook(new Book("Frankenstein", "Mary Shelley", "Fiction"));
            }

            if (account().count() == 0) {
                account().addAccount(new Account("anton", "t3nn1sch@mp22"));
                account().addAccount(new Account("bernie", "thyr01dExp3rt"));
                account().addAccount(new Account("shirleybee", "carmel2chicago"));
            }
        });
    }
}
