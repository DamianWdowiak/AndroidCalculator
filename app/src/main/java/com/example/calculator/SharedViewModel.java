package com.example.calculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<CharSequence> displayText = new MutableLiveData<>();
    private final MutableLiveData<String> operation = new MutableLiveData<>();
    private final MutableLiveData<BigDecimal> result = new MutableLiveData<>();
    private final MutableLiveData<Boolean> operationAllowed = new MutableLiveData<>();
    private final MutableLiveData<Boolean> c_ceClicked = new MutableLiveData<>();

    public String getOperation() {
        return operation.getValue();
    }

    public void setOperation(String text) {
        operation.setValue(text);
    }

    public BigDecimal getResult() {
        return result.getValue();
    }

    public void setResult(BigDecimal value) {
        result.setValue(value.stripTrailingZeros());
    }

    public Boolean getOperationAllowed() {
        return operationAllowed.getValue();
    }

    public void setOperationAllowed(boolean flag) {
        operationAllowed.setValue(flag);
    }

    public Boolean getC_ceClicked() {
        return c_ceClicked.getValue();
    }

    public void setC_ceClicked(boolean flag) {
        c_ceClicked.setValue(flag);
    }

    public LiveData<CharSequence> getDisplayText() {
        return displayText;
    }

    public void setDisplayText(CharSequence text) {
        displayText.setValue(text);
    }
}
