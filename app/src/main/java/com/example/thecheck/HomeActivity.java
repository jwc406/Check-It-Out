package com.example.thecheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.view.Menu;
import android.content.Context;

public class HomeActivity extends AppCompatActivity {

    public static ArrayList<LectureViewAdapter> mArrayList;
    public static LectureViewAdapter adapter;
    public static RecyclerView mRecyclerView;
    Button bt_add;
    Context context = this;
    final static String dbName = "class.db";
    final static  int dbVersion = 1;

    DBHelper dbHelper = new DBHelper(this, dbName, null, dbVersion);
    SQLiteDatabase db;

    long now = System.currentTimeMillis();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String date = sdf.format(new java.util.Date(now));

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home);

        db = dbHelper.getReadableDatabase();

        //use toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //강의추가버튼
        bt_add = (Button)findViewById(R.id.bt_add);

        init();
        getData();
    }

    public void onClick(View v){
        Intent intent;
        switch(v.getId()) {
            case R.id.bt_add:
                intent = new Intent(this, PopupActivity_addLecture.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.bt_home:
                android.widget.Toast.makeText(context, "현재페이지", android.widget.Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_calender:
                intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String newlecture = data.getStringExtra("lecture");
                String newcategory = data.getStringExtra("category");
                String newtime = data.getStringExtra("time");
                String numclass = data.getStringExtra("numclass");
                String url = data.getStringExtra("url");
                LectureItem item = new LectureItem(newlecture, newcategory, newtime, Integer.parseInt(numclass), url);
                adapter.addItem(item);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recycler_lectureView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new LectureViewAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        String sql;

        LectureItem data = new LectureItem("컴퓨터학개론", "학교", "월요일 1교시~2교시",15, "http://naver.com");
        adapter.addItem(data);
        sql = String.format("INSERT INTO class VALUES" +
                        " (NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%d');",
                data.getlectureName(), data.getCategory(), data.getlectureTime(), data.getnumClass(), data.getUrl(), date, 0);
        db.execSQL(sql);

        data = new LectureItem("피트니스", "취미", "월수금 20:00 ~ 21:00",30, "https://swhackathon.com/");
        adapter.addItem(data);
        sql = String.format("INSERT INTO class VALUES" +
                        " (NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%d');",
                data.getlectureName(), data.getCategory(), data.getlectureTime(), data.getnumClass(), data.getUrl(), date, 0);
        db.execSQL(sql);


        data = new LectureItem("토익인강", "공부", "월화수목금 15:00 ~ 17:00",35, "https://google.co.kr");
        adapter.addItem(data);
        sql = String.format("INSERT INTO class VALUES" +
                        " (NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%d');",
                data.getlectureName(), data.getCategory(), data.getlectureTime(), data.getnumClass(), data.getUrl(), date, 0);
        db.execSQL(sql);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // DBHelper 클래스 (taeyang2.lee)
    static class DBHelper extends android.database.sqlite.SQLiteOpenHelper {

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