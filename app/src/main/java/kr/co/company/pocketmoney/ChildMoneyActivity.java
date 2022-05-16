package kr.co.company.pocketmoney;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.company.pocketmoney.R;

public class ChildMoneyActivity extends AppCompatActivity {

    private MoneyAdapter adapter;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmoney);


        // 잔액
        TextView balance = (TextView) findViewById(R.id.balance);
        int bal = 0;
        balance.setText(String.valueOf(bal));

        // 글쓰기 버튼
        imageButton = findViewById(R.id.plusMoney);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChildMoneyPlusActivity.class);
                startActivity(intent);
            }
        });


        // 뒤로가기 버튼
        ImageButton backButton = (ImageButton) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChildActivity.class);
                startActivity(intent);
            }
        });


        // 리사이클러 뷰
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        final MoneyAdapter adapter = new MoneyAdapter();
        recyclerView.setAdapter(adapter);

        // 일단 임의로 설정..
        adapter.addItem(new MoneyItem("지출", "2022년 4월 30일", "3000", "식비"));
        adapter.addItem(new MoneyItem("수입", "2022년 5월 1일", "5000", "기타"));
        adapter.notifyDataSetChanged();
        bal = bal + 5000 - 3000;
        balance.setText(String.valueOf(bal));



        // 글쓰기 -> 인텐트 가져오기
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            // 글쓰기에서 담아온 정보들을 리사이클러뷰에 띄움
            String io = bundle.getString("io", "null");
            int m = bundle.getInt("money");
            String money = Integer.toString(m);
            String category = bundle.getString("category");
            String day = bundle.getString("day", "null");

            adapter.addItem(new MoneyItem(io, day, money, category));
            adapter.notifyDataSetChanged();

            if (io.equals("수입")) {
                bal += m;
            }
            else {
                bal -= m;
            }

            balance.setText(String.valueOf(bal));

        }

    }

}
