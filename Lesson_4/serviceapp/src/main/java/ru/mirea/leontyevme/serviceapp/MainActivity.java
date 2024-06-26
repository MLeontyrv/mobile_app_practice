package ru.mirea.leontyevme.serviceapp;

import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.leontyevme.serviceapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding	binding;
    private	int	PermissionCode = 200;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
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
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        View view =	binding.getRoot();
        setContentView(view);
        if	(ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) ==	PackageManager.PERMISSION_GRANTED)	{
            Log.d(MainActivity.class.getSimpleName().toString(), "Разрешения получены");
        }	else {
            Log.d(MainActivity.class.getSimpleName().toString(), "Нет разрешений!");
            ActivityCompat.requestPermissions(this,	new	String[]{POST_NOTIFICATIONS, FOREGROUND_SERVICE}, PermissionCode);
        }
        binding.button1.setOnClickListener(new View.OnClickListener()	{
            @Override
            public void onClick(View v)	{
                Intent	serviceIntent =	new	Intent(MainActivity.this,	PlayerService.class);
                ContextCompat.startForegroundService(MainActivity.this,	serviceIntent);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)	{
                stopService(
                        new Intent(MainActivity.this,	PlayerService.class));
            }
        });
    }
}