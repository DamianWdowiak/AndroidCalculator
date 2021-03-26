package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.lang.Math.pow;


public class BasicButtons extends Fragment implements View.OnClickListener {

    private static final int PRECISION = 10;
    public static final MathContext MATH_CONTEXT = new MathContext(PRECISION);
    Button acBtn;
    Button bkspBtn;
    Button plusMinusBtn;
    Button divideBtn;
    Button num7Btn;
    Button num8Btn;
    Button num9Btn;
    Button multiplyBtn;
    Button num4Btn;
    Button num5Btn;
    Button num6Btn;
    Button subtractBtn;
    Button num1Btn;
    Button num2Btn;
    Button num3Btn;
    Button addBtn;
    Button num0Btn;
    Button dotBtn;
    Button resultBtn;
    Button percentBtn;
    private SharedViewModel viewModel;

    public BasicButtons() {
        // Required empty public constructor
    }


    private void clearAll() {
        viewModel.setDisplayText("0");
        viewModel.setOperationAllowed(false);
        viewModel.setOperation("");
        viewModel.setResult(new BigDecimal("0.0", MATH_CONTEXT));
    }

    private void bindViews() {
        acBtn = getView().findViewById(R.id.acBtn);
        bkspBtn = getView().findViewById(R.id.bkspBtn);
        plusMinusBtn = getView().findViewById(R.id.plus_minusBtn);
        divideBtn = getView().findViewById(R.id.divideBtn);
        num7Btn = getView().findViewById(R.id.num7Btn);
        num8Btn = getView().findViewById(R.id.num8Btn);
        num9Btn = getView().findViewById(R.id.num9Btn);
        multiplyBtn = getView().findViewById(R.id.multiplyBtn);
        num4Btn = getView().findViewById(R.id.num4Btn);
        num5Btn = getView().findViewById(R.id.num5Btn);
        num6Btn = getView().findViewById(R.id.num6Btn);
        subtractBtn = getView().findViewById(R.id.subtractBtn);
        num1Btn = getView().findViewById(R.id.num1Btn);
        num2Btn = getView().findViewById(R.id.num2Btn);
        num3Btn = getView().findViewById(R.id.num3Btn);
        addBtn = getView().findViewById(R.id.addBtn);
        num0Btn = getView().findViewById(R.id.num0Btn);
        dotBtn = getView().findViewById(R.id.dotBtn);
        resultBtn = getView().findViewById(R.id.resultBtn);
        percentBtn = getView().findViewById(R.id.percentBtn);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic_buttons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews();
        SharedViewModelFactory sharedViewModelFactory = new SharedViewModelFactory();
        viewModel = new ViewModelProvider(requireActivity(), sharedViewModelFactory).get(SharedViewModel.class);

        acBtn.setOnClickListener(v -> clearAll());
        bkspBtn.setOnClickListener(v -> {
            if (!viewModel.getC_ceClicked()) {
                viewModel.setC_ceClicked(true);
                String displayValue = viewModel.getDisplayText().getValue().toString();
                if (!displayValue.equals("0")) {
                    viewModel.setDisplayText(displayValue.substring(0, displayValue.length() - 1));
                    if (viewModel.getDisplayText().getValue().toString().isEmpty()) {
                        clearAll();
                    }
                }
            } else {
                viewModel.setC_ceClicked(false);
                clearAll();
            }
        });
        plusMinusBtn.setOnClickListener(v -> {
            String displayValue = viewModel.getDisplayText().getValue().toString();
            if (!displayValue.equals("0")) {
                if (displayValue.charAt(0) == '-') {
                    viewModel.setDisplayText(displayValue.substring(1));
                } else {
                    viewModel.setDisplayText("-" + displayValue);
                }
                viewModel.setC_ceClicked(false);
            }
        });
        dotBtn.setOnClickListener(v -> {
            String displayValue = viewModel.getDisplayText().getValue().toString();
            if (Character.isDigit(displayValue.charAt(displayValue.length() - 1))) {
                if (!displayValue.contains(".")) {
                    viewModel.setDisplayText(displayValue + ".");
                }
            }
        });
        divideBtn.setOnClickListener(this);
        num7Btn.setOnClickListener(this);
        num8Btn.setOnClickListener(this);
        num9Btn.setOnClickListener(this);
        multiplyBtn.setOnClickListener(this);
        num4Btn.setOnClickListener(this);
        num5Btn.setOnClickListener(this);
        num6Btn.setOnClickListener(this);
        subtractBtn.setOnClickListener(this);
        num1Btn.setOnClickListener(this);
        num2Btn.setOnClickListener(this);
        num3Btn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        num0Btn.setOnClickListener(this);
        resultBtn.setOnClickListener(this);
        percentBtn.setOnClickListener(v -> {
            if (isInputValid()) {
                viewModel.setC_ceClicked(false);
                BigDecimal toPercent = new BigDecimal("0.01");
                BigDecimal temp = new BigDecimal(viewModel.getDisplayText().getValue().toString()).multiply(toPercent);
                BigDecimal result;
                try {
                    result = viewModel.getResult().multiply(temp, MATH_CONTEXT).stripTrailingZeros();
                    viewModel.setDisplayText(result.stripTrailingZeros().toString());
                } catch (Exception e) {
                    displayInvalidInputToast();
                }
            } else {
                displayInvalidInputToast();
            }
        });
    }

