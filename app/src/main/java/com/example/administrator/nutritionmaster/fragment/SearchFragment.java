package com.example.administrator.nutritionmaster.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.activity.AddFoodActivity;
import com.example.administrator.nutritionmaster.activity.DietPlanActivity;
import com.example.administrator.nutritionmaster.activity.FoodInfoActivity;
import com.example.administrator.nutritionmaster.adapter.FoodInfoAdapter;
import com.example.administrator.nutritionmaster.constant.HttpConstants;
import com.example.administrator.nutritionmaster.entity.Foodbean;
import com.example.administrator.nutritionmaster.utils.SimpleHttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/18.
 */

public class SearchFragment extends Fragment {
    private String[] mStrs = {"111"};
    private SearchView mSearchView;
    private ListView mListView;
    private View view;
    List<Foodbean> list = new ArrayList<Foodbean>();
    //解决搜索时出现类似Toast提示
    private FoodInfoAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view == null){
            view = inflater.inflate(R.layout.search_fragment, container, false);
        }
        mSearchView = view.findViewById(R.id.search_view);
        mListView = view.findViewById(R.id.listView);

        final Handler handler = new Handler(){
            public void handleMessage(Message msg) {


                adapter = new FoodInfoAdapter(getContext(),list);
                mListView.setAdapter(adapter);

            }
        };

        // 获取列表
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/AddFoodServlet?task=queryAll";
                String result = SimpleHttpUtil.doGet(url);
                Gson gson = new Gson();
                list = gson.fromJson(result, new TypeToken<List<Foodbean>>() {}.getType());
                Log.i("=========>",result.toString());
//                mStrs = new String[list.size()];



//                for(int i = 0;i < list.size();i++){
//                    mStrs[i] = list.get(i).getName()+":"+list.get(i).getHeat()+"(千卡/100克)";
//                }

                handler.sendMessage(handler.obtainMessage(22,mStrs));
            }
        }).start();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!"".equals(newText)){
                    //解决出现Toast问题
                    //mListView.setFilterText(newText);
                    adapter.getFilter().filter(newText);
                }else{
                    adapter.getFilter().filter(newText);
                    mListView.clearTextFilter();
                }
                return false;
            }
        });


        /*mListView.setAdapter(adapter);
        mListView.setTextFilterEnabled(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!"".equals(newText)){
                    //解决出现Toast问题
                    //mListView.setFilterText(newText);
                    adapter.getFilter().filter(newText);
                }else{
                    adapter.getFilter().filter(newText);
                    mListView.clearTextFilter();
                }
                return false;
            }
        });*/
        mListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
//                        Toast.makeText(getActivity().getApplicationContext(), "您点击了第" + id + "个项目", Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/FoodServlet?task=queryFoodById&foodId="+id;
                                String result = SimpleHttpUtil.doGet(url);
                                Gson gson = new Gson();
                                Map<String, String> resultMap = gson.fromJson(result, new TypeToken<Map<String, String>>(){}.getType());
//                                Food obj = (String)resultMap.get("food");
                                Foodbean food = gson.fromJson(resultMap.get("food"),new TypeToken<Foodbean>(){}.getType());
                                Intent it = new Intent(getActivity(), FoodInfoActivity.class);
                                it.putExtra("foodInfo",food);
                                startActivity(it);
                            }
                        }).start();
                    }
                }
        );


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TextView tv = (TextView) getActivity().findViewById(R.id.tv);
        // tv.setText(getArguments().getString("ARGS"));

    }

    public static SearchFragment newInstance(String content) {
        Bundle args = new Bundle();
        Log.i("=======",content);
        args.putString("ARGS", content);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
