package com.example.thecheck;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PopupActivity_addLecture extends Activity {

    EditText tv_newlecture, tv_category, tv_deadline, tv_newtime,tv_newnumclass, tv_url;
    Button bt_addlecture;

    DBHelper dbHelper;

    final static String dbName = "class.db";
    final static int dbVersion = 1;

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_addlecture);

        //UI 객체생성
        tv_newlecture = (EditText)findViewById(R.id.newLecture);
        tv_category = (EditText)findViewById(R.id.newCategory);
        tv_newtime = (EditText)findViewById(R.id.newTime);
        tv_newnumclass = (EditText)findViewById(R.id.newNumcalss);
        tv_url = (EditText)findViewById(R.id.newUrl);
        bt_addlecture = (Button)findViewById(R.id.bt_addlecture);

        //DB 객체 생성 (taeyang2.lee)
        dbHelper = new DBHelper(this, dbName, null, dbVersion);

        bt_addlecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db;
                String sql;
                long now = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(new Date(now));

                switch (view.getId()) {
                    case R.id.bt_addlecture:
                        String newlecture = tv_newlecture.getText().toString();
                        String newcategory = tv_category.getText().toString();
                        String newtime = tv_newtime.getText().toString();
                        String numclass = tv_newnumclass.getText().toString();
                        String url = tv_url.getText().toString();

                        // 데이터 삽입 (taeyang2.lee)
                        db = dbHelper.getWritableDatabase();
                        sql = String.format("INSERT INTO class VALUES" +
                                        " (NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%d');",
                                newlecture, newcategory, newtime, numclass, url, date, 0);
                        db.execSQL(sql);


                        Intent intent = new Intent();
                        intent.putExtra("lecture", newlecture);
                        intent.putExtra("category", newcategory);
                        intent.putExtra("time", newtime);
                        intent.putExtra("numclass", numclass);
                        intent.putExtra("url", url);
                        setResult(RESULT_OK, intent);
                        finish();

                }

            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    // DBHelper 클래스 (taeyang2.lee)
    public static class DBHelper extends SQLiteOpenHelper {

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