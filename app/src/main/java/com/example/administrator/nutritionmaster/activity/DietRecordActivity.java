package com.example.administrator.nutritionmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.codbking.calendar.CaledarAdapter;
import com.codbking.calendar.CalendarBean;
import com.codbking.calendar.CalendarDateView;
import com.codbking.calendar.CalendarUtil;
import com.codbking.calendar.CalendarView;
import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.adapter.DietRecordAdapter;
import com.example.administrator.nutritionmaster.constant.HttpConstants;
import com.example.administrator.nutritionmaster.entity.Dietbean;
import com.example.administrator.nutritionmaster.utils.SimpleHttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DietRecordActivity extends AppCompatActivity implements CalendarView.OnItemClickListener {

    TextView mTitle;
    CalendarDateView mCalendarDateView;
    ListView mList;
    ListView noonList;
    String mapDataJsonStr;
    Map<String, List<Dietbean>> mapData;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 20) {
                List<Dietbean> morningList = mapData.get("morning");
                List<Dietbean> noonList = mapData.get("noon");
                List<Dietbean> dinnerList = mapData.get("dinner");
                List<Dietbean> addList = mapData.get("add");
                Integer morningAmountHeat = 0;
                Integer noonAmountHeat = 0;
                Integer dinnerAmountHeat = 0;
                Integer addAmountHeat = 0;
                for (Dietbean dietbean : morningList) {
                    morningAmountHeat += Integer.valueOf(dietbean.getAmountheat());
                }

                for (Dietbean dietbean : noonList) {
                    noonAmountHeat += Integer.valueOf(dietbean.getAmountheat());
                }

                for (Dietbean dietbean : dinnerList) {
                    dinnerAmountHeat += Integer.valueOf(dietbean.getAmountheat());
                }

                for (Dietbean dietbean : addList) {
                    addAmountHeat += Integer.valueOf(dietbean.getAmountheat());
                }
                DietRecordAdapter dra = new DietRecordAdapter(morningAmountHeat, noonAmountHeat, dinnerAmountHeat, addAmountHeat, DietRecordActivity.this);
                mList.setAdapter(dra);
                mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        if (position == 6) {
                        }
                    }
                });
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_record);

        mCalendarDateView = (CalendarDateView) findViewById(R.id.calendarDateView);
        mTitle = (TextView) findViewById(R.id.title);
        mList = (ListView) findViewById(R.id.list);

        Intent getIntent = getIntent();
        mapDataJsonStr = getIntent.getStringExtra("data");
        Gson gson = new Gson();
        mapData = gson.fromJson(mapDataJsonStr, new TypeToken<Map<String, List<Dietbean>>>() {
        }.getType());
        initView();
        initList();
    }

    private void initList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String dateStr = sdf.format(date);
                String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/DietRecordServlet?task=queryByDate&date=" + dateStr;
                String result = SimpleHttpUtil.doGet(url);
                Gson gson = new Gson();
                Map<String, Object> resultMap = gson.fromJson(result, new TypeToken<Map<String, Object>>() {
                }.getType());
                if ("success".equals(resultMap.get("status"))) {
                    String mapDataJsonStr = (String) resultMap.get("data");
                    mapData = gson.fromJson(mapDataJsonStr, new TypeToken<Map<String, List<Dietbean>>>() {
                    }.getType());
                    handler.sendMessage(handler.obtainMessage(20, mapData));
                }
            }
        }).start();

    }

    private void initView() {

        findViewById(R.id.back_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mCalendarDateView.setAdapter(new CaledarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {

                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_xiaomi, null);
                }

                TextView chinaText = (TextView) convertView.findViewById(R.id.chinaText);
                TextView text = (TextView) convertView.findViewById(R.id.text);

                text.setText("" + bean.day);
                if (bean.mothFlag != 0) {
                    text.setTextColor(0xff9299a1);
                } else {
                    text.setTextColor(0xff444444);
                }
                chinaText.setText(bean.chinaDay);
                return convertView;
            }
        });

        mCalendarDateView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion, final CalendarBean bean) {
                mTitle.setTextSize(18);
                mTitle.setText(bean.year + "年" + bean.moth + "月" + bean.day + "日");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String dateStr = bean.year + "-" + bean.moth + "-" + bean.day;
                        String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/DietRecordServlet?task=queryByDate&date=" + dateStr;
                        String result = SimpleHttpUtil.doGet(url);
                        Gson gson = new Gson();
                        Map<String, Object> resultMap = gson.fromJson(result, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        if ("success".equals(resultMap.get("status"))) {
                            String mapDataJsonStr = (String) resultMap.get("data");
                            mapData = gson.fromJson(mapDataJsonStr, new TypeToken<Map<String, List<Dietbean>>>() {
                            }.getType());
                            handler.sendMessage(handler.obtainMessage(20, mapData));
                        }
                    }
                }).start();
            }
        });
        mTitle.setTextSize(18);
        int[] data = CalendarUtil.getYMD(new Date());
        mTitle.setText(data[0] + "年" + data[1] + "月" + data[2] + "日");
    }

    public void onClick() {
        finish();
    }

    @Override
    public void onItemClick(View view, int i, CalendarBean calendarBean) {
    }
}
