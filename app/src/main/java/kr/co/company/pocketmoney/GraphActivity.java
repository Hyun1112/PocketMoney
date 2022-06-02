package kr.co.company.pocketmoney;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    PieChart pieChart;
    ExpensesByTag expensesByTag = null;
    String spinner_date = "06월";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgraph);

        // 뒤로가기 버튼
        ImageButton backButton = (ImageButton) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChildMoneyActivity.class);
                startActivity(intent);
            }
        });

        expensesByTag = new ExpensesByTag();
        //setChart(expensesByTag);

        // Spinner
        Spinner monthSpinner = (Spinner)findViewById(R.id.spinner_month);
        monthSpinner.setSelection(5);   // 초기선택 05월로 세팅

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_date = monthSpinner.getSelectedItem().toString();   //선택된 nn월
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        setPieChart(expensesByTag);
    }

    private class ExpensesByTag{
        private int eat = 0;
        private int car = 0;
        private int prepare = 0;
        private int hobby = 0;
        private int etc = 0;

        ExpensesByTag(){
            String tag = null;
            String date = null;
            int exchangedAmount = 0;
            //데이터베이스 값 불러오기
            DatabaseHelper helper = new DatabaseHelper(GraphActivity.this);
            SQLiteDatabase db = helper.getReadableDatabase();

            String sql = "select date, money, content from money_table where io LIKE '지출'";
            Cursor cursor = null;
            try{
                cursor = db.rawQuery(sql, null);
            } catch (Exception e){}

            if(cursor != null){
                while(cursor.moveToNext()){
                    date = cursor.getString(0); //database : date
                    exchangedAmount = cursor.getInt(1); //database : money
                    tag = cursor.getString(2);  //database : content

                    //날짜 파싱
                    String intDate = date.replaceAll("[^0-9]", "");
                    //데베에 저장된 월 / 2022년  05월  24일
                    int m2 = Integer.parseInt(intDate.substring(4, 6)); //4번부터 6번 앞까지 데베값

                    String intSpinner = spinner_date.replaceAll("[^0-9]","0");
                    int m1 = Integer.parseInt(intSpinner.substring(0,2));  //0번부터 2번 앞까지 스피너 값



                    //선택된 월에서만 계산하기
                    if(m1 == m2){
                        switch (tag){
                            case "식비":
                                eat += exchangedAmount;
                                break;
                            case "교통":
                                car += exchangedAmount;
                                break;
                            case "준비물":
                                prepare += exchangedAmount;
                                break;
                            case "문화":
                                hobby += exchangedAmount;
                                break;
                            case "기타":
                                etc += exchangedAmount;
                        }
                    }
                }
            }

            cursor.close();
            db.close();
        }
        private int getFoodExpenses(){return eat;}
        private int getTransExpenses(){return car;}
        private int getPrepare(){return prepare;}
        private int getHobby(){return hobby;}
        private int getEtcExpenses(){return etc;}
        private Integer[] getExpensesArray(){
            String[] tags = {"식비", "교통", "준비물", "문화", "기타"};
            Integer[] expensesArray = new Integer[tags.length];

            expensesArray[0] = new Integer(getFoodExpenses());
            expensesArray[1] = new Integer(getTransExpenses());
            expensesArray[2] = new Integer(getPrepare());
            expensesArray[3] = new Integer(getHobby());
            expensesArray[4] = new Integer(getEtcExpenses());

            return expensesArray;
        }
    }

    private  void setPieChart(ExpensesByTag expensesByTag){
        if(expensesByTag == null) return;

        pieChart = (PieChart)findViewById(R.id.piechart);

        pieChart.setUsePercentValues(false); //퍼센트로 넣을 것인가
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5); //상하좌우 간격
        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true); //도넛모양
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        Description description = new Description();
        description.setText("지출 통계"); //라벨
        description.setTextSize(15);
        pieChart.setDescription(description); //우측 하단 표시

        ArrayList<PieEntry> val = new ArrayList<>(); // 그래프에 표시할 값
        Integer[] expenses = expensesByTag.getExpensesArray();
        String[] tags = {"식비", "교통", "준비물", "문화", "기타"};

        for(int i = 0; i < expenses.length; ++i){
            if(expenses[i] != 0) val.add(new PieEntry(expenses[i],tags[i]));
        }

        PieDataSet pieDataSet = new PieDataSet(val,null);
        //pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(1.5f); // 그래프 항목 사이 간격
        pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        int colorBlack = Color.parseColor("#000000");
        pieChart.setEntryLabelColor(colorBlack);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextColor(Color.BLACK); // 그래프에 표시되는 값들의 색
        pieData.setValueTextSize(18f); // 그래프에 표시되는 값들의 크기


        pieChart.setData(pieData);

    }
}