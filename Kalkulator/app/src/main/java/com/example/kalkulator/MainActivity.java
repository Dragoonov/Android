package com.example.kalkulator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button simpleButton;
    private Button advancedButton;
    private Button aboutButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleButton = findViewById(R.id.button);
        advancedButton = findViewById(R.id.button2);
        aboutButton = findViewById(R.id.button3);
        exitButton = findViewById(R.id.button4);

        simpleButton.setOnClickListener(s ->startActivity(new Intent(MainActivity.this, SimpleCalculatorActivity.class)));
        advancedButton.setOnClickListener(s ->startActivity(new Intent(MainActivity.this, AdvancedCalculatorActivity.class)));
        aboutButton.setOnClickListener(s -> startActivity(new Intent(MainActivity.this, AboutActivity.class)));
        exitButton.setOnClickListener(s -> finish());
    }
}
