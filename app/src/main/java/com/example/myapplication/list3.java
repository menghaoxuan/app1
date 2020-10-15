package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class list3 extends AppCompatActivity {
    TextView textView1,textView2;
    EditText editText;
    String name;
    Double rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list3);

        textView1 = findViewById(R.id.rate_name);
        textView2 = findViewById(R.id.rate_out);
        editText = findViewById(R.id.rate_in);

        SharedPreferences sharedPreferences = getSharedPreferences("currency", Activity.MODE_PRIVATE);
        name = sharedPreferences.getString("currency","");
        rate = Double.parseDouble(sharedPreferences.getString("rate",""));

        System.out.println(rate+"");


        //清空currency.xml中的数据
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        rate = 1 /(rate/100);
        textView1.setText(name);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Double num = 0.0;

                try{
                    num = Double.parseDouble(s.toString());
                }catch (Exception e){
                    textView2.setText("请输入正确的金额");
                    return;
                }
                textView2.setText(num * rate + "");
            }

        });

    }
}