package com.example.supercalories;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatedBMR extends AppCompatActivity {

    CaloriesHelper helper;
    TextView caloriesTextView;
    RadioGroup activity_RadioGroup;
    RadioButton activitySelectedBtn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated_bmr);
        helper = new CaloriesHelper();
        Intent passed_Intent = getIntent();
        Bundle passed_Bundle = passed_Intent.getExtras();
        helper.BMR = passed_Bundle.getDouble("BMR");
        helper.weight = passed_Bundle.getDouble("weight");
        helper.height = passed_Bundle.getDouble("height");
        helper.age = passed_Bundle.getInt("age");
        helper.fatPercentage = passed_Bundle.getInt("fatPercentage");
        helper.gender = passed_Bundle.getBoolean("gender");
        helper.formula = Formulas.valueOf(passed_Bundle.getString("formulaString"));

        caloriesTextView = findViewById(R.id.caloriesTextView);
        caloriesTextView.setText(helper.BMR +" kcal");

        activity_RadioGroup = findViewById(R.id.activityRadioGroup);
        activity_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                activitySelectedBtn = findViewById(checkedId);
                if(activitySelectedBtn.getText().toString().equals("SEDENTARY(little or no exercise)"))
                {
                    helper.activity_Level = ActivityLevel.Sedentary;
                }
                else if(activitySelectedBtn.getText().toString().equals("LIGHT(exercise 1–3 times/week)"))
                {
                    helper.activity_Level = ActivityLevel.Light;
                }
                else if(activitySelectedBtn.getText().toString().equals("MODERATE(exercise 4–5 times/week)"))
                {
                    helper.activity_Level = ActivityLevel.Moderate;
                }
                else if(activitySelectedBtn.getText().toString().equals("ACTIVE(exercise daily or intense training 3–4 times/week)"))
                {
                    helper.activity_Level = ActivityLevel.Active;
                }
                else if(activitySelectedBtn.getText().toString().equals("VERY ACTIVE(intense training 6–7 times/week)"))
                {
                    helper.activity_Level = ActivityLevel.Very_Active;
                }
            }
        });
    }

    public  void  calculateDifferentCases(View view)
    {
        double[] calories = helper.calculateWithActivity(helper.activity_Level, helper.BMR);
        Intent options_Intent = new Intent(this, OptionsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDoubleArray("calories", calories);
        options_Intent.putExtras(bundle);
        startActivity(options_Intent);
    }
}