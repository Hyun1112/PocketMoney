package kr.co.company.pocketmoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SetCActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cset);

        // 수정 버튼
        Button budgetMod = (Button) findViewById(R.id.budgetMod);
        budgetMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(getApplicationContext(), SetCPlusActivity.class);
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

        Intent recvIntent = getIntent();

        if (recvIntent != null) {
            eatBud.setText(recvIntent.getStringExtra("eat"));
            carBud.setText(recvIntent.getStringExtra("car"));
            preBud.setText(recvIntent.getStringExtra("pre"));
            hobbyBud.setText(recvIntent.getStringExtra("hobby"));
            etcBud.setText(recvIntent.getStringExtra("etc"));
//            eat = Integer.valueOf(recvIntent.getStringExtra("eat"));
//            eatBud.setText(String.valueOf(eat));
//
//            car = Integer.valueOf(recvIntent.getStringExtra("car"));
//            carBud.setText(String.valueOf(car));
//
//            pre = Integer.valueOf(recvIntent.getStringExtra("pre"));
//            preBud.setText(String.valueOf(pre));
//
//            hobby = Integer.valueOf(recvIntent.getStringExtra("hobby"));
//            hobbyBud.setText(String.valueOf(hobby));
//
//            etc = Integer.valueOf(recvIntent.getStringExtra("etc"));
//            etcBud.setText(String.valueOf(etc));
        }

        // 지출 표시
        // 합치기
        TextView outEat = (TextView) findViewById(R.id.outEat);
        TextView outCar = (TextView) findViewById(R.id.outCar);
        TextView outPrepare = (TextView) findViewById(R.id.outPrepare);
        TextView outHobby = (TextView) findViewById(R.id.outHobby);
        TextView outEtc = (TextView) findViewById(R.id.outEtc);

        // 총액 표시
        TextView budgetTotal = (TextView) findViewById(R.id.budgetTotal);
        int budget = eat + car + pre + hobby + etc;
        budgetTotal.setText(String.valueOf(budget));
        // 합치기
        TextView outTotal = (TextView) findViewById(R.id.outTotal);
        TextView total = (TextView) findViewById(R.id.total);


        // 초기화 버튼
        Button budgetReset = (Button) findViewById(R.id.budgetReset);
        budgetReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eatBud.setText("");
                outEat.setText("");

                carBud.setText("");
                outCar.setText("");

                preBud.setText("");
                outPrepare.setText("");

                hobbyBud.setText("");
                outHobby.setText("");

                etcBud.setText("");
                outEtc.setText("");
            }
        });
    }
}
