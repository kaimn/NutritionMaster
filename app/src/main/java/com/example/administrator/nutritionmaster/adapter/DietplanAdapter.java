package com.example.administrator.nutritionmaster.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.activity.DietPlanActivity;
import com.example.administrator.nutritionmaster.entity.ListViewFoodItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class DietplanAdapter extends BaseAdapter {


    private List<ListViewFoodItem> tempList = new ArrayList<ListViewFoodItem>();
    private LayoutInflater layoutInflater;
    private Context context;

    public DietplanAdapter(List<ListViewFoodItem> list,Context context){
        this.tempList = list;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }



    @Override
    public int getCount() {
        return tempList.size();
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
            view = layoutInflater.inflate(R.layout.listview_item, null);
        }else{
            Log.i("info:", "有缓存，不需要重新生成"+position);
            view = convertView;
        }
        ListViewFoodItem m = tempList.get(position);
        TextView tv_userName = (TextView)view.findViewById(R.id.tv_userName);
        tv_userName.setText("  "+m.getFoodName());
        tv_userName.setTextSize(18);

        TextView tv_lastMessage = (TextView)view.findViewById(R.id.tv_lastMessage);
        tv_lastMessage.setText("  "+m.getFoodHeat());
        tv_lastMessage.setTextSize(14);

        TextView tv_datetime = (TextView)view.findViewById(R.id.tv_datetime);
        tv_datetime.setText(m.getHeatAmount());
        tv_datetime.setTextSize(16);
        return view;
    }




}
