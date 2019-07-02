package com.example.administrator.nutritionmaster.fragment;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> list;
    public MyFragmentPagerAdapter (FragmentManager fm, ArrayList<Fragment> list){
        super(fm);
        this.list = list;
    }
    //获得也没总个数
    @Override
    public int getCount() {
        return list.size();
    }
    //得到每个页面
    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }

}