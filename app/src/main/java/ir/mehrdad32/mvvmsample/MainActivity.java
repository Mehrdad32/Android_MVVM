package ir.mehrdad32.mvvmsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import ir.mehrdad32.mvvmsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(UserViewModel.class);
        model.getUsers().observe(this, users -> {
            if (users == null)
                return;

            binding.outputText.setText("Size: " + model.getUsers().getValue().size());
        });

        binding.plusFab.setOnClickListener(view -> {
            model.addUser();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.outputText.setText("Size: " + model.getUsers().getValue().size());
    }
}