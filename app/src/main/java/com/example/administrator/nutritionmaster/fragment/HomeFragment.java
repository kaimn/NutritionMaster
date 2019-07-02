package com.example.administrator.nutritionmaster.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.activity.DietCareActivity;
import com.example.administrator.nutritionmaster.activity.DietPlanActivity;
import com.example.administrator.nutritionmaster.activity.DietRecordActivity;
import com.example.administrator.nutritionmaster.activity.ShowListActivity;
import com.example.administrator.nutritionmaster.activity.ShowPaiActivity;
import com.example.administrator.nutritionmaster.constant.HttpConstants;
import com.example.administrator.nutritionmaster.utils.SimpleHttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);


        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "";
            }
        }).start();


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //跳转到 饮食计划 activity
        //注意类型转换
        TextView toDietPlanBt = getActivity().findViewById(R.id.food_plan_button);
        toDietPlanBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), DietPlanActivity.class);
                startActivity(it);
            }
        });

        TextView toDietCareBt = getActivity().findViewById(R.id.tiaoyang);
        toDietCareBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), DietCareActivity.class);
                startActivity(it);
            }
        });

        TextView toDietRecordBt = getActivity().findViewById(R.id.food_record_button);
        toDietRecordBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String dateStr = sdf.format(date);
                        String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/DietRecordServlet?task=queryByDate&date=" + dateStr;
                        String result = SimpleHttpUtil.doGet(url);
                        Gson gson = new Gson();
                        Map<String, Object> resultMap = gson.fromJson(result, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        if ("success".equals(resultMap.get("status"))) {
                            String mapDataJsonStr = (String) resultMap.get("data");
                            //Map<String,List<Dietbean>> mapData = (Map<String,List<Dietbean>>) resultMap.get("data");
                            System.out.println(resultMap);
                            Intent it = new Intent(getActivity(), DietRecordActivity.class);
                            it.putExtra("data", mapDataJsonStr);
                            startActivity(it);
                        }

                    }
                }).start();
            }
        });

        TextView toShowListBt = getActivity().findViewById(R.id.niupai);
        toShowListBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), ShowListActivity.class);
                startActivity(it);
            }
        });

        TextView toShowPaiBt = getActivity().findViewById(R.id.paigu);
        toShowPaiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), ShowPaiActivity.class);
                startActivity(it);
            }
        });
    }

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
