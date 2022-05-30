package kr.co.company.pocketmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChildRewardActivity extends AppCompatActivity {
    TextView txt_reward[] = new TextView[5];
    DatabaseHelper myDB = new DatabaseHelper(this);

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

        txt_reward[0] = findViewById(R.id.txt_reward1);
        txt_reward[1] = findViewById(R.id.txt_reward2);
        txt_reward[2] = findViewById(R.id.txt_reward3);
        txt_reward[3] = findViewById(R.id.txt_reward4);
        txt_reward[4] = findViewById(R.id.txt_reward5);

        // 데이터 조회
        Cursor res = myDB.getR();
        int i = 0;

        while(res.moveToNext()) {
            String getReward = res.getString(0);
            txt_reward[i].setTextSize(18);
            txt_reward[i].setText(getReward);
            i++;

            //putExtra()로 전달한 값을 출력
            //Intent receiveIntent = getIntent();
            //String receiveMessage = receiveIntent.getStringExtra("message");
            //txt_reward = (TextView) findViewById(R.id.txt_reward1);
            //txt_reward.setTextSize(18);
            //txt_reward.setText(receiveMessage);
        }


        final TextView btn_selector1 = (TextView) findViewById(R.id.btn_selector1);
        final TextView btn_selector2 = (TextView) findViewById(R.id.btn_selector2);
        final TextView btn_selector3 = (TextView) findViewById(R.id.btn_selector3);
        final TextView btn_selector4 = (TextView) findViewById(R.id.btn_selector4);
        final TextView btn_selector5 = (TextView) findViewById(R.id.btn_selector5);


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