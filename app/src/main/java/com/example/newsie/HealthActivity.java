package com.example.newsie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

public class HealthActivity extends AppCompatActivity {
    ListView lv;
    NewsDataAdapter healthDataadapter;
    HealthActivityViewModel healthActivityViewModel;
    ProgressDialog Dialog;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){

            case R.id.refresh1:
                refreshfun();
                return true;
            default:
                return false;
        }


    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.list_view);
        Dialog  = new ProgressDialog(HealthActivity.this);
        Dialog.setMessage("Fetching Appropriate Feeds.... ");
        Dialog.show();
        healthActivityViewModel = new ViewModelProvider(this).get(HealthActivityViewModel.class);

        healthActivityViewModel.getHealthNews().observe(this, new Observer<List<News>>() {

            @Override
            public void onChanged(List<News> news) {
                healthDataadapter = new NewsDataAdapter(HealthActivity.this,news);
                lv.setAdapter(healthDataadapter);
                Dialog.dismiss();

            }

        });

    }

    public  void refreshfun()
    {        lv = (ListView) findViewById(R.id.list_view);

        healthActivityViewModel = new ViewModelProvider(this).get(HealthActivityViewModel.class);

        healthActivityViewModel.getHealthNews().observe(this, new Observer<List<News>>() {

            @Override
            public void onChanged(List<News> news) {
                healthDataadapter = new NewsDataAdapter(HealthActivity.this,news);
                lv.setAdapter(healthDataadapter);

            }

        });
    }
}
