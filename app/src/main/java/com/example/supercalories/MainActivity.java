package com.example.supercalories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import java.text.Normalizer;

public class MainActivity extends AppCompatActivity {

    CaloriesHelper helper;
    RadioGroup gender_RadioGroup;
    RadioGroup formula_RadioGroup;
    RadioButton genderSelectedBtn;
    RadioButton formulaSelectedBtn;
    EditText weightEditText;
    EditText heightEditText;
    EditText ageEditText;
    EditText fatPercentageEditText;
    double weightStart =0, heightStart =0;
    int ageStart =0, fatPercentageStart =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new CaloriesHelper();

        gender_RadioGroup = (RadioGroup)findViewById(R.id.gender_RadioGroup);
        formula_RadioGroup = (RadioGroup)findViewById(R.id.formula_RadioGroup);
        weightEditText = findViewById(R.id.weightValue);
        heightEditText = findViewById(R.id.heightValue);
        ageEditText = findViewById(R.id.ageValue);
        fatPercentageEditText = findViewById(R.id.fatPercentageValue);

        int genderSelectedId = gender_RadioGroup.getCheckedRadioButtonId();
        genderSelectedBtn = findViewById(genderSelectedId);
        int formulaSelectedId = formula_RadioGroup.getCheckedRadioButtonId();
        formulaSelectedBtn = findViewById(formulaSelectedId);

        gender_RadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                genderSelectedBtn = findViewById(checkedId);
                if(genderSelectedBtn.getText().toString().equals("male"))
                    helper.gender = true;
                else if(genderSelectedBtn.getText().toString().equals("female"))
                {
                    helper.gender = false;
                }
            }
        });
        formula_RadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                formulaSelectedBtn = findViewById(checkedId);
                if(formulaSelectedBtn.getText().toString().equals("Mifflin-St Jeor Equation"))
                {
                    helper.formula = Formulas.Mifflin_St_Jeor_Equation;
                }
                else if(formulaSelectedBtn.getText().toString().equals("Harris-Benedict Equation"))
                {
                    helper.formula = Formulas.Harris_Benedict_Equation;
                }
                else if(formulaSelectedBtn.getText().toString().equals("Katch-McArdle Formula"))
                {
                    helper.formula = Formulas.Katch_McArdle_Formula;
                }
            }
        });
    }

    public  void CalculateBMR(View view)
    {
        weightStart = Double.parseDouble(weightEditText.getText().toString());
        heightStart = Double.parseDouble(heightEditText.getText().toString());
        ageStart = Integer.parseInt(ageEditText.getText().toString());
        fatPercentageStart = Integer.parseInt(fatPercentageEditText.getText().toString());
        helper.weight = weightStart;
        helper.height = heightStart;
        helper.age = ageStart;
        helper.fatPercentage = fatPercentageStart;
        if (helper.formula != null && helper.age != 0 && helper.height != 0 && helper.weight != 0)
        {
            double BMRCalculated = helper.CalculateBMR(helper);
            helper.BMR = BMRCalculated;
        }
        Intent calculatedBMR_Intent = new Intent(this, CalculatedBMR.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("BMR", helper.BMR);
        bundle.putDouble("weight", helper.weight);
        bundle.putDouble("height", helper.height);
        bundle.putInt("age", helper.age);
        bundle.putInt("fatPercentage", helper.fatPercentage);
        bundle.putBoolean("gender", helper.gender);
        bundle.putString("formulaString", helper.formula.toString());
        calculatedBMR_Intent.putExtras(bundle);
        startActivity(calculatedBMR_Intent);
    }


}