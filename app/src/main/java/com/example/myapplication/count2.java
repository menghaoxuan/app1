package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class count2 extends AppCompatActivity {
    TextView out1;
    TextView out2;
    EditText edit;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count2);

    }

    public void score1(View view){
        out1 = (TextView) findViewById(R.id.textView15);
        int outScore = Integer.parseInt(out1.getText().toString().split("分")[0]);
        int putScore = Integer.parseInt(view.getTag().toString());
        out1.setText((outScore+putScore)+"分");

    }
    public void score2(View view){
        out2 = (TextView) findViewById(R.id.textView16);
        int outScore = Integer.parseInt(out2.getText().toString().split("分")[0]);
        int putScore = Integer.parseInt(view.getTag().toString());
        out2.setText((outScore+putScore)+"分");

    }


    public void reset2(View view){
        out1 = (TextView) findViewById(R.id.textView15);
        out1.setText("0分");
        out2 = (TextView) findViewById(R.id.textView16);
        out2.setText("0分");

    }
}