    private boolean isInputValid() {
        return !viewModel.getDisplayText().getValue().toString().endsWith("+")
                && !viewModel.getDisplayText().getValue().toString().endsWith("E")
                && !viewModel.getDisplayText().getValue().toString().endsWith(".");
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        Button btn = getView().findViewById(v.getId());
        String buttonText = btn.getText().toString();
        viewModel.setC_ceClicked(false);


        if (Character.isDigit(buttonText.charAt(0))) {

            if (viewModel.getOperationAllowed() && !viewModel.getDisplayText().getValue().equals("0")) {
                if (viewModel.getDisplayText().getValue().length() < PRECISION) {
                    viewModel.setDisplayText(viewModel.getDisplayText().getValue() + buttonText);
                }
                viewModel.setOperationAllowed(true);
            } else {
                viewModel.setOperationAllowed(true);
                viewModel.setDisplayText(buttonText);
                if(viewModel.getOperation().equals("=")){
                    viewModel.setOperationAllowed(false);
                }
            }
        } else {
            if (isInputValid()) {
                BigDecimal temp = new BigDecimal(viewModel.getDisplayText().getValue().toString());

                if (buttonText.equals("=") || (viewModel.getOperationAllowed() && !viewModel.getOperation().equals(""))) {
                    switch (viewModel.getOperation()) {
                        case "+":
                            viewModel.setResult(viewModel.getResult().add(temp, MATH_CONTEXT));
                            break;
                        case "-":
                            viewModel.setResult(viewModel.getResult().subtract(temp, MATH_CONTEXT));
                            break;
                        case "x":
                            viewModel.setResult(viewModel.getResult().multiply(temp, MATH_CONTEXT));
                            break;
                        case "x^y":
                            try {
                                temp = new BigDecimal(String.valueOf(
                                        pow(viewModel.getResult().doubleValue(), temp.doubleValue())), MATH_CONTEXT);
                                viewModel.setResult(temp);
                            } catch (Exception e) {
                                CharSequence text = "Invalid input";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(getActivity(), text, duration);
                                toast.show();
                            }
                            break;
                        case "/":
                            if (temp.equals(new BigDecimal(0))) {
                                CharSequence text = "Cannot divide by zero";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(getActivity(), text, duration);
                                toast.show();
                            } else {
                                viewModel.setResult(viewModel.getResult().divide(temp, MATH_CONTEXT));
                            }
                            break;

                    }
                    viewModel.setDisplayText(viewModel.getResult().toString());
                } else {
                    viewModel.setResult(temp);
                }
                viewModel.setOperation(buttonText);
                viewModel.setOperationAllowed(false);
            } else {
                displayInvalidInputToast();
            }
        }
    }
    private void displayInvalidInputToast() {
        CharSequence text = "Invalid input";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getActivity(), text, duration);
        toast.show();
    }
}