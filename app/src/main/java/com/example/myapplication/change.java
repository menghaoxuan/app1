package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class change extends AppCompatActivity {

    EditText edit1;
    EditText edit2;
    EditText edit3;
    float dollarRate;
    float wonRate;
    float euroRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change);

        edit1 = findViewById(R.id.tex2);
        edit2 = findViewById(R.id.tex3);
        edit3 = findViewById(R.id.tex4);

        SharedPreferences sharedPreferences = getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);

        dollarRate = sharedPreferences.getFloat("dollar_rate",0.1465f);
        euroRate = sharedPreferences.getFloat("euro_rate",0.1259f);
        wonRate = sharedPreferences.getFloat("won_rate",171.7179f);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        double dollarRate = bundle.getDouble("key_dollar",0.1);
//        double euroRate = bundle.getDouble("key_euro",0.1);
//        double wonRate = bundle.getDouble("key_won",0.1);

        edit1.setText(dollarRate + "");
        edit2.setText(euroRate + "");
        edit3.setText(wonRate + "");

    }
    public void change1 (View view){
        Bundle bdl = new Bundle();

        edit1 = (EditText) findViewById(R.id.tex2);
        edit2 = (EditText) findViewById(R.id.tex3);
        edit3 = (EditText) findViewById(R.id.tex4);
        String str1 = edit1.getText().toString();
        String str2 = edit2.getText().toString();
        String str3 = edit3.getText().toString();

        Intent intent = new Intent(this,conversion.class);
        setResult(2,intent);
//        //finish();

        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("dollar_rate",Float.parseFloat(str1));
        editor.putFloat("euro_rate",Float.parseFloat(str2));
        editor.putFloat("won_rate",Float.parseFloat(str3));
        editor.apply();
        finish();

    }


}
