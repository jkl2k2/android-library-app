package com.example.project2part3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project2part3.databinding.ActivityHoldLoginBinding;

import java.util.List;

public class HoldLogin extends AppCompatActivity {
    ActivityHoldLoginBinding binding;
    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHoldLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDatabase.getInstance(HoldLogin.this);
        
        binding.cancelHoldLoginButton.setOnClickListener(v -> finish());

        String requestedTitle = getIntent().getExtras().getString("title");
        
        binding.holdLoginButton.setOnClickListener(v -> {
            List<Account> accounts = db.account().getAll();
            
            if (accounts.contains(new Account(binding.holdUsernameEntry.getText().toString(), binding.holdPasswordEntry.getText().toString()))) {
                Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show();

                db.book().flipAvailable(db.book().findByTitle(requestedTitle).getId());

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Login successful!")
                        .setMessage(String.format("Customer username: %s%nBook Title: %s%nReservation number: %d", binding.holdUsernameEntry.getText().toString(), requestedTitle, LibraryDatabase.reservationNumber))
                        .setPositiveButton("OK", (dialogInterface, i) -> startActivity(new Intent(this, MainActivity.class)));

                AlertDialog successDialog = builder.create();
                successDialog.show();
            } else {
                Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}