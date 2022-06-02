package kr.co.company.pocketmoney;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChildSetActivity extends AppCompatActivity {

    DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cset);

        // 수정 버튼
        Button budgetMod = (Button) findViewById(R.id.budgetMod);
        budgetMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(getApplicationContext(), ChildSetPlusActivity.class);
                startActivity(sendIntent);
            }
        });

        // 예산 표시
        TextView eatBud = (TextView) findViewById(R.id.budgetEat);
        TextView carBud = (TextView) findViewById(R.id.budgetCar);
        TextView preBud = (TextView) findViewById(R.id.budgetPrepare);
        TextView hobbyBud = (TextView) findViewById(R.id.budgetHobby);
        TextView etcBud = (TextView) findViewById(R.id.budgetEtc);

        int eat = 0;
        int car = 0;
        int pre = 0;
        int hobby = 0;
        int etc = 0;

        Cursor cur2 = myDB.getB();
        if (cur2.moveToFirst()) {
            eat = Integer.parseInt(cur2.getString(1));
            car = Integer.parseInt(cur2.getString(2));
            pre = Integer.parseInt(cur2.getString(3));
            hobby = Integer.parseInt(cur2.getString(4));
            etc = Integer.parseInt(cur2.getString(5));
        }

        eatBud.setText(String.valueOf(eat));
        carBud.setText(String.valueOf(car));
        preBud.setText(String.valueOf(pre));
        hobbyBud.setText(String.valueOf(hobby));
        etcBud.setText(String.valueOf(etc));


        // 지출 표시
        TextView outEat = (TextView) findViewById(R.id.outEat);
        TextView outCar = (TextView) findViewById(R.id.outCar);
        TextView outPrepare = (TextView) findViewById(R.id.outPrepare);
        TextView outHobby = (TextView) findViewById(R.id.outHobby);
        TextView outEtc = (TextView) findViewById(R.id.outEtc);

        Cursor res = myDB.getM();
        Cursor dateCur = myDB.getS();

        int bal = 0; // 잔액
        int out = 0; // 지출
        int out_eat = 0;
        int out_car = 0;
        int out_pre = 0;
        int out_hob = 0;
        int out_etc = 0;

        String date = "0000년  00월  00일";

        if (dateCur.moveToFirst()) {
            date = dateCur.getString(1);
        }

        while(res.moveToNext()){
            // 데이터 읽어오기
            String getIO = res.getString(1);
            String getDay = res.getString(2);
            String getMoney = res.getString(3);
            String getContent = res.getString(4);


            // 날짜 비교
            String intDay = getDay.replaceAll("[^0-9]", "");
            String intDate = date.replaceAll("[^0-9]", "");

            int y1 = Integer.parseInt(intDay.substring(0, 4));
            int y2 = Integer.parseInt(intDate.substring(0, 4));

            int m1 = Integer.parseInt(intDay.substring(4, 6));
            int m2 = Integer.parseInt(intDate.substring(4, 6));

            int d1 = Integer.parseInt(intDay.substring(6, 8));
            int d2 = Integer.parseInt(intDate.substring(6, 8));

            int flagD = 0;

            if (y1 > y2) {
                flagD = 1;
            }
            else if (y1 == y2) {
                if (m1 > m2) {
                    flagD = 1;
                }
                else if (m1 == m2) {
                    if (d1 > d2) {
                        flagD = 1;
                    }
                }
            }


            if (getIO.equals("지출")) {
                bal -= Integer.parseInt(getMoney);

                if (flagD == 1) {
                    out += Integer.parseInt(getMoney);

                    if (getContent.equals("식비"))
                        out_eat += Integer.parseInt(getMoney);
                    else if (getContent.equals("교통"))
                        out_car += Integer.parseInt(getMoney);
                    else if (getContent.equals("준비물"))
                        out_pre += Integer.parseInt(getMoney);
                    else if (getContent.equals("문화"))
                        out_hob += Integer.parseInt(getMoney);
                    else
                        out_etc += Integer.parseInt(getMoney);
                }
            }
            else
                bal += Integer.parseInt(getMoney);
        }

        outEat.setText(Integer.toString(out_eat));
        outCar.setText(Integer.toString(out_car));
        outPrepare.setText(Integer.toString(out_pre));
        outHobby.setText(Integer.toString(out_hob));
        outEtc.setText(Integer.toString(out_etc));


        // 총 예산, 지출, 잔액
        TextView budgetTotal = (TextView) findViewById(R.id.budgetTotal);
        int budget = eat + car + pre + hobby + etc;
        budgetTotal.setText(String.valueOf(budget));

        TextView outTotal = (TextView) findViewById(R.id.outTotal);
        outTotal.setText(Integer.toString(out));


        TextView total = (TextView) findViewById(R.id.total);
        total.setText(Integer.toString(bal));



        // 초기화 버튼
        Button budgetReset = (Button) findViewById(R.id.budgetReset);
        budgetReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 예산, 잔액 모두 0으로 바꾸기
                eatBud.setText("0");
                outEat.setText("0");

                carBud.setText("0");
                outCar.setText("0");

                preBud.setText("0");
                outPrepare.setText("0");

                hobbyBud.setText("0");
                outHobby.setText("0");

                etcBud.setText("0");
                outEtc.setText("0");

                budgetTotal.setText("0");
                outTotal.setText("0");
                total.setText("0");


                // 데이터 지우기
                Integer deleteRows = myDB.deleteB("1");
                if(deleteRows>0)
                    Toast.makeText(ChildSetActivity.this,"초기화되었습니다.",Toast.LENGTH_LONG ).show();
                else
                    Toast.makeText(ChildSetActivity.this,"초기화 실패",Toast.LENGTH_LONG ).show();


                // 현재 날짜 불러오기
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년  MM월  dd일");
                String getTime = sdf.format(date);


                // 데이터 없으면 삽입, 있으면 수정
                Cursor cur3 = myDB.getS();

                if (cur3.moveToFirst()) {
                    myDB.updateS("1", getTime);
                }
                else {
                    myDB.insertS("1", getTime);
                }
            }
        });

        // 뒤로가기 버튼
        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChildActivity.class);
                startActivity(intent);
            }
        });
    }
}
