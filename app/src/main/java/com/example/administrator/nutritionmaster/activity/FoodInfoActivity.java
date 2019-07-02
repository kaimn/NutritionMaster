package com.example.administrator.nutritionmaster.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.entity.Foodbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class FoodInfoActivity extends AppCompatActivity {

    private static final String[] titleToShow = {"营养成分","每100克含量"};
    private static final String[][] dataToShow = { { "热量", ""},
            { "钙", ""},
            { "蛋白质", ""},
            { "脂肪", ""},
            { "碳水化合物", ""},
            { "维生素C", ""},
            { "膳食纤维", ""},
            { "维生素E", ""},
            { "维生素A", ""},
            { "胆固醇", ""},
            { "钠", ""}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        Intent it = getIntent();
        Foodbean food = (Foodbean) it.getSerializableExtra("foodInfo");
        System.out.println(food);
        dataToShow[0][1] = String.valueOf(food.getHeat()+"千卡");
        dataToShow[1][1] = String.valueOf(food.getCalcium()+"毫克");
        dataToShow[2][1] = String.valueOf(food.getProtein()+"克");
        dataToShow[3][1] = String.valueOf(food.getFat()+"克");
        dataToShow[4][1] = String.valueOf(food.getCarbohydrate()+"克");
        dataToShow[5][1] = String.valueOf(food.getVitaminc()+"毫克");
        dataToShow[6][1] = String.valueOf(food.getDf()+"克");
        dataToShow[7][1] = String.valueOf(food.getVitamine()+"毫克");
        dataToShow[8][1] = String.valueOf(food.getVitamina()+"微克");
        dataToShow[9][1] = String.valueOf(food.getCholesterol()+"毫克");
        dataToShow[10][1] = String.valueOf(food.getNa()+"毫克");





        Log.i("============>",food.toString());
        TableView<String[]> tableView = (TableView<String[]>) findViewById(R.id.tableView);
        tableView.setDataAdapter(new SimpleTableDataAdapter(this, dataToShow));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this,titleToShow));
        tableView.setColumnCount(2);

        tableView.setColumnWeight(1, 1);

    }
}
