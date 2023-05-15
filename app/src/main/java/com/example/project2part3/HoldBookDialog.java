package com.example.project2part3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.project2part3.databinding.DialogHoldBookBinding;

import java.util.ArrayList;
import java.util.List;

public class HoldBookDialog extends DialogFragment {
    private DialogHoldBookBinding binding;
    private LibraryDatabase db;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogHoldBookBinding.inflate(LayoutInflater.from(getContext()));
        db = LibraryDatabase.getInstance(getActivity());

        int genre = getArguments().getInt("genre");

        List<Book> bookList = db.book().getAll();
        List<String> stringBookList = new ArrayList<>();

        if (genre == 0) {
            // Computer science
            for(Book b : bookList) {
                if (b.getGenre().equals("Computer Science") && b.isAvailable()) {
                    stringBookList.add(b.getTitle());
                }
            }
        } else if (genre == 1) {
            // Fiction
            for(Book b : bookList) {
                if (b.getGenre().equals("Fiction") && b.isAvailable()) {
                    stringBookList.add(b.getTitle());
                }
            }
        } else if (genre == 2) {
            // Memoir
            for(Book b : bookList) {
                if (b.getGenre().equals("Memoir") && b.isAvailable()) {
                    stringBookList.add(b.getTitle());
                }
            }
        } else {
            Toast.makeText(getContext(), "BOOK GENRE CATCH (should never happen)", Toast.LENGTH_SHORT).show();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (stringBookList.size() == 0) {
            // No book of that genre available
            builder.setView(binding.getRoot())
                    .setTitle("There are no books of that genre available!")
                    .setPositiveButton("Exit", ((dialog, which) -> startActivity(new Intent(getContext(), MainActivity.class))));
        } else {
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, stringBookList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.bookSpinner.setAdapter(adapter);

            binding.bookSpinner.setVisibility(View.VISIBLE);

            builder.setView(binding.getRoot())
                    .setTitle("Select a book")
                    .setPositiveButton("Place Hold", (dialog, which) -> {
                        LibraryDatabase.reservationNumber++;
                        Bundle bundle = new Bundle();
                        bundle.putString("title", binding.bookSpinner.getSelectedItem().toString());
                        Intent loginIntent = new Intent(getContext(), HoldLogin.class);
                        loginIntent.putExtras(bundle);
                        startActivity(loginIntent);
                    })
                    .setNegativeButton("Cancel", ((dialog, which) -> { }));
        }

        return builder.create();
    }
}
