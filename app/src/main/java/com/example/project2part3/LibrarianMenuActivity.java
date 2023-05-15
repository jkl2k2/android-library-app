package com.example.project2part3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;

import com.example.project2part3.databinding.ActivityLibrarianMenuBinding;

import java.util.List;

public class LibrarianMenuActivity extends AppCompatActivity {
    private ActivityLibrarianMenuBinding binding;

    private LibraryDatabase libDb;
    private List<Transaction> transactionList;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLibrarianMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialogFragment = new AddBookDialog();

        // Library database setup
        libDb = LibraryDatabase.getInstance(LibrarianMenuActivity.this);
        transactionList = libDb.transaction().getAll();

        // Log out button behavior
        binding.adminLogoutButton.setOnClickListener(v -> finish());

        if (transactionList.size() > 0) {
            binding.latestTransaction.setText(transactionList.get(transactionList.size() - 1).toString());
        }

        binding.adminAddBookButton.setOnClickListener(v -> dialogFragment.show(getSupportFragmentManager(), ""));
    }

}