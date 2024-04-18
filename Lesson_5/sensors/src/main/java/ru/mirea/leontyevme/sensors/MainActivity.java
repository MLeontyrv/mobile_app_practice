package ru.mirea.leontyevme.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.mirea.leontyevme.sensors.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private	ActivityMainBinding	binding;
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

        binding	= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SensorManager sensorManager	= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        ListView listSensor	= binding.listView;

        // создаем список для отображения в	ListView найденных датчиков
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        for	(int i = 0;	i <	sensors.size();	i++) {
            HashMap<String,	Object>	 sensorTypeList	= new HashMap<>();
            sensorTypeList.put("Name", sensors.get(i).getName());
            sensorTypeList.put("Value",	sensors.get(i).getMaximumRange());
            arrayList.add(sensorTypeList);
        }
        // cоздаем адаптер и устанавливаем тип адаптера	- отображение двух полей
        SimpleAdapter mHistory =
                new	SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2,
                        new	String[]{"Name", "Value"},
                        new	int[]{android.R.id.text1, android.R.id.text2});
        listSensor.setAdapter(mHistory);

    }
}