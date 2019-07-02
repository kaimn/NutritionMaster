package com.example.administrator.nutritionmaster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.constant.HttpConstants;
import com.example.administrator.nutritionmaster.utils.SimpleHttpUtil;
import com.example.administrator.nutritionmaster.utils.ToastUtils;
import com.zkk.view.rulerview.RulerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PhysicalActivity extends AppCompatActivity {

    private RulerView ruler_height;   //身高的view
    private RulerView ruler_weight;  //体重的view
    private TextView tv_register_info_height_value, tv_register_info_weight_value;

    private DatePicker datePicker;

    private TextView submit_button;
    private TextView back_button;

    private CheckBox sexBox;

    private String height = "170.00";
    private String weight = "50.00";
    private String birthday = "";
    private boolean sex = false;

    private String username;
    private String password;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);
        datePicker = (DatePicker) findViewById(R.id.dpPicker);

        back_button = (TextView) findViewById(R.id.back);
        submit_button = (TextView) findViewById(R.id.submit);

        ruler_height = (RulerView) findViewById(R.id.ruler_height);
        ruler_weight = (RulerView) findViewById(R.id.ruler_weight);

        tv_register_info_height_value = (TextView) findViewById(R.id.tv_register_info_height_value);
        tv_register_info_weight_value = (TextView) findViewById(R.id.tv_register_info_weight_value);

        sexBox = (CheckBox) findViewById(R.id.btn_register_info_sex);

        sexBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println(b);
            }
        });

        ruler_height.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                tv_register_info_height_value.setText(value + "");
                height = String.valueOf(value);
            }
        });


        ruler_weight.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                tv_register_info_weight_value.setText(value + "");
                weight = String.valueOf(value);
            }
        });

        ruler_height.setValue(165, 80, 250, 1);

        ruler_weight.setValue(55, 20, 200, 0.1f);

        datePicker.init(2013, 8, 20, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
//                Toast.makeText(PhysicalActivity.this,
//                        format.format(calendar.getTime()), Toast.LENGTH_SHORT)
//                        .show();
                birthday = format.format(calendar.getTime());
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhysicalActivity.super.onBackPressed();
            }
        });

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        phone = getIntent().getStringExtra("phone");
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread( new Runnable() {
                    @Override
                    public void run() {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        birthday = sdf.format(new Date());
                        String result = SimpleHttpUtil.doPost(HttpConstants.SERVER_HOST + "NutritionMaster/servlet/RegisterServlet",
                                "username=" + username + "&password=" + password + "&phone=" + phone
                                        + "&height=" + height + "&weight=" + weight + "&birthday=" + birthday + "&sex=" + sex);
                        Looper.prepare();
                        if(result.equals("1")){
                            Intent it = new Intent(PhysicalActivity.this, LoginActivity.class);
                            startActivity(it);
                            Toast.makeText(getApplicationContext(),"注册成功，请登录",  Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),result,  Toast.LENGTH_SHORT).show();
                        }
                        Looper.loop();


                    }
                }).start();
            }
        });
    }
}
