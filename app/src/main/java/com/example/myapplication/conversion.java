package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;

public class conversion extends AppCompatActivity implements Runnable{
    private static final String TAG = "conversion";

    TextView out;
    EditText edit;
    double dollar;
    double euro;
    double won;
    float dollarRate;
    float euroRate;
    float wonRate;

    private SharedPreferences sp ;


    int length = 3;
    public double[] getRate2(){
        double rate[] = new double[length];
        String url = "https://www.usd-cny.com/bankofchina.htm";
        Document doc = null;
        try {
            doc = (Document) Jsoup.connect(url).get();
        Log.i(TAG, "getRate2: "+doc.title());
        Elements tables = doc.getElementsByTag("table");
        Element table = tables.get(0);//因为本网页只有一个table，等价于.first()
        Elements tds = table.getElementsByTag("td");//从table中找<td>，即列
        for (int i = 0;i < tds.size();i+=6){
            Element td1 = tds.get(i);
            Element td2 = tds.get(i+5);
            String str1 = td1.text();
            String val = td2.text();
            Log.i(TAG, "getRate2: "+str1+"==>"+val);
            if(str1.equals("欧元")){
                rate[0] = 100d / Double.parseDouble(val);
                Log.i(TAG, "getRate2: 欧元"+rate[1]);
            }else if(str1.equals("韩元")){
                rate[1] = 100d / Double.parseDouble(val);
                Log.i(TAG, "getRate2: 韩元"+rate[2]);
            }else if(str1.equals("美元")){
                rate[2] = 100d / Double.parseDouble(val);
                Log.i(TAG, "getRate2: 美元"+rate[0]);
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion);
        Thread t = new Thread(this);
        t.start();
        Handler handler;
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 5) {
                    String str = (String) msg.obj;
                    Log.i(TAG, "handleMessage: getMessage msg = " + str);
//                    show.setText(str);
                }
                super.handleMessage(msg);
            }
        };
//        获取Msg对象，用于返回主线程
        Message msg = handler.obtainMessage(5);
//        msg.what = 5;
        msg.obj = "Hello from run()";
        handler.sendMessage(msg);
    }




    @Override
    public void run() {
        Log.i(TAG, "run: run()……");
        // 获取网络数据
        URL url = null;
        ArrayList<String> arrayList;
        double rate[] = getRate2();
        dollarRate = (float) rate[0];
        euroRate = (float) rate[1];
        wonRate = (float) rate[2];
        Log.i(TAG, "run: dollar_rateByJsoup="+dollarRate+"\n");
        Log.i(TAG, "run: euro_rateByJsoup="+euroRate+"\n");
        Log.i(TAG, "run: won_rateByJsoup="+wonRate+"\n");
    }

    public void conver1(View button){
        edit = (EditText)findViewById(R.id.tex1);
        if(TextUtils.isEmpty(edit.getText().toString())){//如果没有输入(用PPT上的方法不对)
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
        }else {
            TextView out = (TextView)findViewById(R.id.textView9);
            double rmb = Double.parseDouble(edit.getText().toString());
            if(button.getId()==R.id.button32){
                double dollar = dollarRate * rmb;
                out.setText(Double.toString(dollar));
            }else if(button.getId()==R.id.button33){
                double euro = euroRate * rmb;
                out.setText(Double.toString(euro));
            }else if(button.getId()==R.id.button34){
                double won = wonRate * rmb;
                out.setText(Double.toString(won));
            }
        }


    }
    public void conver2 (View view){
//        Intent config = new Intent();
//        config.setClass(ExchangeRate.this, ExchangeRate2.class);
        Intent config = new Intent();
        config.setClass(conversion.this, change.class);
        config.putExtra("dollar_rate_key",dollarRate);
        config.putExtra("euro_rate_key",euroRate);
        config.putExtra("won_rate_key",wonRate);
        Log.i(TAG, "jumpExchangeRate2: dollar_rate=" + dollarRate);
        Log.i(TAG, "jumpExchangeRate2: euro_rate=" + euroRate);
        Log.i(TAG, "jumpExchangeRate2: won_rate=" + wonRate);
        startActivityForResult(config,1);
    }
    //    自动运行，获取从exchange_rate2传回的数据

    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==1 && resultCode==2){

            //使用myrate.XML中汇率看是否成功存入、读取
            SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            dollarRate = sharedPreferences.getFloat("dollar_rate",0.0f);
            euroRate = sharedPreferences.getFloat("euro_rate",0.0f);
            wonRate = sharedPreferences.getFloat("won_rate",0.0f);
//            //清除当前文件中数据
//            sharedPreferences.edit().clear().commit();
            Log.i(TAG, "onActivityResult: dollar_rate2=" + dollarRate);
            Log.i(TAG, "onActivityResult: euro_rate2=" + euroRate);
            Log.i(TAG, "onActivityResult: won_rate2=" + wonRate);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
