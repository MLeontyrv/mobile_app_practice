package ru.mirea.leontyevme.getsharedpreferences;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.leontyevme.getsharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        load();
    }
    public void load() {
        SharedPreferences preferences = getSharedPreferences("gsp_settings",	Context.MODE_PRIVATE);
        binding.editTextText.setText(String.valueOf(preferences.getInt("group_number", 0)));
        binding.editTextText2.setText(String.valueOf(preferences.getInt("list_number", 0)));
        binding.editTextText3.setText(preferences.getString("favorite_film", "none"));
    }

    public void save(View view) {
        SharedPreferences sharedPref = getSharedPreferences("gsp_settings",	Context.MODE_PRIVATE);
        SharedPreferences.Editor editor	= sharedPref.edit();

        int groupNumber = Integer.parseInt(binding.editTextText.getText().toString());
        int listNumber = Integer.parseInt(binding.editTextText2.getText().toString());
        String favoriteFilm = binding.editTextText3.getText().toString();

        editor.putInt("group_number", groupNumber);
        editor.putInt("list_number", listNumber);
        editor.putString("favorite_film", favoriteFilm);

        editor.apply();
    }
}