package com.example.thecheck;

import android.animation.ValueAnimator;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;


public class ViewHolderLecture extends  RecyclerView.ViewHolder {

    TextView tv_lecturename,tv_lecturetime, tv_lastDate;
    LinearLayout linearlayout;
    CheckBox ch_lecture;
    Button bt_lecture;

    PopupActivity_addLecture.DBHelper dbHelper;
    SQLiteDatabase db;
    String sql;

    final static String dbName = "class.db";
    final static int dbVersion = 1;

    long now = System.currentTimeMillis();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String date = sdf.format(new java.util.Date(now));

    OnViewHolderItemClickListener onViewHolderItemClickListener;


    public ViewHolderLecture(@NonNull View itemView) {
        super(itemView);

        dbHelper = new PopupActivity_addLecture.DBHelper(LectureViewAdapter.context, dbName, null, dbVersion);
        db = dbHelper.getWritableDatabase();

        tv_lecturename = itemView.findViewById(R.id.lectureName);
        tv_lecturetime = itemView.findViewById(R.id.lectureTime);
        tv_lastDate = itemView.findViewById(R.id.lastDate);
        ch_lecture = itemView.findViewById(R.id.ch_lecture);
        bt_lecture = itemView.findViewById(R.id.bt_lecture);

        linearlayout = itemView.findViewById(R.id.lectureLinearLayout);

        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });

        bt_lecture.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(LectureViewAdapter.context, DetailActivity.class);
                intent.putExtra("이름",tv_lecturename.getText());
                LectureViewAdapter.context.startActivity(intent);
            }
        });

        if(ch_lecture.isChecked()==true) {
            if (tv_lastDate.getText().toString() != date) {
                tv_lastDate.setText(date);

                //update class set numclass = numclass + 1 where name = tv_lecturename.getText();
                sql = String.format("UPDATE LIST SET numclass = numclass + 1 where lectName='" + tv_lecturename.getText() + "' ");
                db.execSQL(sql);

                //update class set lastDate = date where name = tv_lecturename.getText();
                sql = String.format("UPDATE LIST SET lastDate = '" + date + "' where lectName='" + tv_lecturename.getText() + "' ");
                db.execSQL(sql);
                //변수 date엔 오늘의 날짜가 'yyyy-mm-dd'의 string으로 저장되어 있음
            }
        }
    }

    public void onBind(LectureItem data,int position, SparseBooleanArray selectedItems){
        tv_lecturename.setText(data.getlectureName());
        tv_lecturetime.setText(data.getlectureTime());
        tv_lastDate.setText(data.getLastDate());
        changeVisibility(selectedItems.get(position));
    }

    private void changeVisibility(final boolean isExpanded) {
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 600) : ValueAnimator.ofInt(600, 0);
        // Animation이 실행되는 시간, n/1000초
        va.setDuration(500);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // classView가 실제로 사라지게하는 부분
                ch_lecture.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                bt_lecture.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            }
        });
        // Animation start
        va.start();
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }
}