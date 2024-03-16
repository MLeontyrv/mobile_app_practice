package ru.mirea.leontyevme.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    public void onClickToast(View view) {
        int count = editText.getText().toString().length();
        Toast toast = Toast.makeText(getApplicationContext(),
                "СТУДЕНТ №15 ГРУППА БСБО-04-22 Количество символов - " + count,
                Toast.LENGTH_SHORT);
        toast.show();
    }

}