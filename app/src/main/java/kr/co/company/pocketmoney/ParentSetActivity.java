package kr.co.company.pocketmoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParentSetActivity extends AppCompatActivity {
    DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pset);

        Button moneyP = (Button) findViewById(R.id.moneyP);
        moneyP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 설정한 용돈
                EditText inputMoney = (EditText) findViewById(R.id.textView);
                String money = inputMoney.getText().toString();

                // 날짜
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년  MM월  dd일");
                String getTime = sdf.format(date);

                // 수입에 추가
                myDB.insertM("수입", getTime, money, "기타");


                // 완료
                Toast.makeText(ParentSetActivity.this, "설정이 완료되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

        // 뒤로가기 버튼
        ImageButton backButton = (ImageButton) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ParentActivity.class);
                startActivity(intent);
            }
        });

    }
}