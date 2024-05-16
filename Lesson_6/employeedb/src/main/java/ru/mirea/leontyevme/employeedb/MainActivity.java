package ru.mirea.leontyevme.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDatabase database = App.getInstance().getDatabase();
        final EmployeeDao employeeDao = database.employeeDao();

        employeeDao.insert(new Employee(1,"Алая ведьма", 17));
        employeeDao.insert(new Employee(2,"Доктор Стрэндж", 52));
        employeeDao.insert(new Employee(3,"Железный человек", 44));
        employeeDao.insert(new Employee(3,"Капитан Америка", 24));
        employeeDao.insert(new Employee(3,"Тор", 100));

    }

}