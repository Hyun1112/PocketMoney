package kr.co.company.pocketmoney;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ChildSetPlusActivity extends AppCompatActivity {

    DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cset_plus);

        LinearLayout modButton = (LinearLayout) findViewById(R.id.modButton);
        EditText eatModify = (EditText) findViewById(R.id.eatModify);
        EditText carModify = (EditText) findViewById(R.id.carModify);
        EditText prepareModify = (EditText) findViewById(R.id.prepareModify);
        EditText hobbyModify = (EditText) findViewById(R.id.hobbyModify);
        EditText etcModify = (EditText) findViewById(R.id.etcModify);

        modButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChildSetActivity.class);

                String eat = eatModify.getText().toString();
                String car = carModify.getText().toString();
                String pre = prepareModify.getText().toString();
                String hob = hobbyModify.getText().toString();
                String etc = etcModify.getText().toString();

                Cursor cur1 = myDB.getB();

                if (cur1.moveToFirst()) {
                    myDB.updateB("1", String.valueOf(eat), String.valueOf(car), String.valueOf(pre), String.valueOf(hob), String.valueOf(etc));
                }
                else {
                    myDB.insertB("1", String.valueOf(eat), String.valueOf(car), String.valueOf(pre), String.valueOf(hob), String.valueOf(etc));
                }

                // Toast.makeText(SetCPlusActivity.this, "수정완료", Toast.LENGTH_LONG).show();

                startActivity(intent);
            }
        });

        // 뒤로가기 버튼
        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChildSetActivity.class);
                startActivity(intent);
            }
        });
    }
}