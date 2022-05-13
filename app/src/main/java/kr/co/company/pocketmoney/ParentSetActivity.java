package kr.co.company.pocketmoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ParentSetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmoney);

        Button moneyP = (Button) findViewById(R.id.moneyP);
        moneyP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 설정한 용돈
                EditText inputMoney = (EditText) findViewById(R.id.textView);
                String money = inputMoney.getText().toString();

                // 수입으로 보내기 ?

                // 완료
                Toast.makeText(ParentSetActivity.this, "설정이 완료되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

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