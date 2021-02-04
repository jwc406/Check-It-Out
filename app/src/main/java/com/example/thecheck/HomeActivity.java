package com.example.thecheck;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;

public class HomeActivity extends AppCompatActivity {

    public static ArrayList<LectureViewAdapter> mArrayList;
    public static LectureViewAdapter adapter;
    public static RecyclerView mRecyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home);
        init();
        getData();
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