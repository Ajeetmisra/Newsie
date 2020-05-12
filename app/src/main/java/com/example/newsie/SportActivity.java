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

public class SportActivity extends AppCompatActivity {
    ListView lv;
    NewsDataAdapter sportDataadapter;
    SportActivityViewModel sportActivityViewModel;
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
        Dialog  = new ProgressDialog(SportActivity.this);
        Dialog.setMessage("Fetching Appropriate Feeds.... ");
        Dialog.show();
        sportActivityViewModel = new ViewModelProvider(this).get(SportActivityViewModel.class);

        sportActivityViewModel.getSportNews().observe(this, new Observer<List<News>>() {

            @Override
            public void onChanged(List<News> news) {
                sportDataadapter = new NewsDataAdapter(SportActivity.this,news);
                lv.setAdapter(sportDataadapter);
                Dialog.dismiss();

            }

        });

    }
    public  void refreshfun()
    {        lv = (ListView) findViewById(R.id.list_view);

        sportActivityViewModel = new ViewModelProvider(this).get(SportActivityViewModel.class);

        sportActivityViewModel.getSportNews().observe(this, new Observer<List<News>>() {

            @Override
            public void onChanged(List<News> news) {
                sportDataadapter = new NewsDataAdapter(SportActivity.this,news);
                lv.setAdapter(sportDataadapter);

            }

        });
    }
}
