package com.example.project2part3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.project2part3.databinding.ActivityPlaceHoldBinding;

public class PlaceHoldActivity extends AppCompatActivity {

    private ActivityPlaceHoldBinding binding;
    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceHoldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialogFragment = new HoldBookDialog();

        Bundle bundle = new Bundle();

        binding.csGenreButton.setOnClickListener(v -> {
            bundle.putInt("genre", 0);
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(), "");
        });

        binding.fictionGenreButton.setOnClickListener(v -> {
            bundle.putInt("genre", 1);
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(), "");
        });

        binding.memoirGenreButton.setOnClickListener(v -> {
            bundle.putInt("genre", 2);
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(), "");
        });
    }
}