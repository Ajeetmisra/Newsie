package com.example.newsie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Entertainment_news_activity extends Fragment {
    public Fragment_Entertainment_news_activity() {
    }
    ListView lv;
    TextView textView;
    private NewsDataAdapter dataAdapter;
    private EntertainmentActivityViewModel entertainmentActivityViewModel;
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
            entertainmentActivityViewModel = new ViewModelProvider(this).get(EntertainmentActivityViewModel.class);

            entertainmentActivityViewModel.getEntertainmentNews().observe(getViewLifecycleOwner(), new Observer<List<News>>() {

                @Override
                public void onChanged(List<News> news) {
                    dataAdapter = new NewsDataAdapter(getActivity(),news);
                    lv.setAdapter(dataAdapter);

                }

            });
    }


}
