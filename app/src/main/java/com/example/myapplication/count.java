package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class count extends AppCompatActivity {
    TextView out;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count);


    }

    public void score(View view){
        out = (TextView) findViewById(R.id.textView8);
        int outScore = Integer.parseInt(out.getText().toString().split("分")[0]);
        int putScore = Integer.parseInt(view.getTag().toString());
        out.setText((outScore+putScore)+"分");

    }


    public void reset(View view){
        out = (TextView) findViewById(R.id.textView8);
        out.setText("0分");
    }
}
