package com.example.dogs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL="https://dog.ceo/api/breeds/image/random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDogImage();
    }
    private void loadDogImage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(BASE_URL);//создаем адрес для отправки запроса
                    HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();//открываем соединение и сохраняем его
                    InputStream inputStream=urlConnection.getInputStream();//чтобы считывать данные с сервера(считывает по байтам)
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//чтобы считывать по символам
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//чтобы считывать единой строкой
                    String result=bufferedReader.readLine();
                    Log.d("MainActivity", result);
                } catch (Exception e) {
                    Log.d("MainActivity", e.toString());
                }
            }
        }).start();
    }
}