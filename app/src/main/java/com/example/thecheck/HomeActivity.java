package com.example.thecheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home);

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
        LectureItem data = new LectureItem("컴퓨터학개론", "학교", "월요일 1교시~2교시",1, "http://naver.com");
        adapter.addItem(data);
        data = new LectureItem("피트니스", "취미", "월수금 20:00 ~ 21:00",1, "https://swhackathon.com/");
        adapter.addItem(data);
        data = new LectureItem("토익인강", "공부", "월화수목금 15:00 ~ 17:00",1, "https://google.co.kr");
        adapter.addItem(data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
