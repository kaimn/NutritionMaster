package com.example.administrator.nutritionmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.nutritionmaster.R;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void backToLogin(View v) {
        Intent it = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(it);
    }

    public void register(View v) {
        //注册功能
        Intent it = new Intent(RegisterActivity.this, PhysicalActivity.class);
        it.putExtra("username", ((EditText) findViewById(R.id.editText3)).getText().toString());
        it.putExtra("password", ((EditText) findViewById(R.id.password)).getText().toString());
        it.putExtra("phone", ((EditText) findViewById(R.id.phone)).getText().toString());
        startActivity(it);
        Toast.makeText(getApplicationContext(),"请继续填写基础信息",  Toast.LENGTH_SHORT).show();
    }
}
