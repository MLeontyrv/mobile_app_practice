package ru.mirea.leontyevme.yandexmaps;

import android.app.Application;
import com.yandex.mapkit.MapKitFactory;

public class YandexMapApplication extends Application {
    private final static String YANDEX_MAP_API_KEY = "c2f27831-2aaf-481d-81ce-682682d0798d";

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey(YANDEX_MAP_API_KEY);
    }
}
