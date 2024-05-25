package ru.mirea.leontyevme.yandexdriver;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class App extends Application {
    private final static String YANDEX_MAP_API_KEY = "a170c47b-dfca-4940-b589-ec2ae2c4298b";

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey(YANDEX_MAP_API_KEY);
    }
}
