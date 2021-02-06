package com.example.thecheck;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity{

    ImageView lectImg;
    TextView lectName1, lectTerm1, lectType1, lectUrl1, lectName2, lectTerm2, lectType2, lectUrl2, lectN, lectM;

    DBHelper dbHelper;

    List tempList = new ArrayList();

    final static String dbName = "class.db";
    final static  int dbVersion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_detail);

        lectImg = (ImageView)findViewById(R.id.imageView);
        lectName1 = (TextView)findViewById(R.id.d_lectureName);
        lectTerm1 = (TextView)findViewById(R.id.textView5);
        lectType1 = (TextView)findViewById(R.id.textView6);
        lectUrl1 = (TextView)findViewById(R.id.textView7);
        lectName2 = (TextView)findViewById(R.id.textView);
        lectTerm2 = (TextView)findViewById(R.id.textView8);
        lectType2 = (TextView)findViewById(R.id.textView9);
        lectUrl2 = (TextView)findViewById(R.id.textView10);

        lectN = (TextView)findViewById(com.example.thecheck.R.id.textView11);

        dbHelper = new DBHelper(this, dbName, null, dbVersion);

        SQLiteDatabase db;
        String sql;

        db = dbHelper.getReadableDatabase();
        Intent intent = getIntent();
        String message = intent.getStringExtra("이름");
        sql = "SELECT * FROM class Where lectName = '"+message+"';";

        long now = System.currentTimeMillis();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new java.util.Date(now));

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()){
                lectName2.setText(cursor.getString(1));
                lectTerm2.setText(cursor.getString(3)+"~"+date);
                lectN.setText("현재" +cursor.getString(4)+" 중 "+cursor.getString(7)+"강 완강");
                lectType2.setText(cursor.getString(2));
                lectUrl2.setText(cursor.getString(5));

            }

        } else {
            return;
        }
        cursor.close();
        dbHelper.close();

    }


    // DBHelper 클래스 (taeyang2.lee)
    static class DBHelper extends SQLiteOpenHelper {

        // DB 파일 생성성 (taeyang2.lee)
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        // DB 처음 만들 때 호출 (데이터 생성 등의 초기 처리) (taeyang2.lee)
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE IF NOT EXISTS class " +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, lectName TEXT, lectType TEXT, " +
                    "lecStartDate TEXT, lessons INTEGER, url VARCHAR(20), lastDate TEXT, curnum INTEGER);");
        }

        // DB 업데이트 시 호출 (taeyang2.lee)
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS class");
            onCreate(db);
        }
    }

}