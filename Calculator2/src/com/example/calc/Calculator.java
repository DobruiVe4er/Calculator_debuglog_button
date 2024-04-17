package com.example.calc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends Activity implements View.OnClickListener, TextWatcher {
    EditText etResult;
    Button btnPlus, btnMinus, btnMultiply, btnDivide, btnClear, btnAbs, btnDiv, btnMod, btnStepen, button0, button1,
            button2, button3, button4, button5, button6, button7, button8, button9, btnCalculate, btnToggleDebugLog;
    TextView tvCurrentMethod, tvLastValue;

    boolean debugLogEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        etResult = (EditText) findViewById(R.id.etResult);
        etResult.addTextChangedListener(this);

        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnAbs = (Button) findViewById(R.id.btnAbs);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnMod = (Button) findViewById(R.id.btnMod);
        btnStepen = (Button) findViewById(R.id.btnStepen);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnToggleDebugLog = (Button) findViewById(R.id.btnToggleDebugLog);

        tvCurrentMethod = (TextView) findViewById(R.id.tvCurrentMethod);
        tvLastValue = (TextView) findViewById(R.id.tvLastValue);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnAbs.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMod.setOnClickListener(this);
        btnStepen.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);

        btnToggleDebugLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDebugLog();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlus:
            case R.id.btnMinus:
            case R.id.btnMultiply:
            case R.id.btnDivide:
            case R.id.btnStepen:
                etResult.append(((Button) view).getText());
                break;
            case R.id.button0:
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
                etResult.append(((Button) view).getText());
                break;
            case R.id.btnClear:
                etResult.setText("");
                break;
            case R.id.btnAbs:
                if (etResult.getText().length() > 0) {
                    double double_num1 = Double.parseDouble(etResult.getText().toString());
                    double_num1 = Math.abs(double_num1);
                    etResult.setText(String.valueOf(double_num1));
                }
                break;
            case R.id.btnDiv:
                try {
                    if (etResult.getText().length() > 0) {
                        int int_num1 = Integer.parseInt(etResult.getText().toString());
                        int int_num2 = Integer.parseInt(etResult.getText().toString());
                        if (int_num2 == 0) {
                            throw new CalculatorException("Division by zero is not allowed");
                        }
                        int int_result = int_num1 / int_num2;
                        etResult.setText(String.valueOf(int_result));
                    }
                } catch (CalculatorException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnMod:
                // Обработка кнопки btnMod
                break;
            case R.id.btnCalculate:
                // Обработка кнопки btnCalculate
                break;
            case R.id.btnToggleDebugLog:
                toggleDebugLog();
                break;
        }

        // Вывод текущего метода
        tvCurrentMethod.setText("Текущий метод: " + getResources().getResourceEntryName(view.getId()));
        // Вывод последнего значения кнопки
        tvLastValue.setText("Последнее значение: " + ((Button) view).getText());
    }

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    class CalculatorException extends Exception {
        CalculatorException(String message) {
            super(message);
        }
    }

    private void toggleDebugLog() {
        debugLogEnabled = !debugLogEnabled;
        if (debugLogEnabled) {
            btnToggleDebugLog.setText("Debug log выключен");
            tvCurrentMethod.setVisibility(View.GONE);
            tvLastValue.setVisibility(View.GONE);
        } else {
            btnToggleDebugLog.setText("Debug log включен");
            tvCurrentMethod.setVisibility(View.VISIBLE);
            tvLastValue.setVisibility(View.VISIBLE);
        }
    }
}