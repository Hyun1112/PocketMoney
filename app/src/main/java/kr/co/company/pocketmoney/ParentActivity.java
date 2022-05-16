package kr.co.company.pocketmoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ParentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_parent);

        // 용돈기입장 확인하기
        ImageButton checkP  = (ImageButton) findViewById(R.id.checkP);
        checkP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ParentMoneyActivity.class);
                startActivity(intent);
            }
        });

        // 용돈 설정하기
        ImageButton setP  = (ImageButton) findViewById(R.id.setP);
        setP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ParentSetActivity.class);
                startActivity(intent);
            }
        });

        // 리워드 설정하기
        ImageButton rewardP = (ImageButton) findViewById(R.id.rewardP);
        rewardP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ParentRewardActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
