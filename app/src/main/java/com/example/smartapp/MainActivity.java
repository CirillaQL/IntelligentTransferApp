package com.example.smartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button Login,Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = (Button) this.findViewById(R.id.button);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection conn = null;
                        try {
                            String Strurl = "http://10.0.2.2:40000/login";
                            URL url = new URL(Strurl);
                            Log.d("dsad",Strurl);
                            conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                            conn.setRequestProperty("Accept","application/json");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);
                            conn.setConnectTimeout(5000);
                            conn.setRequestMethod("POST");
                            conn.connect();

                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("userinfo", "1194946223@qq.com");
                            jsonParam.put("password", "ql1194946223");
                            jsonParam.put("loginType",2);

                            Log.i("JSON", jsonParam.toString());
                            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                            os.writeBytes(jsonParam.toString());

                            os.flush();
                            os.close();

                            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                            Log.i("MSG" , conn.getResponseMessage());

                            conn.disconnect();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}

class NetworkConnectTest implements Runnable{

    @Override
    public void run() {

    }
}