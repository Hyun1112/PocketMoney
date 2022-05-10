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
                Intent intent = new Intent(getApplicationContext(), SetCPlusActivity.class);
                startActivity(intent);
            }
        });

        // 예산 표시
        TextView eatBud = (TextView) findViewById(R.id.budgetEat);
        TextView carBud = (TextView) findViewById(R.id.budgetCar);
        TextView preBud = (TextView) findViewById(R.id.budgetPrepare);
        TextView hobbyBud = (TextView) findViewById(R.id.budgetHobby);
        TextView etcBud = (TextView) findViewById(R.id.budgetEtc);

        Intent intent = getIntent();

        int eat = intent.getExtras().getInt("eat");
        eatBud.setText(String.valueOf(eat));

        int car = intent.getExtras().getInt("car");
        carBud.setText(String.valueOf(car));

        int pre = intent.getExtras().getInt("pre");
        preBud.setText(String.valueOf(pre));

        int hobby = intent.getExtras().getInt("hobby");
        hobbyBud.setText(String.valueOf(hobby));

        int etc = intent.getExtras().getInt("etc");
        etcBud.setText(String.valueOf(etc));

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
