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

public class ViewHolderLecture extends  RecyclerView.ViewHolder {

    TextView tv_lecturename,tv_lecturetime;
    LinearLayout linearlayout;
    CheckBox ch_lecture;
    Button bt_lecture;

    OnViewHolderItemClickListener onViewHolderItemClickListener;


    public ViewHolderLecture(@NonNull View itemView) {
        super(itemView);

        tv_lecturename = itemView.findViewById(R.id.lectureName);
        tv_lecturetime = itemView.findViewById(R.id.lectureTime);
        ch_lecture = itemView.findViewById(R.id.ch_lecture);
        bt_lecture = itemView.findViewById(R.id.bt_lecture);

        linearlayout = itemView.findViewById(R.id.lectureLinearLayout);

        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });
    }

    public void onBind(LectureItem data,int position, SparseBooleanArray selectedItems){
        tv_lecturename.setText(data.getlectureName());
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