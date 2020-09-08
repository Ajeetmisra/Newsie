package com.example.newsie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_latest_news_activity extends Fragment {
    public Fragment_latest_news_activity() {
    }
    ListView lv;
    TextView textView;
    private NewsDataAdapter dataAdapter;
    private MainActivitityViewModel myViewModel;
    ArrayList<News> info;
    ArrayList<News> info2;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View rootview = inflater.inflate(R.layout.latestnews_fragment,container,false);
        lv = (ListView)rootview.findViewById(R.id.list_view1);

         return rootview;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }
    // onViewCreated make sure that your view has fully created or not
    // we can implement view model pattern in oncreateview but it crashes because sometimes
    // the view is not properly created
    // so its good to have view model in oncreatedview
        @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myViewModel = new ViewModelProvider(requireActivity()).get(MainActivitityViewModel.class);

        myViewModel.getStates().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                // update UI
                dataAdapter = new NewsDataAdapter(getActivity(),news);

                lv.setAdapter( dataAdapter);
//                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        News currentNewsItem = (News) parent.getAdapter().getItem(position);
//                        String article = currentNewsItem.articleUrl;
//                        Log.d("ajeetv", "onItemClick: "+ currentNewsItem.articleUrl);
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article));
//                        startActivity(browserIntent);
//                    }
//                });

            }
        });
    }


}
