package kr.co.company.pocketmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.database.Cursor;

public class ChildRewardActivity extends AppCompatActivity {
    TextView txt_reward[] = new TextView[5];
    CheckBox btn_selector[] = new CheckBox[5];
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

        btn_selector[0] = findViewById(R.id.btn_selector1);
        btn_selector[1] = findViewById(R.id.btn_selector2);
        btn_selector[2] = findViewById(R.id.btn_selector3);
        btn_selector[3] = findViewById(R.id.btn_selector4);
        btn_selector[4] = findViewById(R.id.btn_selector5);

        // 클릭시 선택.
        btn_selector[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox)view).isChecked()) {
                    btn_selector[0].setChecked(true);
                    myDB.updateR(1,"true");
                }
                else {
                    btn_selector[0].setChecked(false);
                    myDB.updateR(1,"false");
                }
                //btn_selector[0].setChecked(true);
                //myDB.updateR(1,"true");
            }
        });

        btn_selector[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_selector[1].isChecked()) {
                    btn_selector[1].setChecked(true);
                    myDB.updateR(2,"true");
                }
                else {
                    btn_selector[1].setChecked(false);
                    myDB.updateR(2,"false");
                }
                //btn_selector[1].setChecked(true);
                //myDB.updateR(2,"true");
            }

        });
        btn_selector[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_selector[2].isChecked()) {
                    btn_selector[2].setChecked(true);
                    myDB.updateR(3,"true");
                }
                else {
                    btn_selector[2].setChecked(false);
                    myDB.updateR(3,"false");
                }
                //btn_selector[2].setChecked(true);
                //myDB.updateR(3,"true");
            }

        });
        btn_selector[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_selector[3].isChecked()) {
                    btn_selector[3].setChecked(true);
                    myDB.updateR(4,"true");
                }
                else {
                    btn_selector[3].setChecked(false);
                    myDB.updateR(4,"false");
                }
                //btn_selector[3].setChecked(true);
                //myDB.updateR(4,"true");
            }

        });
        btn_selector[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_selector[4].isChecked()) {
                    btn_selector[4].setChecked(true);
                    myDB.updateR(5,"true");
                }
                else {
                    btn_selector[4].setChecked(false);
                    myDB.updateR(5,"false");
                }
                //btn_selector[4].setChecked(true);
                //myDB.updateR(5,"true");
            }

        });

        // 데이터 조회
        Cursor res = myDB.getR();
        int i = 0;

        while (res.moveToNext()) {
            String getReward = res.getString(1);
            String getChecked = res.getString(2);
            txt_reward[i].setTextSize(18);
            txt_reward[i].setText(getReward);

            if (getChecked.equals("true"))
                btn_selector[i].setChecked(true);
            else
                btn_selector[i].setChecked(false);

            i++;
        }
    }
}