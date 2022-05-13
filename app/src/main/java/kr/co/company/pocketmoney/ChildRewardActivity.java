package kr.co.company.pocketmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChildRewardActivity extends AppCompatActivity {
    TextView txt_reward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_reward);

        ImageButton backButton = (ImageButton) findViewById(R.id.btn_cback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChildActivity.class);
                startActivity(intent);
            }
        });

        final TextView btn_selector1 = (TextView) findViewById(R.id.btn_selector1);
        final TextView btn_selector2 = (TextView) findViewById(R.id.btn_selector2);
        final TextView btn_selector3 = (TextView) findViewById(R.id.btn_selector3);
        final TextView btn_selector4 = (TextView) findViewById(R.id.btn_selector4);
        final TextView btn_selector5 = (TextView) findViewById(R.id.btn_selector5);

        //putExtra()로 전달한 값을 출력
        Intent receiveIntent = getIntent();
        String receiveMessage = receiveIntent.getStringExtra("message");
        txt_reward = (TextView) findViewById(R.id.txt_reward1);
        txt_reward.setTextSize(18);
        txt_reward.setText(receiveMessage);

        // 클릭시 선택.
        btn_selector1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_selector1.setSelected(true);
            }
        });

        btn_selector2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_selector2.setSelected(true);
            }
        });

        btn_selector3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_selector3.setSelected(true);
            }
        });

        btn_selector4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_selector4.setSelected(true);
            }
        });

        btn_selector5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_selector5.setSelected(true);
            }
        });

    }

}