package com.example.supercalories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

    TextView staySameWeightTextView;
    TextView lose1kgTextView;
    TextView loseHalfKgTextView;
    TextView loseQuarterKgTextView;
    TextView gain1kgTextView;
    TextView gainHalfKgTextView;
    TextView gainQuarterKgTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        staySameWeightTextView = findViewById(R.id.BMRCaloriesLabel);
        lose1kgTextView = findViewById(R.id.Lose1kgCaloriesLabel);
        loseHalfKgTextView = findViewById(R.id.LoseHalfCaloriesLabel);
        loseQuarterKgTextView = findViewById(R.id.LoseQuarterCaloriesLabel);
        gain1kgTextView = findViewById(R.id.Gain1kgCaloriesLabel);
        gainHalfKgTextView = findViewById(R.id.GainHalfCaloriesLabel);
        gainQuarterKgTextView = findViewById(R.id.GainQuarterCaloriesLabel);
        Intent passed_Intent = getIntent();
        Bundle passed_Bundle = passed_Intent.getExtras();
        double calories[] = passed_Bundle.getDoubleArray("calories");
        staySameWeightTextView.setText(String.valueOf((int)Math.round(calories[0])));
        lose1kgTextView.setText(String.valueOf((int)Math.round(calories[1])));
        loseHalfKgTextView.setText(String.valueOf((int)Math.round(calories[2])));
        loseQuarterKgTextView.setText(String.valueOf((int)Math.round(calories[3])));
        gainQuarterKgTextView.setText(String.valueOf((int)Math.round(calories[4])));
        gainHalfKgTextView.setText(String.valueOf((int)Math.round(calories[5])));
        gain1kgTextView.setText(String.valueOf((int)Math.round(calories[6])));
    }
}