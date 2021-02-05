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
    TextView lectName1, lectTerm1, lectType1, lectUrl1, lectName2, lectTerm2, lectType2, lectUrl2;

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

        dbHelper = new DBHelper(this, dbName, null, dbVersion);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SQLiteDatabase db;
        String sql;

        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                db = dbHelper.getReadableDatabase();
                sql = "SELECT * FROM class;";
                Cursor cursor = db.rawQuery(sql, null);
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()){
                        lectName2.setText(cursor.getString(1));
                        lectTerm2.setText(cursor.getString(6));
                        lectType2.setText(cursor.getString(2));
                        lectUrl2.setText(cursor.getString(5));

                    }

                } else {
                    return;
                }
                cursor.close();

            }

            dbHelper.close();
        }
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
                    "dueDate INTEGER, lecStartDate TEXT, lessons INTEGER, url VARCHAR(20));");
        }

        // DB 업데이트 시 호출 (taeyang2.lee)
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS class");
            onCreate(db);
        }
    }

}