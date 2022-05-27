package kr.co.company.pocketmoney;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ParentMoneyActivity extends AppCompatActivity {

    DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmoney);

        // 일단 글쓰기 안 보이게만 해놓음,, 수정해야 됨
        ImageButton plusMoney = (ImageButton) findViewById(R.id.plusMoney);
        plusMoney.setVisibility(View.INVISIBLE);

        // 잔액
        TextView balance = (TextView) findViewById(R.id.balance);
        int bal = 0;
        balance.setText(String.valueOf(bal));

        // 리사이클러 뷰
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        final MoneyAdapter adapter = new MoneyAdapter();
        recyclerView.setAdapter(adapter);

        /*
        // 일단 임의로 설정..
        adapter.addItem(new MoneyItem("지출", "2022년 4월 30일", "3000", "식비"));
        adapter.addItem(new MoneyItem("수입", "2022년 5월 1일", "5000", "기타"));
        adapter.addItem(new MoneyItem("지출", "2022년 5월 18일", "1000", "교통"));
        adapter.notifyDataSetChanged();
        bal = bal + 5000 - 3000;
        bal -= 1000; //
        balance.setText(String.valueOf(bal));
        */

        // 데이터 조회
        Cursor res = myDB.getM();

        while(res.moveToNext()){
            String getIO = res.getString(1);
            String getDay = res.getString(2);
            String getMoney = res.getString(3);
            String getContent = res.getString(4);

            adapter.addItem(new MoneyItem(getIO, getDay, getMoney, getContent));


            if (getIO.equals("수입"))
                bal += Integer.parseInt(getMoney);
            else
                bal -= Integer.parseInt(getMoney);
        }

        balance.setText(Integer.toString(bal));
        adapter.notifyDataSetChanged();

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
