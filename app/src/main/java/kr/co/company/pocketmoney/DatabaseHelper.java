package kr.co.company.pocketmoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // 데이터베이스 명
    public static final String DATABASE_NAME = "POCKETMONEY.db";

    // 테이블 명
    public static final String TABLE_MONEY = "money_table";
    public static final String TABLE_REWARD = "reward_table";

    // TABLE_MONEY 항목
    public static final String M_1 = "id";
    public static final String M_2 = "io";
    public static final String M_3 = "date";
    public static final String M_4 = "money";
    public static final String M_5 = "content";

    // TABLE_REWARD 항목
    public static final String R_1 = "reward";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_MONEY + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, IO TEXT, DATE TEXT, MONEY TEXT, CONTENT TEXT)");
        db.execSQL("create table " + TABLE_REWARD + "(REWARD TEXT PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MONEY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_REWARD);
        onCreate(db);

    }

    // 용돈기입장 항목 추가
    public boolean insertM (String io, String date, String money, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(M_2,io);
        contentValues.put(M_3,date);
        contentValues.put(M_4,money);
        contentValues.put(M_5,content);
        long result = db.insert(TABLE_MONEY, null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    // 리워드 항목 추가
    public boolean insertR (String reward) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(R_1,reward);
        long result = db.insert(TABLE_REWARD, null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // 용돈기입장 읽어오기
    public Cursor getM() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_MONEY,null);
        return res;
    }

    // 리워드 읽어오기
    public Cursor getR() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_REWARD,null);
        return res;
    }

    // 리워드 삭제하기
    public Integer deleteR (String reward){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_REWARD, "REWARD = ? ",new String[]{reward});
    }

    /*
    //데이터베이스 수정하기
    public boolean updateData(String id, String name, String phone, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,phone);
        contentValues.put(COL_4,address);
        db.update(TABLE_NAME,contentValues,"ID = ?", new String[] { id });
        return true;
    }
    */

}