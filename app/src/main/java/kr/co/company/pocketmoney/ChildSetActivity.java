package kr.co.company.pocketmoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChildSetActivity extends AppCompatActivity {
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

        Intent recvIntent = getIntent();

        if (recvIntent != null) {
            eat = recvIntent.getIntExtra("eat", 0);
            eatBud.setText(String.valueOf(eat));

            car = recvIntent.getIntExtra("car", 0);
            carBud.setText(String.valueOf(car));

            pre = recvIntent.getIntExtra("pre", 0);
            preBud.setText(String.valueOf(pre));

            hobby = recvIntent.getIntExtra("hobby", 0);
            hobbyBud.setText(String.valueOf(hobby));

            etc = recvIntent.getIntExtra("etc", 0);
            etcBud.setText(String.valueOf(etc));
        }

        // 지출 표시
        // 임의로 설정함
        TextView outEat = (TextView) findViewById(R.id.outEat);
        TextView outCar = (TextView) findViewById(R.id.outCar);
        TextView outPrepare = (TextView) findViewById(R.id.outPrepare);
        TextView outHobby = (TextView) findViewById(R.id.outHobby);
        TextView outEtc = (TextView) findViewById(R.id.outEtc);
        outEat.setText("3000");
        outCar.setText("1000");
        outPrepare.setText("0");
        outHobby.setText("0");
        outEtc.setText("0");


        // 총 예산
        TextView budgetTotal = (TextView) findViewById(R.id.budgetTotal);
        int budget = eat + car + pre + hobby + etc;
        budgetTotal.setText(String.valueOf(budget));
        // 총 지출, 잔액 - 임의로 설정
        TextView outTotal = (TextView) findViewById(R.id.outTotal);
        TextView total = (TextView) findViewById(R.id.total);
        outTotal.setText("4000");
        total.setText("1000");



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

                budgetTotal.setText("");
                outTotal.setText("");
                total.setText("");
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
