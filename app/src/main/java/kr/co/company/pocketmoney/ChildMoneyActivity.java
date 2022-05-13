package kr.co.company.pocketmoney;

import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kr.co.company.pocketmoney.R;

public class ChildMoneyActivity extends AppCompatActivity {

    private TextView selected_day, selected_io, selected_money;
    private ImageView selected_category;
    ImageButton imageButton;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_money);//xml

        imageButton = findViewById(R.id.plusMoney);

        selected_io = findViewById(R.id.selected_io);
        selected_money = findViewById(R.id.selected_money);
        selected_category = findViewById(R.id.selected_category);
        selected_day = findViewById(R.id.selected_day);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChildMoneyPlusActivity.class);
                startActivity(intent);
            }
        });

        ImageButton backButton = (ImageButton) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChildActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            //Intent intent = getIntent();
            selected_io.setText(bundle.getString("io", "null"));
            selected_money.setText(bundle.getString("money", "null"));
            category = bundle.getString("category");
            selected_day.setText(bundle.getString("day", "null"));

            if (category.equals("식비")) {
                selected_category.setImageResource(R.drawable.eat);
            } else if (category.equals("교통")) {
                selected_category.setImageResource(R.drawable.car);
            } else if (category.equals("준비물")) {
                selected_category.setImageResource(R.drawable.prepare);
            } else if (category.equals("문화")) {
                selected_category.setImageResource(R.drawable.hobby);
            } else if (category.equals("기타")) {
                selected_category.setImageResource(R.drawable.etc);
            }
        }

    }


}
