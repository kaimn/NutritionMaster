package com.example.administrator.nutritionmaster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.constant.HttpConstants;
import com.example.administrator.nutritionmaster.utils.SimpleHttpUtil;
import com.example.administrator.nutritionmaster.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static final String REGISTER = "";
    private static final String REMEMBER = "记住密码";

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView registerLink = (TextView) findViewById(R.id.register_link);
        SpannableString ssOfRegisterLink = new SpannableString(REGISTER);
        ssOfRegisterLink.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }

        }, 0, REGISTER.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registerLink.setText(ssOfRegisterLink);
        registerLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }


    public void login(View view) {
        //登录功能
        new Thread(new Runnable() {
            @Override
            public void run() {
                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);
                String param = "username=" + username.getText().toString() +"&password="+password.getText().toString();
                String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/LoginServlet";
                String result = SimpleHttpUtil.doPost(url,param);

                Log.d("======>",result);
                try {
                    Gson gson = new Gson();
                    Map<String, String> resultMapper = gson.fromJson(result, new TypeToken<Map<String,String>>(){}.getType());
                    String status =  resultMapper.get("status");
                    Looper.prepare();
                    if("success".equals(status)){
                        String userInfo = resultMapper.get("data");
                        sp = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("userInfo",userInfo);
                        editor.commit();
                        Intent it = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(it);
                        ToastUtils.show(getApplicationContext(),"登录成功");
                        LoginActivity.this.finish();
                    }else{
                        ToastUtils.show(getApplicationContext(),"登录失败");
                    }
                    Looper.loop();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();


    }

//    public void login(View view){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client = new OkHttpClient();
//                EditText username = (EditText) findViewById(R.id.username);
//                EditText password = (EditText) findViewById(R.id.password);
//                //3, 发起新的请求,获取返回信息
//                RequestBody body = new FormBody.Builder()
//                        .add("username", username.getText().toString())//添加键值对
//                        .add("password", password.getText().toString())
//                        .build();
//                Request request = new Request.Builder()
//                        .url("http://118.126.117.184:8080/NutritionMaster/servlet/LoginServlet")
//                        .post(body)
//                        .build();
//                Response response = null;
//                try {
//                    response = client.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        String result = response.body().string();
//                        Gson gson = new Gson();
//                        Map<String, String> resultMapper = gson.fromJson(result, new TypeToken<Map<String,String>>(){}.getType());
//                        String status =  resultMapper.get("status");
//                        if("success".equals(status)){
//                            String userInfo = resultMapper.get("data");
//                            sp = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sp.edit();
//                            editor.putString("userInfo",userInfo);
//                            editor.commit();
//                            Intent it = new Intent(LoginActivity.this,MainActivity.class);
//                            startActivity(it);
////                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT);
//                            LoginActivity.this.finish();
//                        }else{
////                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT);
//                        }
//                        System.out.println(response.body().string());
//                    } else {
////                        ToastUtils.show(getApplicationContext(),"登录失败");
//                        throw new IOException("Unexpected code " + response);
//                    }
//                } catch (IOException e) {
////                    ToastUtils.show(getApplicationContext(),"登录失败");
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//    }


//    public void login(View view) {
//
//                String str="";
//
//                Log.d("======>",str);
//
//
//
//                        Intent it = new Intent(LoginActivity.this,MainActivity.class);
//                        startActivity(it);
//                        ToastUtils.show(getApplicationContext(),"登录成功");
//                        LoginActivity.this.finish();
//
//
//
//
//    }




}
