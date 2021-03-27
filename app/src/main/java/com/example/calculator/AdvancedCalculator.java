package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.math.BigDecimal;

import static com.example.calculator.BasicButtons.MATH_CONTEXT;
import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.log10;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;

public class AdvancedCalculator extends AppCompatActivity {

    TextView display;
    Button sinBtn;
    Button cosBtn;
    Button tanBtn;
    Button lnBtn;
    Button sqrtBtn;
    Button x2Btn;
    Button xYBtn;
    Button logBtn;
    private SharedViewModel viewModel;

    private void bindViews() {
        sinBtn = findViewById(R.id.sinBtn);
        cosBtn = findViewById(R.id.cosBtn);
        tanBtn = findViewById(R.id.tanBtn);
        lnBtn = findViewById(R.id.lnBtn);
        sqrtBtn = findViewById(R.id.sqrtBtn);
        x2Btn = findViewById(R.id.x_2Btn);
        xYBtn = findViewById(R.id.x_yBtn);
        logBtn = findViewById(R.id.logBtn);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        bindViews();

        display = findViewById(R.id.display);
        SharedViewModelFactory sharedViewModelFactory = new SharedViewModelFactory();
        viewModel = new ViewModelProvider(this, sharedViewModelFactory).get(SharedViewModel.class);
        viewModel.getDisplayText().observe(this, text -> display.setText(text));


        if (savedInstanceState == null) {
            viewModel.setC_ceClicked(false);
            viewModel.setOperationAllowed(false);
            viewModel.setOperation("");
            viewModel.setResult(new BigDecimal("0.0", MATH_CONTEXT));
            viewModel.setDisplayText("0");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.basicButtons, BasicButtons.class, null)
                    .commit();
        }

        sinBtn.setOnClickListener(v -> {
            if (isInputValid()) {
                viewModel.setC_ceClicked(false);
                BigDecimal result;
                try {
                    result = new BigDecimal(String.valueOf(sin(Double.parseDouble(viewModel.getDisplayText().getValue().toString()))), MATH_CONTEXT);
                    viewModel.setDisplayText(result.stripTrailingZeros().toEngineeringString());
                } catch (Exception e) {
                    displayInvalidInputToast();
                }
            } else {
                displayInvalidInputToast();
            }
        });

        cosBtn.setOnClickListener(v -> {
            if (isInputValid()) {
                viewModel.setC_ceClicked(false);
                BigDecimal result;
                try {
                    result = new BigDecimal(String.valueOf(cos(Double.parseDouble(viewModel.getDisplayText().getValue().toString()))), MATH_CONTEXT);
                    viewModel.setDisplayText(result.stripTrailingZeros().toEngineeringString());
                } catch (Exception e) {
                    displayInvalidInputToast();
                }
            } else {
                displayInvalidInputToast();
            }
        });

        tanBtn.setOnClickListener(v -> {
            if (isInputValid()) {
                viewModel.setC_ceClicked(false);
                BigDecimal result;
                try {
                    result = new BigDecimal(String.valueOf(tan(Double.parseDouble(viewModel.getDisplayText().getValue().toString()))), MATH_CONTEXT);
                    viewModel.setDisplayText(result.stripTrailingZeros().toEngineeringString());
                } catch (Exception e) {
                    displayInvalidInputToast();
                }
            } else {
                displayInvalidInputToast();
            }
        });

        lnBtn.setOnClickListener(v -> {
            if (isInputValid()) {
                viewModel.setC_ceClicked(false);
                if (isInputPositive()) {
                    BigDecimal result;
                    try {
                        result = new BigDecimal(String.valueOf(log(Double.parseDouble(viewModel.getDisplayText().getValue().toString()))), MATH_CONTEXT);
                        viewModel.setDisplayText(result.stripTrailingZeros().toEngineeringString());
                    } catch (Exception e) {
                        displayInvalidInputToast();
                    }
                } else {
                    displayInvalidInputToast();
                }
            } else {
                displayInvalidInputToast();
            }
        });

        logBtn.setOnClickListener(v -> {
            if (isInputValid()) {
                viewModel.setC_ceClicked(false);
                if (isInputPositive()) {
                    BigDecimal result;
                    try {
                        result = new BigDecimal(String.valueOf(log10(Double.parseDouble(viewModel.getDisplayText().getValue().toString()))), MATH_CONTEXT);
                        viewModel.setDisplayText(result.stripTrailingZeros().toEngineeringString());
                    } catch (Exception e) {
                        displayInvalidInputToast();
                    }
                } else {
                    displayInvalidInputToast();
                }
            } else {
                displayInvalidInputToast();
            }
        });

        sqrtBtn.setOnClickListener(v -> {
            if (isInputValid()) {
                viewModel.setC_ceClicked(false);
                if (isInputPositive()) {
                    BigDecimal result;
                    try {
                        result = new BigDecimal(String.valueOf(sqrt(Double.parseDouble(viewModel.getDisplayText().getValue().toString()))), MATH_CONTEXT);
                        viewModel.setDisplayText(result.stripTrailingZeros().toEngineeringString());
                    } catch (Exception e) {
                        displayInvalidInputToast();
                    }
                } else {
                    displayInvalidInputToast();
                }
            } else {
                displayInvalidInputToast();
            }
        });
        x2Btn.setOnClickListener(v -> {
            if (isInputValid()) {
                viewModel.setC_ceClicked(false);
                BigDecimal result;
                try {
                    result = new BigDecimal(String.valueOf(pow(Double.parseDouble(viewModel.getDisplayText().getValue().toString()), 2)), MATH_CONTEXT);
                    viewModel.setDisplayText(result.stripTrailingZeros().toEngineeringString());
                } catch (Exception e) {
                    displayInvalidInputToast();
                }
            } else {
                displayInvalidInputToast();
            }
        });

        xYBtn.setOnClickListener(v -> {
            if (isInputValid()) {
                viewModel.setC_ceClicked(false);
                BigDecimal temp = new BigDecimal(viewModel.getDisplayText().getValue().toString());
                viewModel.setResult(temp);
                viewModel.setOperation("x^y");
                viewModel.setOperationAllowed(false);
            } else {
                displayInvalidInputToast();
            }
        });
    }

    private void displayInvalidInputToast() {
        CharSequence text = "Invalid input";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    private boolean isInputValid() {
        return !viewModel.getDisplayText().getValue().toString().endsWith("+")
                && !viewModel.getDisplayText().getValue().toString().endsWith("E")
                && !viewModel.getDisplayText().getValue().toString().endsWith(".");
    }

    private boolean isInputPositive() {
        return !viewModel.getDisplayText().getValue().toString().startsWith("-");
    }
}