package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.math.BigDecimal;
import java.math.MathContext;

public class SimpleCalculator extends AppCompatActivity {

    TextView display;
    private SharedViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        display = findViewById(R.id.display);
        SharedViewModelFactory sharedViewModelFactory = new SharedViewModelFactory();
        viewModel = new ViewModelProvider(this, sharedViewModelFactory).get(SharedViewModel.class);
        viewModel.getDisplayText().observe(this, text -> display.setText(text));

        if (savedInstanceState == null) {
            viewModel.setC_ceClicked(false);
            viewModel.setOperationAllowed(false);
            viewModel.setOperation("");
            viewModel.setResult(new BigDecimal("0.0", BasicButtons.MATH_CONTEXT));
            viewModel.setDisplayText("0");

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.basicButtons, BasicButtons.class, null)
                    .commit();
        }
    }
}