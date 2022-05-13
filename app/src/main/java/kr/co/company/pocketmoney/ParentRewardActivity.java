package kr.co.company.pocketmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class ParentRewardActivity extends AppCompatActivity {
    private Context mContext;
    private ArrayList<RewardItem> mArrayList;
    private RewardAdapter mRewardAdapter;
    private RecyclerView mRecyclerView;
    private EditText edit_name;
    private ImageButton btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_parent_reward);
        mContext = getApplicationContext ();
        edit_name = findViewById (R.id.edit_name);
        btn_save = findViewById (R.id.btn_save);
        mRecyclerView = findViewById (R.id.recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager (mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager (layoutManager);

        mArrayList = new ArrayList<> ();
        mRewardAdapter = new RewardAdapter(mContext, mArrayList);
        mRecyclerView.setAdapter (mRewardAdapter);

        //리사이클러뷰 아이템 클릭 이벤트
        mRewardAdapter.setOnItemClickListener (new RewardAdapter.OnItemClickListener () {

            //아이템 클릭시 토스트메시지
            @Override
            public void onItemClick (View v,int position){
                String name = mArrayList.get(position).getName();
                Toast.makeText(mContext, "집안일 : " + name, Toast.LENGTH_SHORT).show();
            }

            //삭제
            @Override
            public void onDeleteClick(View v, int position) {
                mArrayList.remove(position);
                mRewardAdapter.notifyItemRemoved(position);
            }

        });

        //버튼 클릭이벤트
        //내용을 입력한 후 버튼을 클릭하면 어레이리스트에 데이터를 담고 리사이클러뷰에 띄운다.
        btn_save.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(edit_name.getText ().length ()==0){
                    Toast.makeText (mContext,"내용을 입력해주세요", Toast.LENGTH_SHORT).show ();
                }else{
                    String name = edit_name.getText ().toString ();
                    edit_name.setText ("");
                    RewardItem rewardItem = new RewardItem(name);

                    mArrayList.add (rewardItem);
                    mRewardAdapter.notifyItemInserted (mArrayList.size ()-1);
                }
            }
        });

    }
}