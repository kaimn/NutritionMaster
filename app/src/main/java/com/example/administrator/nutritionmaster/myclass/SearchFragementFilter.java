package com.example.administrator.nutritionmaster.myclass;

import android.text.TextUtils;
import android.widget.Filter;

import com.example.administrator.nutritionmaster.entity.Foodbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class SearchFragementFilter extends Filter {


    //我们在performFiltering(CharSequence charSequence)这个方法中定义过滤规则
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        return null;

    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

    }
}
