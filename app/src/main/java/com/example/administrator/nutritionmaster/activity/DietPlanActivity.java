package com.example.administrator.nutritionmaster.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.adapter.DietplanAdapter;
import com.example.administrator.nutritionmaster.constant.HttpConstants;
import com.example.administrator.nutritionmaster.entity.Dietbean;
import com.example.administrator.nutritionmaster.entity.ListViewFoodItem;
import com.example.administrator.nutritionmaster.entity.Userbean;
import com.example.administrator.nutritionmaster.utils.SimpleHttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DietPlanActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4;

    private ListView morningListView;
    private ListView afternoonListView;
    private ListView dinnerListView;
    private ListView addListView;

    private List<ListViewFoodItem> lvfList = new ArrayList<ListViewFoodItem>();
    private List<ListViewFoodItem> messageList = new ArrayList<ListViewFoodItem>();
    private List<List<ListViewFoodItem>> resultList = new ArrayList<List<ListViewFoodItem>>();

    //    private ListView lv;
    private ListView morningLv;
    private ListView noonLv;

    private ListView dinnerLv;
    private ListView addLv;

    private int firstTimeInFlag = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 动态改变LineatLayout 大小
            LinearLayout morningDiv = (LinearLayout) findViewById(R.id.morning_div);
            LinearLayout afernoonDiv = (LinearLayout) findViewById(R.id.afternoon_div);
            LinearLayout dinnerDiv = (LinearLayout) findViewById(R.id.dinner_div);
            LinearLayout addDiv = (LinearLayout) findViewById(R.id.add_div);

            ViewGroup.LayoutParams morningParams = morningDiv.getLayoutParams();
            ViewGroup.LayoutParams afternoonParams = afernoonDiv.getLayoutParams();
            ViewGroup.LayoutParams dinnerParams = dinnerDiv.getLayoutParams();
            ViewGroup.LayoutParams addParams = addDiv.getLayoutParams();

            DietplanAdapter ba;


            //morningParams.height = getListHeight(lv)+100;
            System.out.println("msg.what=================>" + msg.what);
            if (msg.what == 22) {
                final List<ListViewFoodItem> morningList = new ArrayList<ListViewFoodItem>();
                morningLv = (ListView) findViewById(R.id.morning_listview);
                getListItem(lvfList, morningList);
                ba = new DietplanAdapter(morningList, DietPlanActivity.this);
                morningLv.setAdapter(ba);

                morningParams.height = getListHeight(morningLv) + 125;
                morningLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                        //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(DietPlanActivity.this);
                        builder.setMessage("确定删除?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendDeleteDietFoodRequest(morningList, position, 22, "1", "morning");
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                        return false;
                    }
                });


            } else if (msg.what == 23) {
                final List<ListViewFoodItem> noonList = new ArrayList<ListViewFoodItem>();
                noonLv = (ListView) findViewById(R.id.noon_listview);
                getListItem(lvfList, noonList);
                ba = new DietplanAdapter(noonList, DietPlanActivity.this);
                noonLv.setAdapter(ba);
                afternoonParams.height = getListHeight(noonLv) + 125;
                noonLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                        //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(DietPlanActivity.this);
                        builder.setMessage("确定删除?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendDeleteDietFoodRequest(noonList, position, 23, "2", "noon");
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                        return false;
                    }
                });

            } else if (msg.what == 24) {
                final List<ListViewFoodItem> dinnerList = new ArrayList<ListViewFoodItem>();
                dinnerLv = (ListView) findViewById(R.id.dinner_listview);
                getListItem(lvfList, dinnerList);
                ba = new DietplanAdapter(dinnerList, DietPlanActivity.this);
                dinnerLv.setAdapter(ba);
                dinnerParams.height = getListHeight(dinnerLv) + 125;
                dinnerLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                        //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(DietPlanActivity.this);
                        builder.setMessage("确定删除?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendDeleteDietFoodRequest(dinnerList, position, 24, "3", "dinner");
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                        return false;
                    }
                });

            } else if (msg.what == 25) {
                final List<ListViewFoodItem> addList = new ArrayList<ListViewFoodItem>();
                addLv = (ListView) findViewById(R.id.add_listview);
                getListItem(lvfList, addList);
                ba = new DietplanAdapter(addList, DietPlanActivity.this);
                addLv.setAdapter(ba);
                addParams.height = getListHeight(addLv) + 125;
                addLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                        //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(DietPlanActivity.this);
                        builder.setMessage("确定删除?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendDeleteDietFoodRequest(addList, position, 25, "4", "add");
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                        return false;
                    }
                });

            } else if (msg.what == 20) {
                final List<ListViewFoodItem> morningList = new ArrayList<ListViewFoodItem>();
                final List<ListViewFoodItem> noonList = new ArrayList<ListViewFoodItem>();
                final List<ListViewFoodItem> dinnerList = new ArrayList<ListViewFoodItem>();
                final List<ListViewFoodItem> addList = new ArrayList<ListViewFoodItem>();

                morningLv = (ListView) findViewById(R.id.morning_listview);
                getListItem(resultList.get(0), morningList);
                ba = new DietplanAdapter(morningList, DietPlanActivity.this);
                morningLv.setAdapter(ba);
                morningParams.height = getListHeight(morningLv) + 125;


                noonLv = (ListView) findViewById(R.id.noon_listview);
                getListItem(resultList.get(1), noonList);
                ba = new DietplanAdapter(noonList, DietPlanActivity.this);
                noonLv.setAdapter(ba);
                afternoonParams.height = getListHeight(noonLv) + 125;


                dinnerLv = (ListView) findViewById(R.id.dinner_listview);
                getListItem(resultList.get(2), dinnerList);
                ba = new DietplanAdapter(dinnerList, DietPlanActivity.this);
                dinnerLv.setAdapter(ba);
                dinnerParams.height = getListHeight(dinnerLv) + 125;


                addLv = (ListView) findViewById(R.id.add_listview);
                getListItem(resultList.get(3), addList);
                ba = new DietplanAdapter(addList, DietPlanActivity.this);
                addLv.setAdapter(ba);
                addParams.height = getListHeight(addLv) + 125;

                morningLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                        //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(DietPlanActivity.this);
                        builder.setMessage("确定删除?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendDeleteDietFoodRequest(morningList, position, 22, "1", "morning");
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                        return false;
                    }
                });

                noonLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                        //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(DietPlanActivity.this);
                        builder.setMessage("确定删除?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendDeleteDietFoodRequest(noonList, position, 23, "2", "noon");
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                        return false;
                    }
                });

                dinnerLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DietPlanActivity.this);
                        builder.setMessage("确定删除?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendDeleteDietFoodRequest(dinnerList, position, 23, "3", "dinner");
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                        return false;
                    }
                });

                addLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DietPlanActivity.this);
                        builder.setMessage("确定删除?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendDeleteDietFoodRequest(addList, position, 25, "4", "add");
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                        return false;
                    }
                });


            }
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);


    }

    public void addMorning(View view) {
        Intent it = new Intent(DietPlanActivity.this, AddFoodActivity.class);
        it.putExtra("requestCode", "1000");
        startActivityForResult(it, 1000);
    }

    public void addNoon(View view) {
        Intent it = new Intent(DietPlanActivity.this, AddFoodActivity.class);
        it.putExtra("requestCode", "1001");
        startActivityForResult(it, 1001);
    }

    public void addDinner(View view) {
        Intent it = new Intent(DietPlanActivity.this, AddFoodActivity.class);
        it.putExtra("requestCode", "1002");
        startActivityForResult(it, 1002);
    }

    public void addAdd(View view) {
        Intent it = new Intent(DietPlanActivity.this, AddFoodActivity.class);
        it.putExtra("requestCode", "1003");
        startActivityForResult(it, 1003);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lvfList.clear();
        messageList.clear();
        if (requestCode == 1000 && resultCode == 1000) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String date = df.format(new Date());
                    System.out.println(date);
                    String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/DietPlanServlet?task=queryByDateAndTime&date=" + date + "&time=1";
                    String data = SimpleHttpUtil.doGet(url);
                    Gson gson = new Gson();
                    List<Dietbean> list = gson.fromJson(data, new TypeToken<List<Dietbean>>() {
                    }.getType());

                    getDietbeanToLlistViewFoodItem(list, lvfList);
//                    for(int i = 0; i < list.size(); i++){
//                        ListViewFoodItem lvf = new ListViewFoodItem();
//                        lvf.setId(String.valueOf(list.get(i).getId()));
//                        lvf.setTime(Integer.valueOf(list.get(i).getTime()));
//                        lvf.setHeatAmount(list.get(i).getAmountheat());
//                        lvf.setFoodHeat(list.get(i).getFoodheat());
//                        lvf.setFoodId(list.get(i).getFoodid());
//                        lvf.setFoodName(list.get(i).getFoodname());
//                        lvfList.add(lvf);
//                    }

                    handler.sendMessage(handler.obtainMessage(22, lvfList));
                }
            }).start();


        } else if (requestCode == 1001 && resultCode == 1001) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String date = df.format(new Date());
                    System.out.println(date);
                    String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/DietPlanServlet?task=queryByDateAndTime&date=" + date + "&time=2";
                    String data = SimpleHttpUtil.doGet(url);
                    Gson gson = new Gson();
                    List<Dietbean> list = gson.fromJson(data, new TypeToken<List<Dietbean>>() {
                    }.getType());
                    getDietbeanToLlistViewFoodItem(list, lvfList);

                    handler.sendMessage(handler.obtainMessage(23, lvfList));
                }
            }).start();

        } else if (requestCode == 1002 && resultCode == 1002) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String date = df.format(new Date());
                    System.out.println(date);
                    String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/DietPlanServlet?task=queryByDateAndTime&date=" + date + "&time=3";
                    String data = SimpleHttpUtil.doGet(url);
                    Gson gson = new Gson();
                    List<Dietbean> list = gson.fromJson(data, new TypeToken<List<Dietbean>>() {
                    }.getType());
                    getDietbeanToLlistViewFoodItem(list, lvfList);

                    handler.sendMessage(handler.obtainMessage(24, lvfList));
                }
            }).start();
        } else if (requestCode == 1003 && resultCode == 1003) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String date = df.format(new Date());
                    System.out.println(date);
                    String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/DietPlanServlet?task=queryByDateAndTime&date=" + date + "&time=4";
                    String data = SimpleHttpUtil.doGet(url);
                    Gson gson = new Gson();
                    List<Dietbean> list = gson.fromJson(data, new TypeToken<List<Dietbean>>() {
                    }.getType());
                    getDietbeanToLlistViewFoodItem(list, lvfList);

                    handler.sendMessage(handler.obtainMessage(25, lvfList));
                }
            }).start();
        }

    }


    public int getListHeight(ListView listView) {
        int totalHeight = 0;
        ListAdapter mAdapter = listView.getAdapter();
        if (mAdapter == null) {
            return 0;
        }
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);
            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            //mView.measure(0, 0);
            totalHeight += mView.getMeasuredHeight() + 35;
            Log.d("数据" + i, String.valueOf(totalHeight));
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        Log.d("数据", "listview总高度=" + params.height);
        listView.setLayoutParams(params);
        listView.requestLayout();

        return totalHeight;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (firstTimeInFlag == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String date = df.format(new Date());
                    System.out.println(date);
                    String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/DietPlanServlet?task=queryAllByDateAndTime&date=" + date;
                    String data = SimpleHttpUtil.doGet(url);
                    Gson gson = new Gson();
                    Map<String, List<Dietbean>> resultMap = gson.fromJson(data, new TypeToken<Map<String, List<Dietbean>>>() {
                    }.getType());
                    List<Dietbean> morningListDietbean = resultMap.get("morning");
                    List<Dietbean> noonListDietbean = resultMap.get("noon");
                    List<Dietbean> dinnerListDietbean = resultMap.get("dinner");
                    List<Dietbean> addListDietbean = resultMap.get("add");

                    List<ListViewFoodItem> morningList = new ArrayList<ListViewFoodItem>();
                    getDietbeanToLlistViewFoodItem(morningListDietbean, morningList);


                    List<ListViewFoodItem> noonList = new ArrayList<ListViewFoodItem>();
                    getDietbeanToLlistViewFoodItem(noonListDietbean, noonList);

                    List<ListViewFoodItem> dinnerList = new ArrayList<ListViewFoodItem>();
                    getDietbeanToLlistViewFoodItem(dinnerListDietbean, dinnerList);

                    List<ListViewFoodItem> addList = new ArrayList<ListViewFoodItem>();
                    getDietbeanToLlistViewFoodItem(addListDietbean, addList);

                    resultList.add(morningList);
                    resultList.add(noonList);
                    resultList.add(dinnerList);
                    resultList.add(addList);


                    handler.sendMessage(handler.obtainMessage(20, resultList));
                    firstTimeInFlag = 1;
                }
            }).start();
        }
    }

    public void getListItem(List<ListViewFoodItem> srcList, List<ListViewFoodItem> dntList) {
        for (int i = 0; i < srcList.size(); i++) {

            //添加数据
            ListViewFoodItem m = new ListViewFoodItem();
            m.setId("" + srcList.get(i).getId());
            m.setFoodName(srcList.get(i).getFoodName());
            m.setFoodHeat(srcList.get(i).getFoodHeat());
            m.setHeatAmount(srcList.get(i).getHeatAmount());
            dntList.add(m);
        }
    }


    public void getDietbeanToLlistViewFoodItem(List<Dietbean> srcList, List<ListViewFoodItem> dntList) {
        for (int i = 0; i < srcList.size(); i++) {
            ListViewFoodItem lvf = new ListViewFoodItem();
            lvf.setId(String.valueOf(srcList.get(i).getId()));
            lvf.setTime(Integer.valueOf(srcList.get(i).getTime()));
            lvf.setHeatAmount(srcList.get(i).getAmountheat());
            lvf.setFoodHeat(srcList.get(i).getFoodheat());
            lvf.setFoodId(srcList.get(i).getFoodid());
            lvf.setFoodName(srcList.get(i).getFoodname());
            dntList.add(lvf);
        }
    }

    public void sendDeleteDietFoodRequest(final List<ListViewFoodItem> tempList, final int position, final int what, final String time, final String mapKey) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String date = df.format(new Date());
                String id = tempList.get(position).getId();
                String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/DietPlanServlet?task=deleteFoodFromDietById&id=" + id + "&time=" + time + "&date=" + date;
                String data = SimpleHttpUtil.doGet(url);
                Gson gson = new Gson();
                Map<String, List<Dietbean>> resultMap = gson.fromJson(data, new TypeToken<Map<String, List<Dietbean>>>() {
                }.getType());
                List<Dietbean> morningListDietbean = resultMap.get(mapKey);
                lvfList = new ArrayList<ListViewFoodItem>();
                getDietbeanToLlistViewFoodItem(morningListDietbean, lvfList);

                handler.sendMessage(handler.obtainMessage(what, lvfList));
            }
        }).start();
    }

    public void toAssessmentActivity(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("loginUser", MODE_PRIVATE);
                String userInfoStr = sp.getString("userInfo", "");
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Userbean userInfo = gson.fromJson(userInfoStr, new TypeToken<Userbean>() {
                }.getType());
                String userName = userInfo.getUsername();

                String url = HttpConstants.SERVER_HOST + "NutritionMaster/servlet/AssessmentServlet";
                String result = SimpleHttpUtil.doPost(url, "task=assessment&userName=" + userName);
//                Map<String, Object> resultMap = gson.fromJson(result, new TypeToken<Map<String, Object>>(){}.getType());

                Intent it = new Intent(DietPlanActivity.this, AssessmentActivity.class);
                it.putExtra("result", result);
                startActivity(it);

            }
        }).start();


    }

}
