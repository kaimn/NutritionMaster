package com.example.administrator.nutritionmaster.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.activity.PhysicalActivity;
import com.example.administrator.nutritionmaster.entity.Userbean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by WangChang on 2016/5/15.
 */
public class MyinfoFragment extends Fragment {

    TextView nikeNameTV;
    TextView myDeclarationTV;
    TextView phoneNumTV;
    TextView sexTV;
    TextView myBirthdayTV;
    TextView heightTV;
    TextView weightTV;
    TextView gold;
    Button resetBT;
    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myinfo_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //从数据库中获取数据并展示
        super.onActivityCreated(savedInstanceState);
        sp = getActivity().getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        String userInfoStr = sp.getString("userInfo", "");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Userbean userInfo = gson.fromJson(userInfoStr, new TypeToken<Userbean>() {
        }.getType());
        nikeNameTV = (TextView) getActivity().findViewById(R.id.nike_name);
        myDeclarationTV = (TextView) getActivity().findViewById(R.id.my_declaration);
        phoneNumTV = (TextView) getActivity().findViewById(R.id.phone_num);
        sexTV = (TextView) getActivity().findViewById(R.id.sex);
        myBirthdayTV = (TextView) getActivity().findViewById(R.id.my_birthday);
        heightTV = (TextView) getActivity().findViewById(R.id.my_height);
        weightTV = (TextView) getActivity().findViewById(R.id.my_init_weight);
        gold = (TextView) getActivity().findViewById(R.id.my_gold);

        nikeNameTV.setText(userInfo.getNikename().toString());
        myDeclarationTV.setText(userInfo.getDeclaration().toString());
        phoneNumTV.setText(userInfo.getPhone().toString());
        sexTV.setText(userInfo.getSex().toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = userInfo.getBirthday();
        myBirthdayTV.setText(sdf.format(birthday));
        heightTV.setText(userInfo.getHeight().toString() + " CM");
        weightTV.setText(userInfo.getWeight().toString() + " Kg");
        gold.setText(userInfo.getGold().toString() + " Kg");

//        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
//        tv.setText(getArguments().getString("ARGS"));
    }

    public static MyinfoFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MyinfoFragment fragment = new MyinfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
