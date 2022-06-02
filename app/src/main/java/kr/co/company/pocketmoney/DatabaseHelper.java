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
    public static final String TABLE_BUDGET = "budget_table";
    public static final String TABLE_CHECKSET = "checkset_table";

    // TABLE_MONEY 항목
    public static final String M_1 = "id";
    public static final String M_2 = "io";
    public static final String M_3 = "date";
    public static final String M_4 = "money";
    public static final String M_5 = "content";

    // TABLE_REWARD 항목
    public static final String R_1 = "rid";
    public static final String R_2 = "reward";
    public static final String R_3 = "checked";

    // TABLE_BUDGET 항목
    public static final String B_1 = "id";
    public static final String B_2 = "eat";
    public static final String B_3 = "car";
    public static final String B_4 = "pre";
    public static final String B_5 = "hobby";
    public static final String B_6 = "etc";

    // TABLE_CHECKSET 항목
    public static final String S_1 = "id";
    public static final String S_2 = "date";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_MONEY + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, IO TEXT, DATE TEXT, MONEY TEXT, CONTENT TEXT)");
        db.execSQL("create table " + TABLE_REWARD + "(RID INTEGER PRIMARY KEY AUTOINCREMENT, REWARD TEXT, CHECKED TEXT)");
        db.execSQL("create table " + TABLE_BUDGET + "(ID TEXT PRIMARY KEY, EAT TEXT, CAR TEXT, PRE TEXT, HOBBY TEXT, ETC TEXT)");
        db.execSQL("create table " + TABLE_CHECKSET + "(ID TEXT PRIMARY KEY, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MONEY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_REWARD);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_BUDGET);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CHECKSET);
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
    public boolean insertR (String reward, String checked) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(R_2,reward);
        contentValues.put(R_3,checked);
        long result = db.insert(TABLE_REWARD, null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // 예산 항목 추가
    public boolean insertB (String id, String eat, String car, String pre, String hobby, String etc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(B_1,id);
        contentValues.put(B_2,eat);
        contentValues.put(B_3,car);
        contentValues.put(B_4,pre);
        contentValues.put(B_5,hobby);
        contentValues.put(B_6,etc);
        long result = db.insert(TABLE_BUDGET, null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    // 예산 초기화 시 날짜 추가
    public boolean insertS (String id, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(S_1,id);
        contentValues.put(S_2,date);
        long result = db.insert(TABLE_CHECKSET, null,contentValues);
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

    // 예산 읽어오기
    public Cursor getB() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_BUDGET,null);
        return res;
    }

    // 예산 설정 초기화 시 날짜 읽어오기
    public Cursor getS() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_CHECKSET,null);
        return res;
    }

    // 리워드 삭제하기
    public Integer deleteR (String reward){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_REWARD, "REWARD = ? ",new String[]{reward});
    }

    // 예산 삭제하기
    public Integer deleteB (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_BUDGET, "ID = ? ",new String[]{id});
    }

    // 리워드 수정하기
    public boolean updateR(int rid, String checked) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(R_1, rid);
        contentValues.put(R_3, checked);
        db.update(TABLE_REWARD, contentValues, R_1+ "=" + rid, null);
        return true;
    }

    // 예산 수정하기
    public boolean updateB (String id, String eat, String car, String pre, String hobby, String etc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(B_1,id);
        contentValues.put(B_2,eat);
        contentValues.put(B_3,car);
        contentValues.put(B_4,pre);
        contentValues.put(B_5,hobby);
        contentValues.put(B_6,etc);
        db.update(TABLE_BUDGET,contentValues,"ID = ?", new String[] { id });
        return true;
    }

    // 예산 설정 초기화 시 날짜 수정하기
    public boolean updateS (String id, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(S_1,id);
        contentValues.put(S_2,date);
        db.update(TABLE_CHECKSET,contentValues,"ID = ?", new String[] { id });
        return true;
    }

}