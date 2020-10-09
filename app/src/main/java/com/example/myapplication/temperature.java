package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class temperature extends AppCompatActivity {
    TextView out;
    EditText edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);
        TextView tv = findViewById(R.id.textView6);
        tv.setText("温度转化器");
        EditText input = findViewById(R.id.editTextTextPersonName2);
        String str = input.getText().toString();
        out = (TextView) findViewById(R.id.textView5);
        edit = (EditText) findViewById(R.id.editTextTextPersonName2);
    }


    public void abc(View v) {
        String str = edit.getText().toString();

        if(str.charAt(str.length()-1) =='C') {
            Double d = Double.parseDouble(str.substring(0, str.length() - 1));
            out.setText("结果为：" + (d * 9 / 5 + 32)+ "F");
        }
        else if(str.charAt(str.length()-1) =='F') {
            Double d = Double.parseDouble(str.substring(0, str.length() - 1));
            out.setText("结果为：" + (d-32)*5/9 + "C");
        }
        else{
            out.setText("输入有误，请重新输入");
        }

    }
}
