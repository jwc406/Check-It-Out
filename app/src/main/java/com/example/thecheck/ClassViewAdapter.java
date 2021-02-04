package com.example.thecheck;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;

public class ClassViewAdapter extends RecyclerView.Adapter<ClassViewAdapter.ClassViewHolder> {

    private ArrayList<ClassItem> dataList;

    public ClassViewAdapter(ArrayList<ClassItem> dataList) {
        this.dataList = dataList;
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        protected CheckBox ch;
        protected TextView n;

        public ClassViewHolder(View view){
            super(view);
            ch = view.findViewById(R.id.hadclass);
        }
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.itemclass, null);

        return new ClassViewAdapter.ClassViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClassViewHolder horizontalViewHolder, int position)
    {
        horizontalViewHolder
                .ch
                .setChecked(true);
        horizontalViewHolder
                .ch
                .setText("2강");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
