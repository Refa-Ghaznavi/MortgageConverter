package com.example.mortgageconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    EditText etMoney, etInterest, etYears;
    TextView etResult;
    RelativeLayout reConvert;

    final byte MONTHS_IN_YEAR = 12;
    final byte PERCENT = 100;
    double mortgage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMoney = findViewById(R.id.et_money);
        etInterest = findViewById(R.id.et_interest);
        etYears = findViewById(R.id.et_years);
        etResult = findViewById(R.id.et_result);

        reConvert = findViewById(R.id.reConvert);

        reConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //the clickToShow is coded without validation
                if(validationSuccess()){
                    clickToShow();
                    // the clickToShowTwo is coded with validation which is not working
                    // clickToShowTwo();
                }

            }
        });
    }

    public void clickToShow() {
        // converting the Value to String
        String valueOfMoney = etMoney.getText().toString();
        int principal = Integer.parseInt(valueOfMoney);

        String valueOfInterest = etInterest.getText().toString();
        double annualInterest = Double.parseDouble(valueOfInterest);
        double monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;

        String valueOfYears = etYears.getText().toString();
        int years = Integer.parseInt(valueOfYears);
        int numberOfPayments = years * MONTHS_IN_YEAR;

        mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);

        etResult.setText(mortgageFormatted);
    }

    /*validation of reConvert*/
    private boolean validationSuccess() {
        if(etMoney.getText().toString().equalsIgnoreCase(""))
        {
            LayoutInflater layoutInflater = getLayoutInflater();
            View layout = layoutInflater.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast_id));

            TextView textView = (TextView) layout.findViewById(R.id.toast_mes);
            textView.setText("Please Add Amount of Money....");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM,0,10);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            return false;
        }
        else if(etInterest.getText().toString().equalsIgnoreCase(""))
        {
            LayoutInflater layoutInflater = getLayoutInflater();
            View layout = layoutInflater.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast_id));

            TextView textView = (TextView) layout.findViewById(R.id.toast_mes);
            textView.setText("Please Add amount of Interest...");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM,0,10);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            return false;
        }
        else if(etYears.getText().toString().equalsIgnoreCase(""))
        {
            LayoutInflater layoutInflater = getLayoutInflater();
            View layout = layoutInflater.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast_id));

            TextView textView = (TextView) layout.findViewById(R.id.toast_mes);
            textView.setText("Please Add Number of Years...");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM,0,10);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            return false;
        }
        return true;
    }
}