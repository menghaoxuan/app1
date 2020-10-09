package com.example.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class list<handler> extends ListActivity implements Runnable{
    private static final String TAG = "conversion";

    int length = 3;
    public List<String> getRate2(){
        String url = "https://www.usd-cny.com/bankofchina.htm";
        Document doc = null;
        List<String> list = new ArrayList<>();
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
                list.add(str1+"==>"+val);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list);

                handler = new  Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 5) {


                        List<String> str1 = (List<String>) msg.obj;
                        ListAdapter adapter = new ArrayAdapter<String>(
                                list.this,
                                android.R.layout.simple_list_item_1,
                                str1);
                        setListAdapter(adapter);
                    }
                    super.handleMessage(msg);
                }
            };
        Thread t = new Thread(this);
        t.start();
    }





    @Override
    public void run() {
        Log.i(TAG, "run: run()……");
        // 获取网络数据
        URL url = null;
        ArrayList<String> arrayList;
        List<String> list = getRate2();
        Message msg = handler.obtainMessage(5);
        //msg.what = 5;
        msg.obj = list;
        handler.sendMessage(msg);
        handler.postDelayed(this,86400000);//定时时间
    }
}