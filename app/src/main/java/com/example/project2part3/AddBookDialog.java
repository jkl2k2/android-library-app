package com.example.project2part3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.example.project2part3.databinding.DialogAddBookBinding;

public class AddBookDialog extends DialogFragment {
    private DialogAddBookBinding binding;
    private LibraryDatabase db;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogAddBookBinding.inflate(LayoutInflater.from(getContext()));
        db = LibraryDatabase.getInstance(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(binding.getRoot())
                .setTitle("Add a book")
                .setPositiveButton("Add", (dialog, which) -> {
                    if (!binding.bookTitle.getText().toString().equals("") && !binding.bookAuthor.getText().toString().equals("") && !binding.bookGenre.getText().toString().equals("")) {
                        db.book().addBook(new Book(binding.bookTitle.getText().toString(), binding.bookAuthor.getText().toString(), binding.bookGenre.getText().toString()));

                        Toast toast = Toast.makeText(getContext(), "Successfully added book!", Toast.LENGTH_SHORT);
                        toast.show();

                        getContext().startActivity(new Intent(getContext(), MainActivity.class));
                    } else {
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());

                        builder2.setTitle("One of the fields is blank!");
                        builder2.setPositiveButton("OK", (dialogInterface, i) -> {});

                        AlertDialog blankFieldFragment = builder2.create();
                        blankFieldFragment.show();
                    }
                })
                .setNegativeButton("Cancel", ((dialog, which) -> { }));

        return builder.create();
    }
}
