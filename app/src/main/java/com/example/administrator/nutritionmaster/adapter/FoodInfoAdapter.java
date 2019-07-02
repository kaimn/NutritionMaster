package com.example.administrator.nutritionmaster.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.entity.Foodbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2018/4/22.
 */

public class FoodInfoAdapter extends BaseAdapter implements Filterable {

    private List<Foodbean> _data = new ArrayList<Foodbean>();
    private LayoutInflater _inflater ;
    private List<Foodbean> _originalData = new ArrayList<Foodbean>();
    private SearchFilter _filter;

    private final Object _lock = new Object();

    public FoodInfoAdapter(Context context, List list){
        this._data = list;
        this._inflater = LayoutInflater.from(context);
    }

    public void resetData(ArrayList<Foodbean> data) {
        _data = data;
        if (_originalData != null)
            _originalData = _data;
    }

    @Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public Object getItem(int position) {
        return _data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // 在此最好返回数据的唯一标识，在一些特定情况下使用到
        // 如果没有，此处一般返回position
        return _data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = null;

        if(convertView == null){

            view = _inflater.inflate(R.layout.food_listview_item, null);
        }else{
            view = convertView;
        }

        Foodbean m = _data.get(position);
        TextView tv_userName = view.findViewById(R.id.tv_foodName);
        tv_userName.setText("" + m.getName());
        tv_userName.setTextSize(18);

        TextView tv_lastMessage = view.findViewById(R.id.tv_foodHeat);
        tv_lastMessage.setText("" + m.getHeat()+"千卡/100克");
        tv_lastMessage.setTextSize(14);

        TextView tv_datetime = (TextView)view.findViewById(R.id.tv_datetime);
        tv_datetime.setText("" + m.getId());
        tv_datetime.setTextSize(14);

        return view;
    }


    @Override
    public Filter getFilter() {
        if (_filter == null) {
            _filter = new SearchFilter();
        }
        return _filter;
    }

    class SearchFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // 定义过滤规则
            FilterResults filterResults = new FilterResults();

            // 保存原始数据
            if (_originalData == null || _originalData.size() == 0) {
                synchronized (_lock) {
                    _originalData = new ArrayList<Foodbean>(_data);
                }
            }

            // 如果搜索框内容为空，就恢复原始数据
            if (TextUtils.isEmpty(constraint)) {
                synchronized (_lock) {
                    filterResults.values = _originalData;
                    filterResults.count = _originalData.size();
                }
            } else {

                // 否则过滤出新数据
                String filterString = constraint.toString().trim()
                        .toLowerCase(Locale.US);// 过滤首尾空白，小写过滤
                ArrayList<Foodbean> newValues = new ArrayList<Foodbean>();

                for (Foodbean vo : _originalData) {
                    if (vo.getName().toLowerCase(Locale.US)
                            .contains(filterString)) {
                        newValues.add(vo);
                    }
                    filterResults.values = newValues;
                    filterResults.count = newValues.size();
                }
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            _data = (ArrayList<Foodbean>) results.values;// 更新适配器的数据
            if (results.count > 0) {
                notifyDataSetChanged();// 通知数据发生了改变
            } else {
                notifyDataSetInvalidated();// 通知数据失效
            }
        }
    }
}
