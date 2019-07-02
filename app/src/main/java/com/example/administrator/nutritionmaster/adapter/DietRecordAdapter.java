package com.example.administrator.nutritionmaster.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.entity.Dietbean;
import com.example.administrator.nutritionmaster.entity.ListViewFoodItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class DietRecordAdapter extends BaseAdapter {


    private Integer morning;
    private Integer noon;
    private Integer dinner;
    private Integer add;
    private LayoutInflater layoutInflater;
    private Context context;

    public DietRecordAdapter(Integer morning,Integer noon,Integer dinner,Integer add, Context context){
        this.morning = morning;
        this.noon = noon;
        this.dinner = dinner;
        this.add = add;

        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }



    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        //布局不变，数据变

        //如果缓存为空，我们生成新的布局作为1个item
        if(convertView==null){
            Log.i("info:", "没有缓存，重新生成"+position);
//            LayoutInflater inflater = context.getLayoutInflater();
            //因为getView()返回的对象，adapter会自动赋给ListView
            view = layoutInflater.inflate(R.layout.diet_record_listview_item, null);
        }else{
            Log.i("info:", "有缓存，不需要重新生成"+position);
            view = convertView;
        }

        TextView tv_content = (TextView)view.findViewById(R.id.tv_content);
        tv_content.setTextSize(18);
        if(position == 0) {
            tv_content.setText(" 基本数据(热量统计)");
        }else if(position == 1){
            tv_content.setText(" 早餐: "+ String.valueOf(morning) +" 千卡");
        }else if(position == 2){
            tv_content.setText(" 午餐: "+ String.valueOf(noon) +" 千卡");
        }else if(position == 3){
            tv_content.setText(" 晚餐: "+ String.valueOf(dinner) +" 千卡");
        }else if(position == 4){
            tv_content.setText(" 加餐: "+ String.valueOf(add) +" 千卡");
        }else if(position == 5){
            tv_content.setText(" 总计: "+ String.valueOf(morning+noon+dinner+add) +" 千卡");
        }else if(position == 6){
            tv_content.setText("                            <详细记录>     ");
            tv_content.setTextColor(Color.RED);
        }
        return view;
    }




}
