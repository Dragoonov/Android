package com.example.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

interface MyFunction<T>
{
    T apply(T c);
}

public class AdvancedCalculatorActivity extends AppCompatActivity {


    private Processor processor = new Processor();
    private boolean procFlag = false;

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
    private Button powerButton;
    private Button squareButton;
    private Button logButton;
    private Button lnButton;
    private Button sinButton;
    private Button cosButton;
    private Button tanButton;
    private Button sqrtButton;
    private Button procButton;
    private TextView result;

    private void changeSign() {
        try {
            if (result.getText().charAt(0) == '-')
                result.setText(result.getText().subSequence(1, result.length()));
            else
                result.setText("-" + result.getText());
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Cannot change sign", Toast.LENGTH_SHORT).show();
        }
    }

    private void getOutcome() {
        try {
            if(processor.isPower()==true) {
                processor.addValue(Double.toString(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString()))));
                processor.setPower(false);
                processor.setPowerValue(0);
            }
            else
                processor.addValue(Double.valueOf(result.getText().toString()).toString());
            result.setText(Double.toString(processor.compute()));
        }
        catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendOperationToProcessor(String op){
        try {
            if(result.getText().charAt(result.length()-1)=='%')
                result.setText(Double.valueOf(Double.valueOf(result.getText().subSequence(0,result.getText().length()-1).toString())/100.0).toString());
            if(processor.isPower()==true) {
                processor.addValue(Double.toString(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString()))));
                processor.setPower(false);
                processor.setPowerValue(0);
            }
            else
                processor.addValue(Double.valueOf(result.getText().toString()).toString());
            processor.addValue(op);
            result.setText("");
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT).show();
        }
    }

    private void setPower()
    {
        try {
            processor.setPowerValue(Double.valueOf(result.getText().toString()));
            processor.setPower(true);
            result.setText("");
        }
        catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT).show();
        }
    }

    private void performOperation(String op) {
        try {
            if(result.getText().charAt(result.length()-1)=='%')
                result.setText(Double.valueOf(Double.valueOf(result.getText().subSequence(0,result.getText().length()-1).toString())/100.0).toString());
            if(processor.isPower()==true) {
                switch(op)
                {
                    case "square": result.setText(Double.toString(Math.pow(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString())),2))); break;
                    case "log": result.setText(Double.toString(Math.log10(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString()))))); break;
                    case "ln": result.setText(Double.toString(Math.log(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString()))))); break;
                    case "sqrt": result.setText(Double.toString(Math.sqrt(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString()))))); break;
                    case "sin":result.setText(Double.toString(Math.sin(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString()))))); break;
                    case "cos":result.setText(Double.toString(Math.cos(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString()))))); break;
                    case "tan":result.setText(Double.toString(Math.tan(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString()))))); break;
                    case "proc":result.setText(Double.toString(100*(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString()))))+"%"); break;
                }
            }
            else {
                switch(op)
                {
                    case "square": result.setText(Double.toString(Math.pow(Double.valueOf(result.getText().toString()),2))); break;
                    case "log": result.setText(Double.toString(Math.log10(Double.valueOf(result.getText().toString())))); break;
                    case "ln": result.setText(Double.toString(Math.log(Double.valueOf(result.getText().toString())))); break;
                    case "sqrt": result.setText(Double.toString(Math.sqrt(Double.valueOf(result.getText().toString())))); break;
                    case "sin":result.setText(Double.toString(Math.sin(Double.valueOf(result.getText().toString())))); break;
                    case "cos":result.setText(Double.toString(Math.cos(Double.valueOf(result.getText().toString())))); break;
                    case "tan":result.setText(Double.toString(Math.tan(Double.valueOf(result.getText().toString())))); break;
                    case "proc":result.setText(Double.toString(100*(Double.valueOf(result.getText().toString())))+"%"); break;
                }
            }
            processor.setPower(false);
            processor.setPowerValue(0);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT).show();
        }
    }

    private void performOperation2(MyFunction<Double> fun) {
        try {
            if(result.getText().charAt(result.length()-1)=='%')
                result.setText(Double.valueOf(Double.valueOf(result.getText().subSequence(0,result.getText().length()-1).toString())/100.0).toString());
            if(processor.isPower())
                result.setText(Double.toString(Math.pow(processor.getPowerValue(),Double.valueOf(result.getText().toString()))));
            result.setText(Double.toString(fun.apply(Double.valueOf(result.getText().toString()))));
            result.setText(result.getText()+(procFlag?"%":""));
            procFlag=false;
            processor.setPower(false);
            processor.setPowerValue(0);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);
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
        powerButton = findViewById(R.id.powerButton);
        squareButton = findViewById(R.id.squareButton);
        logButton = findViewById(R.id.logButton);
        lnButton = findViewById(R.id.lnButton);
        sinButton = findViewById(R.id.sinButton);
        cosButton = findViewById(R.id.cosButton);
        tanButton = findViewById(R.id.tanButton);
        sqrtButton = findViewById(R.id.sqrtButton);
        dotButton = findViewById(R.id.dotButton);
        procButton = findViewById(R.id.procButton);
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
        clearButton.setOnClickListener(s -> {processor.clear(); result.setText("");});
        equalButton.setOnClickListener(s -> getOutcome());
        plusButton.setOnClickListener(s -> sendOperationToProcessor("+"));
        minusButton.setOnClickListener(s -> sendOperationToProcessor("-"));
        multiButton.setOnClickListener(s -> sendOperationToProcessor("*"));
        divideButton.setOnClickListener(s -> sendOperationToProcessor("/"));
        dotButton.setOnClickListener(s -> result.setText(result.getText()+"."));
        powerButton.setOnClickListener(s -> setPower());


        squareButton.setOnClickListener(s -> performOperation2(a->Math.pow(a,2)));
        logButton.setOnClickListener(s -> performOperation2(Math::log10));
        lnButton.setOnClickListener(s -> performOperation2(Math::log));
        sqrtButton.setOnClickListener(s -> performOperation2(Math::sqrt));
        sinButton.setOnClickListener(s -> performOperation2(Math::sin));
        cosButton.setOnClickListener(s -> performOperation2(Math::cos));
        tanButton.setOnClickListener(s -> performOperation2(Math::tan));
        procButton.setOnClickListener(s -> {
                                            procFlag = true;
                                            performOperation2(a->100*a);
                                            });

       /* squareButton.setOnClickListener(s -> performOperation("square"));
        logButton.setOnClickListener(s -> performOperation("log"));
        lnButton.setOnClickListener(s -> performOperation("ln"));
        sqrtButton.setOnClickListener(s -> performOperation("sqrt"));
        sinButton.setOnClickListener(s -> performOperation("sin"));
        cosButton.setOnClickListener(s -> performOperation("cos"));
        tanButton.setOnClickListener(s -> performOperation("tan"));
        procButton.setOnClickListener(s -> performOperation("proc"));*/

        bkspButton.setOnClickListener(s -> {
            if (result.getText().length() == 0)
                processor.clear();
            else
                result.setText(result.getText().subSequence(0,result.length()-1));
        });
    }
}
