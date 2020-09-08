package com.example.newsie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
  ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView) findViewById(R.id.imageView3);
        Thread th = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // write your activity which you want to open

                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        };
        th.start();
    }
}
