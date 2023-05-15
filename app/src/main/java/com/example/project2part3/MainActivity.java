package com.example.project2part3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project2part3.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    // Library database
    private LibraryDatabase libDb;
    private List<Account> accountList;
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Library database initialization
        libDb = LibraryDatabase.getInstance(MainActivity.this);
        libDb.populateInitialData();
        accountList = libDb.account().getAll();
        bookList = libDb.book().getAll();

        // Create account button intent
        binding.createButton.setOnClickListener(v -> startActivity(new Intent(this, CreateAccountActivity.class)));

        // Place hold button intent
        binding.holdButton.setOnClickListener(v -> startActivity(new Intent(this, PlaceHoldActivity.class)));

        // Manage system button intent
        binding.systemButton.setOnClickListener(v -> startActivity(new Intent(this, ManageSystemActivity.class)));

    }
}