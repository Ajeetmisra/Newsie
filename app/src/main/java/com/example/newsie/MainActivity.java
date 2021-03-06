package com.example.newsie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    NewsDataAdapter newsDataAdapter;
    MainActivitityViewModel mainActivitityViewModel;
    ProgressDialog Dialog;
    TabLayout tabLayout;
    ViewPager viewPager;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.business:
                Intent intent0 = new Intent(this,BusinessActivity.class);
                startActivity(intent0);
                return true;
            case R.id.entertainment:
                Intent intent5 = new Intent(this,EntertainmentActivity.class);
                startActivity(intent5);
                return true;
            case R.id.health:
                Intent intent1 = new Intent(this,HealthActivity.class);
                startActivity(intent1);
                return true;
            case R.id.science:
                Intent intent2 = new Intent(this,ScienceActivity.class);
                startActivity(intent2);
                return true;
            case R.id.sport:
                Intent intent3 = new Intent(this,SportActivity.class);
                startActivity(intent3);
                return true;
            case R.id.technology:
                Intent intent4 = new Intent(this,TechnologyActivity.class);
                startActivity(intent4);
                return true;
            case R.id.refresh:
            refreshFeedFunction();

            default:
                return false;

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);
//        toolbar =(Toolbar) findViewById(R.id.appbarlayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),1);
        adapter.AddFragments(new Fragment_latest_news_activity(),"Latest");
        adapter.AddFragments(new Fragment_business_news_activity(),"Business");
        adapter.AddFragments(new Fragment_Health_news_activity(),"Health");
        adapter.AddFragments(new Fragment_Entertainment_news_activity(),"Entertainment");
        adapter.AddFragments(new Fragment_Science_news_activity(),"Science");
        adapter.AddFragments(new Fragment_Sport_news_activity(),"Sport");
        adapter.AddFragments(new Fragment_Technology_news_activity(),"Technology");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


//        ImageView imageView = findViewById(R.id.imageView);
//        String imageUrl = "https://img.etimg.com/thumb/msid-75644621,width-1070,height-580,imgsize-116609,overlay-etmarkets/photo.jpg";
//        Picasso.get().load(imageUrl).into(imageView);
          lv = (ListView) findViewById(R.id.list_view);
//        Dialog  = new ProgressDialog(MainActivity.this);
//        Dialog.setMessage("Fetching Appropriate Feeds.... ");
//        Dialog.show();
        mainActivitityViewModel = new ViewModelProvider(this).get(MainActivitityViewModel.class);

        mainActivitityViewModel.getStates().observe(this, new Observer<List<News>>() {

            @Override
            public void onChanged(List<News> news) {
            newsDataAdapter = new NewsDataAdapter(MainActivity.this,news);
            lv.setAdapter(newsDataAdapter);
//                Dialog.dismiss();

            }

        });
    }
 public void refreshFeedFunction()
 { mainActivitityViewModel = new ViewModelProvider(this).get(MainActivitityViewModel.class);

     mainActivitityViewModel.getStates().observe(this, new Observer<List<News>>() {

         @Override
         public void onChanged(List<News> news) {
             newsDataAdapter = new NewsDataAdapter(MainActivity.this,news);
             lv.setAdapter(newsDataAdapter);

         }

     });


 }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("aj", "onStart: ");
        refreshFeedFunction();
    }

}

