package com.example.project2part3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project2part3.databinding.ActivityCreateAccountBinding;
import com.example.project2part3.databinding.ActivityManageSystemBinding;

public class ManageSystemActivity extends AppCompatActivity {
    private ActivityManageSystemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageSystemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Cancel button behavior
        binding.cancelLoginButton.setOnClickListener(v -> finish());

        // Create account button
        binding.loginButton.setOnClickListener(v -> {
            String usernameText = binding.librarianUsernameEntry.getText().toString();
            String passwordText = binding.librarianPasswordEntry.getText().toString();

            if (usernameText.equals("") || passwordText.equals("")) {
                Toast.makeText(this, "One of the fields are blank!", Toast.LENGTH_SHORT).show();
            } else {
                if (usernameText.equals("!admin2") && passwordText.equals("!admin2")) {
                    startActivity(new Intent(this, LibrarianMenuActivity.class));
                }
            }
        });
    }
}