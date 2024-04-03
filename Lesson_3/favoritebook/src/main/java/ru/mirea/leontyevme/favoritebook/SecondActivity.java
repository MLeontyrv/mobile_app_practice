package ru.mirea.leontyevme.favoritebook;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView textView = findViewById(R.id.textView2);

        String text = getIntent().getStringExtra("key");
        textView.setText("Любимая книга разработчика – " + text);
    }
    public void click(View view) {
        EditText editTextUserBook = findViewById(R.id.editText);
        String userBookName = editTextUserBook.getText().toString();
        Intent data = new Intent();
        data.putExtra("MESSAGE", "Название Вашей любимой книги: " + userBookName);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}