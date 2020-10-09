package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.BundleCompat;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.templates.TemperatureControlTemplate;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public  class MainActivity extends AppCompatActivity {

    @Override
    protected void  onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void jTemperature (View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, temperature.class);
        startActivity(intent);
    }

    public void jCount (View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, count.class);
        startActivity(intent);
    }

    public void jCount2 (View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, count2.class);
        startActivity(intent);
    }

    public void jConversion (View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, conversion.class);
        startActivity(intent);
    }


}