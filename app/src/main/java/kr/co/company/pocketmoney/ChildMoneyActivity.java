package kr.co.company.pocketmoney;

import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChildMoneyActivity extends AppCompatActivity {

    private TextView selected_day, selected_io, selected_money;
    private ImageView selected_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_money);//xml

        Bundle bundle = getIntent().getExtras();
        TextView selected_io = (TextView)findViewById(R.id.selected_io);

        selected_io.setText(bundle.getString("지출","no data"));

        /*
        Intent it = getIntent(); //인텐트 받기 선언

        // 이전에 보냈던 it_check의 값을 받아 str에 저장
        String str= it.getStringExtra("it_check"); //즉 str = "option1" 또는 "option2" 가 들어있음

        // 이 값을 이용하여 필요한 동작 구현
        if(str.equals("지출")){
            //str 값이 option1 이라면
            // 필요한 동작 코드 작성
            //텍스트, 색 바꾸기
            selected_io.setText("지출");

        }else if(str.equals("수입")){
            //str 값이 option2 라면
            // 필요한 동작 코드 작성
            selected_io.setText("수입");
        }

         */

    }


}
