package com.example.calculator;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainScreen extends AppCompatActivity {

    private Button simpleBtn;
    private Button advancedBtn;
    private Button aboutBtn;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        simpleBtn = findViewById(R.id.simpleBtn);
        advancedBtn = findViewById(R.id.advancedBtn);
        aboutBtn = findViewById(R.id.aboutBtn);
        exitBtn = findViewById(R.id.exitBtn);
        simpleBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreen.this, SimpleCalculator.class);
            startActivity(intent);
        });
        advancedBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreen.this, AdvancedCalculator.class);
            startActivity(intent);
        });
        aboutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreen.this, About.class);
            startActivity(intent);
        });
        exitBtn.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });
    }
}