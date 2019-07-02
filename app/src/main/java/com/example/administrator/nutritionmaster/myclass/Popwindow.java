package com.example.administrator.nutritionmaster.myclass;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.nutritionmaster.R;
import com.example.administrator.nutritionmaster.entity.ListViewFoodItem;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class Popwindow extends PopupWindow{

    private TextView btn_add, btn_cancel,remove_food_btn,add_food_btn;
    private View mMenuView;
    private Activity mContext;
    public Popwindow(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_window, null);
        btn_add = (TextView) mMenuView.findViewById(R.id.btn_ok);
        btn_cancel = (TextView) mMenuView.findViewById(R.id.btn_cancel);

//        add_food_btn = (TextView) mMenuView.findViewById(R.id.add_food_btn);
//        remove_food_btn = (TextView) mMenuView.findViewById(R.id.remove_food_btn);


        //取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置按钮监听
        btn_add.setOnClickListener(itemsOnClick);
        btn_cancel.setOnClickListener(itemsOnClick);
//        add_food_btn.setOnClickListener(itemsOnClick);
//        remove_food_btn.setOnClickListener(itemsOnClick);



        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        //this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });

//        mMenuView.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                // popupWindow隐藏时恢复屏幕正常透明度
//                setBackgroundAlpha(1.0f);
//            }
//        });

    }

//    public void setBackgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
//                .getAttributes();
//        lp.alpha = bgAlpha;
//        ((Activity) mContext).getWindow().setAttributes(lp);
//    }

    /**
     * 想popwindow的控件控件中添值
     * @param foodName
     * @param foodHeat
     * @param foodId
     * @param foodTime
     */
    public void setPopUpResult(String foodName,String foodHeat,String foodId,String foodTime){
        TextView foodHeatTV = getContentView().findViewById(R.id.food_heat);
        TextView foodNameTV = getContentView().findViewById(R.id.food_name_text);
        TextView foodIdTV = getContentView().findViewById(R.id.food_id);
        TextView foodTimeTV = getContentView().findViewById(R.id.food_time);

        foodHeatTV.setText(foodHeat);
        foodNameTV.setText(foodName);
        foodIdTV.setText(foodId);
        foodTimeTV.setText(foodTime);
    }

//    public void setFoodCount(int param){
//        TextView foodCountTV = (TextView) getContentView().findViewById(R.id.food_count);
//        int currentCount = Integer.valueOf(foodCountTV.getText().toString());
//        if(param == 1){
//            currentCount++;
//        }else{
//            if(currentCount > 0){
//                currentCount--;
//            }
//
//        }
//
//        foodCountTV.setText(String.valueOf(currentCount));
//    }

    /**
     * 选择食物
     * 点击popwindow的确定按钮之后的处理
     * @param foodLItemist
     */
    public void addFoodToList(List<ListViewFoodItem> foodLItemist){
        EditText foodCountTV = (EditText) getContentView().findViewById(R.id.food_count_text);
        TextView foodHeatTV = (TextView) getContentView().findViewById(R.id.food_heat);
        TextView foodNameTV = getContentView().findViewById(R.id.food_name_text);
        TextView foodIdTV = getContentView().findViewById(R.id.food_id);
        TextView foodTimeTV = getContentView().findViewById(R.id.food_time);
        float foodHeat = Float.valueOf(foodHeatTV.getText().toString());
        float foodCount =(Float.valueOf(foodCountTV.getText().toString()));
        int amountHeat =(int)(foodHeat * (foodCount/100.0f));
        ListViewFoodItem lvf = new ListViewFoodItem();
        //lvf.setFoodId();
        lvf.setFoodName(foodNameTV.getText().toString());
        lvf.setFoodHeat(foodHeatTV.getText().toString());
        lvf.setHeatAmount(String.valueOf(amountHeat));
        lvf.setFoodId(Integer.valueOf(foodIdTV.getText().toString()));
        lvf.setTime(Integer.valueOf(foodTimeTV.getText().toString()));
        lvf.setCount((int)(foodCount));
        foodLItemist.add(lvf);
    }



}
