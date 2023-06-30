package com.example.supercalories;

enum  Formulas {
    Mifflin_St_Jeor_Equation,
    Harris_Benedict_Equation,
    Katch_McArdle_Formula
}

enum  ActivityLevel{
    Sedentary,
    Light,
    Moderate,
    Active,
    Very_Active
}



public class CaloriesHelper {

    double SEDENTARY_MULTIPLIER = 1.2;
    double LIGHT_MULTIPLIER = 1.375;
    double MODERATE_MULTIPLIER = 1.55;
    double ACTIVE_MULTIPLIER = 1.725;
    double VERY_ACTIVE_MULTIPLIER = 1.9;

    public double BMR;
    public  double weight;
    public  double height;
    public  int age;
    public  int fatPercentage;
    public  boolean gender; // true = male ; false = female
    public  Formulas formula;
    public  ActivityLevel activity_Level;

    public  CaloriesHelper()
    {}

    public double CalculateBMR(CaloriesHelper helper)
    {
        double BMR = 0;

        if(helper.formula == Formulas.Mifflin_St_Jeor_Equation)
        {
            BMR = MifflinCalculate(helper.weight, helper.height, helper.age, helper.gender);
        }
        else if(helper.formula == Formulas.Harris_Benedict_Equation)
        {
            BMR = HarrisCalculate(helper.weight,helper.height,helper.age,helper.gender);
        }
        else if(helper.formula == Formulas.Katch_McArdle_Formula)
        {
            BMR = KatchCalculate(helper.weight,helper.fatPercentage);
        }
        return  (double) Math.round(BMR * 100) / 100;
    }

    private  double MifflinCalculate(double weight, double height, int age, boolean gender)
    {
        if(gender)
        {
            return  (10*weight + 6.25*height - 5*age +5);
        }
        else
        {
            return  (10*weight + 6.25*height - 5*age - 161);
        }
    }

    private double HarrisCalculate(double weight, double height, int age, boolean gender)
    {
        if(gender)
        {
            return  (13.397*weight + 4.799*height - 5.677*age + 88.362);
        }
        else
        {
            return ( 9.247*weight + 3.098*height - 4.330*age + 447.593);
        }
    }

    private  double KatchCalculate(double weight, int fatPercentage)
    {
        return  (370 + 21.6*(1 - (fatPercentage/100))*weight);
    }

    public  double[] calculateWithActivity(ActivityLevel activity_Level, double BMR) // TODO : calculate the needded calories for different cases based on the activity level and return as array
    {
        double activityMultiplier;
        switch (activity_Level)
        {
            case Sedentary: activityMultiplier = SEDENTARY_MULTIPLIER;
            break;
            case Moderate: activityMultiplier = MODERATE_MULTIPLIER;
            break;
            case Light: activityMultiplier = LIGHT_MULTIPLIER;
            break;
            case Active: activityMultiplier = ACTIVE_MULTIPLIER;
            break;
            case Very_Active: activityMultiplier = VERY_ACTIVE_MULTIPLIER;
            break;
            default:
                activityMultiplier = 1.0;
        }
        double caloriesIntakeWithActivity = BMR * activityMultiplier;
        double[] calories = new double[7];
        calories[0] = caloriesIntakeWithActivity; // staying the same weight
        double percentage1kg = (900/BMR);
        calories[1] = caloriesIntakeWithActivity - caloriesIntakeWithActivity*percentage1kg;// lose 1kg per week
        double percentageHalfKg = 525/BMR;
        calories[2] = caloriesIntakeWithActivity - caloriesIntakeWithActivity*percentageHalfKg;// lose 0.5kg per week
        double percentageQuarterKg = 275/BMR;
        calories[3] = caloriesIntakeWithActivity - caloriesIntakeWithActivity*percentageQuarterKg;// lose 0.25kg per week
        calories[4] = caloriesIntakeWithActivity + caloriesIntakeWithActivity*275/BMR;// gain 0.25kg per week
        calories[5] = caloriesIntakeWithActivity + caloriesIntakeWithActivity*550/BMR;//gain 0.5kg per week
        calories[6] = caloriesIntakeWithActivity + caloriesIntakeWithActivity*1100/BMR;// gain 1kg per week
        return  calories;
    }
}
