package com.example.project2part3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project2part3.databinding.ActivityCreateAccountBinding;
import com.example.project2part3.databinding.ActivityMainBinding;

import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {
    private ActivityCreateAccountBinding binding;

    private LibraryDatabase libDb;
    private List<Account> accountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Library database setup
        libDb = LibraryDatabase.getInstance(CreateAccountActivity.this);
        accountList = libDb.account().getAll();

        // Cancel button behavior
        binding.cancelAccountButton.setOnClickListener(v -> finish());

        // Create account button
        binding.createAccountButton.setOnClickListener(v -> {
            String usernameText = binding.usernameEntry.getText().toString();
            String passwordText = binding.passwordEntry.getText().toString();

            if (usernameText.equals("") || passwordText.equals("")) {
                Toast.makeText(this, "One of the fields are blank!", Toast.LENGTH_SHORT).show();
            } else {
                for (Account a : accountList) {
                    if (a.getUsername().equals(usernameText)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);

                        builder.setTitle("That username is already taken!");
                        builder.setPositiveButton("OK", (dialogInterface, i) -> finish());

                        AlertDialog usernameTakenDialog = builder.create();
                        usernameTakenDialog.show();

                        return;
                    }
                }
                libDb.account().addAccount(new Account(usernameText, passwordText));

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Account successfully created!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                libDb.transaction().addTransaction(new Transaction(usernameText, "New account"));

                AlertDialog successDialog = builder.create();
                successDialog.show();
            }
        });
    }
}