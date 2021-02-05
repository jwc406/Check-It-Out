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

public class HomeActivity extends AppCompatActivity {

    public static ArrayList<LectureViewAdapter> mArrayList;
    public static LectureViewAdapter adapter;
    public static RecyclerView mRecyclerView;
    Button bt_add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home);

        //use toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //강의추가버튼
        bt_add = (Button)findViewById(R.id.bt_add);

        init();
        getData();
    }

    public void onClick(View v){
        Intent intent;
        intent = new Intent(this, PopupActivity_addLecture.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String newlecture = data.getStringExtra("lecture");
                String newtime = data.getStringExtra("time");
                String numclass = data.getStringExtra("numclass");
                LectureItem item = new LectureItem(1, newlecture, newtime, Integer.parseInt(numclass));
                adapter.addItem(item);
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
        LectureItem data = new LectureItem(1, "컴퓨터학개론", "9:00-10:15", 2);
        adapter.addItem(data);
        data = new LectureItem(1, "컴퓨터구조", "13:00-14:00", 2);
        adapter.addItem(data);
        data = new LectureItem(1, "알고리즘", "10:30-11:45", 2);
        adapter.addItem(data);
        data = new LectureItem(1, "운영체제", "15:00-17:00", 2);
        adapter.addItem(data);
    }
}
