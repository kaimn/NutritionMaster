package com.example.administrator.nutritionmaster.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.constant.HttpConstants;
import com.example.administrator.nutritionmaster.entity.Foodbean;
import com.example.administrator.nutritionmaster.entity.ListViewFoodItem;
import com.example.administrator.nutritionmaster.myclass.Popwindow;
import com.example.administrator.nutritionmaster.utils.SimpleHttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddFoodActivity extends AppCompatActivity {

    private String[] strArr = new String[]{
            "ListView控件演示1",
            "ProgressBar控件演示2",
            "GridView控件演示3",
            "ScrollView控件演示4",
            "DatePicker控件演示5",
            "ListView控件演示6",
            "ProgressBar控件演示7",
            "GridView控件演示8",
            "ScrollView控件演示9",
            "DatePicker控件演示10",
            "ListView控件演示11",
            "ProgressBar控件演示12",
            "GridView控件演示13",
            "ScrollView控件演示14",
            "DatePicker控件演示15"
    };

    List<ListViewFoodItem> result = new ArrayList<>();

    Map<String, String> dataDTO = new HashMap<>();

    private List<Foodbean> lvfList = new ArrayList<Foodbean>();

    private ListView listView;
    private SimpleAdapter adapter;//要实现的类

    private TextView tv;

    Popwindow popWindow;
    Activity mContext;

    int myRequestCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        listView = (ListView) findViewById(R.id.food_list);
        Intent getIntent = getIntent();
        myRequestCode = Integer.valueOf(getIntent.getStringExtra("requestCode"));

        //动态创建食物列表
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {

                listView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return lvfList.size();
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
                        if (convertView == null) {
                            Log.i("info:", "没有缓存，重新生成" + position);
                            LayoutInflater inflater = AddFoodActivity.this.getLayoutInflater();
                            //因为getView()返回的对象，adapter会自动赋给ListView
                            view = inflater.inflate(R.layout.food_listview_item, null);
                        } else {
                            Log.i("info:", "有缓存，不需要重新生成" + position);
                            view = convertView;
                        }
                        Foodbean m = lvfList.get(position);
                        TextView tv_userName = (TextView) view.findViewById(R.id.tv_foodName);
                        tv_userName.setText("  " + m.getName());
                        tv_userName.setTextSize(18);

                        TextView tv_lastMessage = (TextView) view.findViewById(R.id.tv_foodHeat);
                        tv_lastMessage.setText("  " + m.getHeat());
                        tv_lastMessage.setTextSize(14);

                        TextView tv_datetime = (TextView) view.findViewById(R.id.tv_datetime);
                        tv_datetime.setText("千卡/100克");
                        tv_datetime.setTextSize(16);

                        TextView tv_foodid = (TextView) view.findViewById(R.id.tv_foodId);
                        tv_foodid.setText("");

                        TextView tv_foodtime = (TextView) view.findViewById(R.id.tv_time);

                        if (myRequestCode == 1000) {
                            tv_foodtime.setText("1");
                        } else if (myRequestCode == 1001) {
                            tv_foodtime.setText("2");
                        } else if (myRequestCode == 1002) {
                            tv_foodtime.setText("3");
                        } else if (myRequestCode == 1003) {
                            tv_foodtime.setText("4");
                        }


                        return view;
                    }
                });

            }
        };

        final EditText et = (EditText) findViewById(R.id.textEdit);
        et.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = et.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et.getWidth()
                        - et.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
//熊72.26.67.1
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = SimpleHttpUtil.doPost(HttpConstants.SERVER_HOST + "NutritionMaster/servlet/AddFoodServlet", "task=search&condition=" + et.getText().toString());
                            Gson gson = new Gson();
                            List<Foodbean> list = gson.fromJson(result, new TypeToken<List<Foodbean>>() {
                            }.getType());
                            lvfList = list;
                            handler.sendMessage(handler.obtainMessage(10, lvfList));
                        }
                    }).start();
                }
                return false;
            }
        });

        popWindow = new Popwindow(AddFoodActivity.this, onClickListener);


        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });

        //listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commonFunList));
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(getApplicationContext(), "您点击了第" + position + "个项目", Toast.LENGTH_SHORT).show();
                        setBackgroundAlpha(0.5f);
                        Foodbean foodbean = lvfList.get(position);
                        String foodName = foodbean.getName();
                        String foodHeat = String.valueOf(foodbean.getHeat());
                        String foodId = String.valueOf(foodbean.getId());
                        TextView foodTimeTV = (TextView) findViewById(R.id.tv_time);
                        String foodTime = foodTimeTV.getText().toString();
                        popWindow.setPopUpResult(foodName, foodHeat, foodId, foodTime);
                        popWindow.showAtLocation(AddFoodActivity.this.findViewById(R.id.add_food_div), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                    }
                }
        );

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/AddFoodServlet?task=queryAll";
                String result = SimpleHttpUtil.doGet(url);
                Gson gson = new Gson();
                List<Foodbean> list = gson.fromJson(result, new TypeToken<List<Foodbean>>() {
                }.getType());
                lvfList = list;
                handler.sendMessage(handler.obtainMessage(10, lvfList));

            }
        }).start();

        //跳转到自定义食物界面
//        TextView defined_food = (TextView) findViewById(R.id.defined_food_btn);
//        defined_food.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(AddFoodActivity.this,DefinedFoodActivity.class);
//                startActivity(it);
//            }
//        });

    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView foodAmountText = (TextView) findViewById(R.id.food_amount_text);
            Integer currentCount;
            switch (v.getId()) {
                case R.id.btn_ok:
                    if (result.size() >= 10) {
                        Toast.makeText(getApplicationContext(), "建议健康饮食", Toast.LENGTH_SHORT).show();
                    } else {
                        popWindow.addFoodToList(result);
                        popWindow.dismiss();
                    }
                    foodAmountText.setText(String.valueOf(result.size()));
                    Log.i("====>", String.valueOf(result.size()));
                    break;
                case R.id.btn_cancel:
                    System.out.println("btn_pick_photo");
                    popWindow.dismiss();
                    break;
//                case R.id.add_food_btn:
//                    popWindow.setFoodCount(1);
//                    break;
//                case  R.id.remove_food_btn:
//                    popWindow.setFoodCount(0);
//                    break;

            }
        }
    };


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = AddFoodActivity.this.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        AddFoodActivity.this.getWindow().setAttributes(lp);
    }

    /**
     * 单击完成之后，将选好的食物发送到服务器
     *
     * @param v
     */
    public void backToDietActivity(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = HttpConstants.SERVER_HOST +  "/NutritionMaster/servlet/DietPlanServlet";
                Gson gson = new Gson();
                String resultJson = gson.toJson(result);
                dataDTO.put("task", "addFoodToDiet");
                dataDTO.put("jsonStr", resultJson);
                String dataStrDTO = gson.toJson(dataDTO);


                String str = "dataStrDTO=" + dataStrDTO;
                String resultPost = SimpleHttpUtil.doPost(url, str);

                Intent it = new Intent();
                setResult(myRequestCode, it);
                finish();

            }
        }).start();


    }


}