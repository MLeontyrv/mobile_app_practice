package ru.mirea.leontyevme.simplefragmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Fragment fragment1, fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment1 = new FirstFragment();
        fragment2 = new SecondFragment();
        if (view.getId() == R.id.button) {
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment1).commit();
        }
        else if (view.getId() == R.id.button1) {
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment2).commit();
        }
    }
}