package com.example.administrator.nutritionmaster.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.nutritionmaster.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.formatter.ColumnChartValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleColumnChartValueFormatter;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class AssessmentActivity extends AppCompatActivity {

    //饼形图控件
    private PieChartView pie_chart;
    private PieChartView protein_pie_chart;

    //数据
    private PieChartData pieChardata;
    private ColumnChartView columnChartView;
    private ColumnChartView columnChartView2;
    List<SliceValue> values = new ArrayList<SliceValue>();

    private PieChartData proteinPieChartData;
    List<SliceValue> proteinValues = new ArrayList<SliceValue>();
    //定义数据，实际情况肯定不是这样写固定值的
    private int[] data = new int[4];

    private double[] MEData = new double[9];

    private double[] proteinData = new double[4];

    private Map<String, Object> bMap;

    private int[] colorData = {Color.parseColor("#ec063d"),
            Color.parseColor("#f1c704"),
            Color.parseColor("#c9c9c9"),
            Color.parseColor("#2bc208")};
    private String[] stateChar = {"早餐", "午餐", "晚餐", "加餐"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        pie_chart = (PieChartView) findViewById(R.id.pie_chart);

        protein_pie_chart = (PieChartView) findViewById(R.id.pie_chart2);

        columnChartView = (ColumnChartView) findViewById(R.id.test_content);
        columnChartView.setOnValueTouchListener(new ColumnChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, int i1, SubcolumnValue subcolumnValue) {
                Toast.makeText(AssessmentActivity.this, subcolumnValue.getValue() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {
//                Toast.makeText(AssessmentActivity.this, "黑人???", Toast.LENGTH_SHORT).show();
            }
        });

        columnChartView2 = (ColumnChartView) findViewById(R.id.test_content2);
        columnChartView2.setOnValueTouchListener(new ColumnChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, int i1, SubcolumnValue subcolumnValue) {
                Toast.makeText(AssessmentActivity.this, subcolumnValue.getValue() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {
//                Toast.makeText(AssessmentActivity.this, "黑人???", Toast.LENGTH_SHORT).show();
            }
        });

        pie_chart.setOnValueTouchListener(selectListener);//设置点击事件监听
        protein_pie_chart.setOnValueTouchListener(proteinSelectListener);

        TextView level1TV = (TextView) findViewById(R.id.level1_text);
        TextView level2TV = (TextView) findViewById(R.id.level2_text);
        TextView level3TV = (TextView) findViewById(R.id.level3_text);
        TextView level4TV = (TextView) findViewById(R.id.level4_text);
        TextView level5TV = (TextView) findViewById(R.id.level5_text);
        TextView level6TV = (TextView) findViewById(R.id.level6_text);

        Intent it = getIntent();
        String result = it.getStringExtra("result");
        Gson gson = new Gson();
        Map<String, Object> resultMap = gson.fromJson(result, new TypeToken<Map<String, Object>>() {
        }.getType());
        Map<String, Double> levelMap = (Map<String, Double>) resultMap.get("levelMap");
        Map<String, Double> heatMap = (Map<String, Double>) resultMap.get("heatMap");

        Map<String, Double> calciumMap = (Map<String, Double>) resultMap.get("calciumMap");
        Map<String, Double> vitaminAMap = (Map<String, Double>) resultMap.get("vitaminAMap");
        Map<String, Double> vitaminCMap = (Map<String, Double>) resultMap.get("vitaminCMap");
        Map<String, Double> vitaminEMap = (Map<String, Double>) resultMap.get("vitaminEMap");
        Map<String, Double> naMap = (Map<String, Double>) resultMap.get("naMap");

        Map<String, Double> proteinMap = (Map<String, Double>) resultMap.get("proteinMap");
        Map<String, Double> carbohydrateMap = (Map<String, Double>) resultMap.get("carbohydrateMap");
        Map<String, Double> fatMap = (Map<String, Double>) resultMap.get("fatMap");
        Map<String, Double> dfMap = (Map<String, Double>) resultMap.get("dfMap");

        Map<String, String> anaMap = (Map<String, String>) resultMap.get("anaMap");
        Map<String, String> anaMERMap = (Map<String, String>) resultMap.get("anaMERMap");
        bMap = (Map<String, Object>) resultMap.get("bMap");

        level1TV.setTextSize(18);
        level1TV.setTextColor(0xFF0091FF);
        level1TV.setText("所需热量计算情况：");

        level2TV.setTextSize(16);
        level2TV.setText("  很少运动：" + String.valueOf(levelMap.get("level1") + " 千卡"));

        level3TV.setTextSize(16);
        level3TV.setText("  轻度运动：" + String.valueOf(levelMap.get("level2") + " 千卡"));

        level4TV.setTextSize(16);
        level4TV.setText("  大量运动：" + String.valueOf(levelMap.get("level3") + " 千卡"));

        level5TV.setTextSize(16);
        level5TV.setText("  剧烈运动：" + String.valueOf(levelMap.get("level4") + " 千卡"));

        level6TV.setTextSize(16);
        level6TV.setText("  体重变化：" + String.valueOf(heatMap.get("morning") + heatMap.get("noon") + heatMap.get("dinner") + heatMap.get("add") - levelMap.get("level2")) + " 千卡");

        TextView heatAssessment = (TextView) findViewById(R.id.heat_assessment_text);
        heatAssessment.setTextSize(16);
        heatAssessment.setText(anaMap.get("anaResult"));

        TextView meAssementTV = (TextView) findViewById(R.id.me_assessment_text);
        meAssementTV.setTextSize(16);
        meAssementTV.setText(anaMERMap.get("anaMERResult"));

        data[0] = (int) (double) (heatMap.get("morning"));
        data[1] = (int) (double) (heatMap.get("noon"));
        data[2] = (int) (double) (heatMap.get("dinner"));
        data[3] = (int) (double) (heatMap.get("add"));

        MEData[0] = vitaminAMap.get("all");
        MEData[1] = vitaminCMap.get("all");
        MEData[2] = vitaminEMap.get("all");
        MEData[3] = naMap.get("all");
        MEData[4] = calciumMap.get("all");

        MEData[5] = proteinMap.get("all");
        System.out.println("+++++++++++++"+MEData[5]);
        MEData[6] = carbohydrateMap.get("all");
        MEData[7] = fatMap.get("all");
        MEData[8] = dfMap.get("all");

        proteinData[0] = proteinMap.get("morning");
        proteinData[1] = proteinMap.get("noon");
        proteinData[2] = proteinMap.get("dinner");
        proteinData[3] = proteinMap.get("add");


        for (int i=0;i<4;i++){
            System.out.println(proteinData[i]);
        }


        setPieChartData();
        initPieChart();


        setProteinPieChartData();
        initProteinPieChart();

        generateDefaultData();
    }


    /**
     * 获取数据
     */
    private void setPieChartData() {

        for (int i = 0; i < data.length; ++i) {
            SliceValue sliceValue = new SliceValue((float) data[i], colorData[i]);
            values.add(sliceValue);
        }
    }

    private void setProteinPieChartData() {
        for (int i = 0; i < proteinData.length; ++i) {
            SliceValue sliceValue = new SliceValue((float) proteinData[i], colorData[i]);
            proteinValues.add(sliceValue);
        }
    }

    /**
     * 初始化
     */
    private void initPieChart() {
        pieChardata = new PieChartData();
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(true);//是否是环形显示
        pieChardata.setValues(values);//填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        pieChardata.setCenterText1FontSize(16);
        pieChardata.setCenterText2FontSize(16);
        pie_chart.setPieChartData(pieChardata);
        pie_chart.setValueSelectionEnabled(true);//选择饼图某一块变大
        pie_chart.setAlpha(0.9f);//设置透明度
        pie_chart.setCircleFillRatio(1f);//设置饼图大小

    }

    private void initProteinPieChart() {
        proteinPieChartData = new PieChartData();
        proteinPieChartData.setHasLabels(true);//显示表情
        proteinPieChartData.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        proteinPieChartData.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        proteinPieChartData.setHasCenterCircle(true);//是否是环形显示
        proteinPieChartData.setValues(proteinValues);//填充数据
        proteinPieChartData.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        proteinPieChartData.setCenterCircleScale(0.5f);//设置环形的大小级别
        proteinPieChartData.setCenterText1FontSize(16);
        proteinPieChartData.setCenterText2FontSize(16);
        protein_pie_chart.setPieChartData(proteinPieChartData);
        protein_pie_chart.setValueSelectionEnabled(true);//选择饼图某一块变大
        protein_pie_chart.setAlpha(0.9f);//设置透明度
        protein_pie_chart.setCircleFillRatio(1f);//设置饼图大小

    }


    /**
     * 监听事件
     */
    private PieChartOnValueSelectListener selectListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            pieChardata.setCenterText1(stateChar[arg0]);
            pieChardata.setCenterText1Color(colorData[arg0]);
            pieChardata.setCenterText1FontSize(1);
            pieChardata.setCenterText2(calPercent(arg0));
            pieChardata.setCenterText2Color(colorData[arg0]);
            pieChardata.setCenterText2FontSize(1);
            Toast.makeText(AssessmentActivity.this, stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();
        }
    };

    private String calPercent(int i) {
        String result = "";
        int sum = 0;
        for (int i1 = 0; i1 < data.length; i1++) {
            sum += data[i1];
        }
        result = String.format("%.2f", (float) data[i] * 100 / sum) + "%";
        return result;
    }


    /**
     * 监听事件
     */
    private PieChartOnValueSelectListener proteinSelectListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            proteinPieChartData.setCenterText1(stateChar[arg0]);
            proteinPieChartData.setCenterText1Color(colorData[arg0]);
            proteinPieChartData.setCenterText1FontSize(1);
            proteinPieChartData.setCenterText2(proteinCalPercent(arg0));
            proteinPieChartData.setCenterText2Color(colorData[arg0]);
            proteinPieChartData.setCenterText2FontSize(1);
            Toast.makeText(AssessmentActivity.this, stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();
        }
    };

    private String proteinCalPercent(int i) {
        String result = "";
        int sum = 0;
        for (int i1 = 0; i1 < proteinData.length; i1++) {
            sum += proteinData[i1];
        }
        result = String.format("%.2f", (float) proteinData[i] * 100 / sum) + "%";
        return result;
    }

    private void generateDefaultData() {

        //一个柱状图需要一个柱子集合
        List<Column> columnList = new ArrayList<>();
        //每根柱子又可以分为多根柱子
        List<SubcolumnValue> subcolumnValueList;
        int columns = 5;//一共7根柱子
        for (int i = 0; i < columns; i++) {
            subcolumnValueList = new ArrayList<>();
            /*for (int j = 0; j < subColumn; j++) {
                //每根子柱子需要一个值和颜色
               subcolumnValueList.add(new SubcolumnValue((float) Math.random()*100, ChartUtils.pickColor()));
            }*/
            if (i == 0) {
                subcolumnValueList.add(new SubcolumnValue(new Float((Double) bMap.get("vitaminA")/1000), ChartUtils.COLOR_BLUE));
                subcolumnValueList.add(new SubcolumnValue((float) MEData[0] / 1000.0f, ChartUtils.COLOR_GREEN));
            } else if (i == 1) {
                subcolumnValueList.add(new SubcolumnValue(new Float((Double) bMap.get("vitaminC")), ChartUtils.COLOR_BLUE));
                subcolumnValueList.add(new SubcolumnValue((float) MEData[1], ChartUtils.COLOR_GREEN));
            } else if (i == 2) {
                subcolumnValueList.add(new SubcolumnValue(new Float((Double) bMap.get("vitaminE")), ChartUtils.COLOR_BLUE));
                subcolumnValueList.add(new SubcolumnValue((float) MEData[2], ChartUtils.COLOR_GREEN));
            } else if (i == 3) {
                subcolumnValueList.add(new SubcolumnValue(new Float((Double) bMap.get("Na")), ChartUtils.COLOR_BLUE));
                subcolumnValueList.add(new SubcolumnValue((float) MEData[3], ChartUtils.COLOR_GREEN));
            } else if (i == 4) {
                subcolumnValueList.add(new SubcolumnValue(new Float((Double) bMap.get("Ca")), ChartUtils.COLOR_BLUE));
                subcolumnValueList.add(new SubcolumnValue((float) MEData[4], ChartUtils.COLOR_GREEN));
            }

            //每根柱子需要一个子柱子集合
            Column column = new Column(subcolumnValueList);
            //这一步是能让圆柱标注数据显示带小数的重要一步
            ColumnChartValueFormatter chartValueFormatter = new SimpleColumnChartValueFormatter(0);
            column.setFormatter(chartValueFormatter);
            column.setHasLabels(true);//是否直接显示标注（其它的一些设置类似折线图）
            columnList.add(column);
        }
        ColumnChartData data = new ColumnChartData(columnList);
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("微量元素类别");
        axisY.setName("摄入量:毫克");
        axisY.hasLines();//是否显示网格线
        axisY.setTextColor(Color.BLACK);//颜色
        //给轴设置值
        List<AxisValue> list = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            list.add(new AxisValue(i).setLabel("周" + (i + 1)));//i代表位置，label则是在轴上该位置的标注
//        }
        list.add(new AxisValue(0).setLabel("维他命A"));
        list.add(new AxisValue(1).setLabel("维他命C"));
        list.add(new AxisValue(2).setLabel("维他命E"));
        list.add(new AxisValue(3).setLabel("钠元素"));
        list.add(new AxisValue(4).setLabel("钙元素"));

        //给x轴设置值
        axisX.setValues(list);
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        //设置是否让多根子柱子在同一根柱子上显示（会以断层的形式分开），由于这里子柱子只有一根，故设置true也无意义，读者可自行尝试
        data.setStacked(false);
        columnChartView.setColumnChartData(data);

        //一个柱状图需要一个柱子集合
        columnList = new ArrayList<>();
        columns = 4;//一共7根柱子
        for (int i = 0; i < columns; i++) {
            subcolumnValueList = new ArrayList<>();
            if (i == 0) {
                subcolumnValueList.add(new SubcolumnValue(new Float((Double) bMap.get("protein")), ChartUtils.COLOR_BLUE));
                subcolumnValueList.add(new SubcolumnValue((float) MEData[5], ChartUtils.COLOR_GREEN));
            } else if (i == 1) {
                subcolumnValueList.add(new SubcolumnValue(new Float((Double) bMap.get("carbohydrate")), ChartUtils.COLOR_BLUE));
                subcolumnValueList.add(new SubcolumnValue((float) MEData[6], ChartUtils.COLOR_GREEN));
            } else if (i == 2) {
                subcolumnValueList.add(new SubcolumnValue(new Float((Double) bMap.get("fat")), ChartUtils.COLOR_BLUE));
                subcolumnValueList.add(new SubcolumnValue((float) MEData[7], ChartUtils.COLOR_GREEN));
            } else if (i == 3) {
                subcolumnValueList.add(new SubcolumnValue(new Float((Double) bMap.get("df")), ChartUtils.COLOR_BLUE));
                subcolumnValueList.add(new SubcolumnValue((float) MEData[8], ChartUtils.COLOR_GREEN));
            }

            //每根柱子需要一个子柱子集合
            Column column = new Column(subcolumnValueList);
            //这一步是能让圆柱标注数据显示带小数的重要一步
            ColumnChartValueFormatter chartValueFormatter = new SimpleColumnChartValueFormatter(0);
            column.setFormatter(chartValueFormatter);
            column.setHasLabels(true);//是否直接显示标注（其它的一些设置类似折线图）
            columnList.add(column);
        }
        data = new ColumnChartData(columnList);
        axisX = new Axis();
        axisY = new Axis().setHasLines(true);
        axisX.setName("主要元素类别");
        axisY.setName("摄入量:克");
        axisY.hasLines();//是否显示网格线
        axisY.setTextColor(Color.BLACK);//颜色
        //给轴设置值
        list = new ArrayList<>();
        list.add(new AxisValue(0).setLabel("蛋白质"));
        list.add(new AxisValue(1).setLabel("碳水化合物"));
        list.add(new AxisValue(2).setLabel("脂肪"));
        list.add(new AxisValue(3).setLabel("膳食纤维"));

        //给x轴设置值
        axisX.setValues(list);
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        //设置是否让多根子柱子在同一根柱子上显示（会以断层的形式分开），由于这里子柱子只有一根，故设置true也无意义，读者可自行尝试
        data.setStacked(false);
        columnChartView2.setColumnChartData(data);
    }

    public void backToActivity(View view) {
        finish();
    }

}


