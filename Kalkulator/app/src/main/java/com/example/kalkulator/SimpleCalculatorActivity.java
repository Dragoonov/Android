package com.example.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleCalculatorActivity extends AppCompatActivity {

    private Processor processor = new Processor();

    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button zeroButton;
    private Button signButton;
    private Button bkspButton;
    private Button clearButton;
    private Button equalButton;
    private Button plusButton;
    private Button minusButton;
    private Button multiButton;
    private Button divideButton;
    private Button dotButton;
    private TextView result;

    private void sendOperationToProcessor(String op) {
        String resultText = result.getText().toString();
        try {
            processor.addValue(Double.valueOf(resultText).toString());
            processor.addValue(op);
            result.setText("");
        }
        catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT).show();
        }
    }

    private void changeSign()
    {
        String resultText = result.getText().toString();
        try {
            if (resultText.startsWith("-"))
                result.setText(resultText.substring(1, result.length()));
            else
                result.setText("-" + result.getText());
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Cannot change sign", Toast.LENGTH_SHORT).show();
        }
    }

    private void getOutcome() {
        String resultText = result.getText().toString();
        try {
            processor.addValue(Double.valueOf(resultText).toString());
            result.setText(Double.toString(processor.compute()));
            resultText = result.getText().toString();
            double a = Double.valueOf(resultText);
            if(a==(int)a)
                result.setText(Integer.toString((int)a));
        }
        catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        oneButton = findViewById(R.id.oneButton);
        twoButton = findViewById(R.id.twoButton);
        threeButton = findViewById(R.id.threeButton);
        fourButton = findViewById(R.id.fourButton);
        fiveButton = findViewById(R.id.fiveButton);
        sixButton = findViewById(R.id.sixButton);
        sevenButton = findViewById(R.id.sevenButton);
        eightButton = findViewById(R.id.eightButton);
        nineButton = findViewById(R.id.nineButton);
        zeroButton = findViewById(R.id.zeroButton);
        signButton = findViewById(R.id.signButton);
        bkspButton = findViewById(R.id.bkspButton);
        clearButton = findViewById(R.id.clearButton);
        equalButton = findViewById(R.id.equalButton);
        plusButton = findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);
        multiButton = findViewById(R.id.multiButton);
        divideButton = findViewById(R.id.divideButton);
        dotButton = findViewById(R.id.dotButton);
        result = findViewById(R.id.result);

        result.setText("");
        oneButton.setOnClickListener(s -> result.setText(result.getText()+"1"));
        twoButton.setOnClickListener(s -> result.setText(result.getText()+"2"));
        threeButton.setOnClickListener(s -> result.setText(result.getText()+"3"));
        fourButton.setOnClickListener(s -> result.setText(result.getText()+"4"));
        fiveButton.setOnClickListener(s -> result.setText(result.getText()+"5"));
        sixButton.setOnClickListener(s -> result.setText(result.getText()+"6"));
        sevenButton.setOnClickListener(s -> result.setText(result.getText()+"7"));
        eightButton.setOnClickListener(s -> result.setText(result.getText()+"8"));
        nineButton.setOnClickListener(s -> result.setText(result.getText()+"9"));
        zeroButton.setOnClickListener(s -> result.setText(result.getText()+"0"));
        signButton.setOnClickListener(s -> changeSign());
        equalButton.setOnClickListener(s -> getOutcome());
        clearButton.setOnClickListener(s -> {processor.clear(); result.setText("");});
        plusButton.setOnClickListener(s -> sendOperationToProcessor("+"));
        minusButton.setOnClickListener(s -> sendOperationToProcessor("-"));
        multiButton.setOnClickListener(s -> sendOperationToProcessor("*"));
        divideButton.setOnClickListener(s -> sendOperationToProcessor("/"));
        dotButton.setOnClickListener(s -> result.setText(result.getText()+"."));
        bkspButton.setOnClickListener(s -> {
            if (result.getText().length() == 0)
                processor.clear();
            else
                result.setText(result.getText().subSequence(0,result.length()-1));
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("key_name", result.getText().toString());
        savedInstanceState.putStringArrayList("key_lista",processor.values);
        savedInstanceState.putDouble("key_double",processor.powerValue);
        savedInstanceState.putBoolean("key_booblean",processor.isPower);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        {
            processor.values =  savedInstanceState.getStringArrayList("key_lista");
            processor.isPower = savedInstanceState.getBoolean("key_booblean");
            processor.powerValue = savedInstanceState.getDouble("key_double");
            result.setText("" + savedInstanceState.getString("key_name"));

        }
    }
}
