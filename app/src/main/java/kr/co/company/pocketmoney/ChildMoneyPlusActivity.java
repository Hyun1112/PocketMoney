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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ChildMoneyPlusActivity extends AppCompatActivity {

    private TextView textview_date, text;
    EditText money_text;
    public String ioselected = "지출";
    String selected_category, money, selected_day;

    private RadioGroup radioGroup;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    int state, year, monthOfYear, dayOfMonth;

    final Context context = this;
    private Button content;
    Button btn_write;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_money_plus); //xml : child_money_plus

        ImageButton backButton = (ImageButton) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChildMoneyActivity.class);
                startActivity(intent);
            }
        });

        this.InitializeView();
        this.InitializeListener();

        content = (Button)findViewById(R.id.content);
        text = (TextView)findViewById(R.id.category);
        money_text = (EditText)findViewById(R.id.money_text);
        btn_write = findViewById(R.id.btn_write);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override       //라디오버튼이 클릭되었을 때를 정의
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.out){
                    ioselected = "지출";
                } else if(checkedId == R.id.in){
                    ioselected = "수입";
                }
            }
        });

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = { "식비", "교통", "준비물", "문화", "기타" };
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

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
                                selected_category = text.getText().toString();
                                //selected_category = items[id].toString();
                                dialog.dismiss();
                            }
                        });

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();
            }
        });


        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChildMoneyActivity.class);
                Bundle bundle = new Bundle();

                money = money_text.getText().toString();
                System.out.println(money);

                //int money = Integer.parseInt(money_text.getText().toString());
                bundle.putString("io", ioselected);
                bundle.putString("money", money);
                bundle.putString("category", selected_category);
                bundle.putString("day", selected_day);

                intent.putExtras(bundle);
                startActivity(intent);
            }

        });

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
                textview_date.setText(year + "년  " + (monthOfYear+1) + "월  " + dayOfMonth + "일");
                selected_day=textview_date.getText().toString();    //textview 선택된 날짜로 변경
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2022, 4, 1);
        dialog.show();
    }

}
