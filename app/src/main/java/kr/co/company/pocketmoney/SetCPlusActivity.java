package kr.co.company.pocketmoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SetCPlusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cset_plus);

        Button modButton = (Button) findViewById(R.id.modButton);
        EditText eatModify = (EditText) findViewById(R.id.eatModify);
        EditText carModify = (EditText) findViewById(R.id.carModify);
        EditText prepareModify = (EditText) findViewById(R.id.prepareModify);
        EditText hobbyModify = (EditText) findViewById(R.id.hobbyModify);
        EditText etcModify = (EditText) findViewById(R.id.etcModify);

        modButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SetCActivity.class);

                String eatMod = eatModify.getText().toString();
                String carMod = carModify.getText().toString();
                String preMod = prepareModify.getText().toString();
                String hobbyMod = hobbyModify.getText().toString();
                String etcMod = etcModify.getText().toString();

                intent.putExtra("eat", eatMod);
                intent.putExtra("car", carMod);
                intent.putExtra("pre", preMod);
                intent.putExtra("hobby", hobbyMod);
                intent.putExtra("etc", etcMod);

                // Toast.makeText(SetCPlusActivity.this, "수정완료", Toast.LENGTH_LONG).show();

                startActivity(intent);
            }
        });
    }
}

