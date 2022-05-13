package kr.co.company.pocketmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ChildMoneyPlusActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textview_date;
    private TextView text;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private RadioGroup radioGroup;
    int state;
    String strio;
    final Context context = this;
    private Button content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_money_plus); //xml : child_money_plus

        this.InitializeView();
        this.InitializeListener();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupClickListener);

        content = (Button) findViewById(R.id.content);
        text = (TextView)findViewById(R.id.category);

        content.setOnClickListener(this);

    }


    RadioGroup.OnCheckedChangeListener radioGroupClickListener = new RadioGroup.OnCheckedChangeListener() {
        @Override       //라디오버튼이 클릭되었을 때를 정의
        /*public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i==R.id.out){
                state = 1;
            } else if ( i == R.id.in){
                state = 2;
            }
        }*/
        public void onCheckedChanged(RadioGroup rg, int i) {
            RadioButton rb = (RadioButton)findViewById(i);
            int id = rg.getId();
            switch(id){
                case R.id.radioGroup:
                    strio = ((RadioButton)findViewById(i)).getText().toString();
                    break;
            }
        }
    };
    public String Selected(View v) {
        RadioButton out = (RadioButton) findViewById(R.id.out);
        RadioButton in = (RadioButton) findViewById(R.id.in);

        String resultText = ""; //체크된 값을 저장할 스트링 값
        if(out.isSelected()){
            resultText = "지출";
        } else if(in.isSelected()){
            resultText = "수입";
        }

        return resultText;
    }

    public void InitializeView()
    {
        textview_date = (TextView)findViewById(R.id.date);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textview_date.setText(year + "년  " + (monthOfYear+1) + "월  " + dayOfMonth + "일");   //textview 선택된 날짜로 변경
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2022, 4, 1);
        dialog.show();
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.content://v.getId()==R.id.content

                final CharSequence[] items = { "식비", "교통", "문화", "기타" };
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // 제목셋팅
                alertDialogBuilder.setTitle("카테고리 선택");
                alertDialogBuilder.setItems(items,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                // 프로그램을 종료한다
                                Toast.makeText(getApplicationContext(),
                                        items[id] + " 선택했습니다.",
                                        Toast.LENGTH_SHORT).show();
                                text.setText(items[id]);
                                dialog.dismiss();
                            }
                        });

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();
                break;
            /*
            case R.id.btn_write:
                Intent it = new Intent(this, writing.class); // MainActivity.java로 보내기 위한 인텐트 선언
                it.putExtra("it_select", Selected(v)); // it_check 라는 이름하에 체크된 값을 전달
                startActivity(it); // writing.java를 실행하면서 값을 전달
                break;  */

            case R.id.btn_write:
                Intent it = new Intent(this, ChildMoneyActivity.class); // MainActivity.java로 보내기 위한 인텐트 선언
                //it.putExtra("it_select", Selected(v)); // it_check 라는 이름하에 체크된 값을 전달
                Bundle bundle = new Bundle();
                bundle.putString("지출", strio);
                it.putExtras(bundle);
                startActivity(it); // writing.java를 실행하면서 값을 전달
                break;

            default:
                break;
        }

        /*
        if (v.getId() == R.id.btn_write) { //button_write 이라는 버튼이 생성되었다고 가정.
            Intent it = new Intent(this, writing.class); // MainActivity.java로 보내기 위한 인텐트 선언
            it.putExtra("it_select", Selected(v)); // it_check 라는 이름하에 체크된 값을 전달
            startActivity(it); // writing.java를 실행하면서 값을 전달
            }
        */
    }

}
