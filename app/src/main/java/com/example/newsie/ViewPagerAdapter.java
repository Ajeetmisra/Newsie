package com.example.newsie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment>fragmentList = new ArrayList<Fragment>();
    List<String> fragmentstitle = new ArrayList<String>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentstitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentstitle.get(position);
    }
   public void AddFragments(Fragment fragment, String s)
   {
       fragmentstitle.add(s);
       fragmentList.add(fragment);
   }
}
