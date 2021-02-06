package com.example.thecheck;

import java.util.ArrayList;

public class LectureItem {
    private int lectureId;
    private int numClass;
    private String lectureName;
    private String category;
    private String url;
    private String lectureTime;
    private int curNum;
    private String lastDate;

    long now = System.currentTimeMillis();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String date = sdf.format(new java.util.Date(now));

    public LectureItem() {
    }
    public LectureItem(String lectureName, String category, String lectureTime, int numClass, String url){
        this.lectureName = lectureName;
        this.category = category;
        this.lectureTime = lectureTime;
        this.numClass = numClass;
        this.url = url;
        this.curNum = 0;
        this.lastDate = date;
    }

    public int getlectureId() { return lectureId;}
    public void setlectureId(int lectureId){
        this.lectureId = lectureId;
    }

    public String getlectureName() { return lectureName;}
    public void setlectureName(String lectureName){
        this.lectureName = lectureName;
    }

    public String getlectureTime() { return lectureTime;}
    public void setlectureTime(String lectureTime){
        this.lectureTime = lectureTime;
    }

    public int getnumClass() { return numClass;}
    public void setnumClass(int lectureId){
        this.numClass = numClass;
    }

    private int getcurNum() {return curNum;}
    public void pluscurNum(){
        this.numClass += 1;
    }

    public String getLastDate() { return lastDate;}
    public void setLastDate(String date){
        this.lectureTime = date;
    }

    public String getCategory() { return category;}
    public void setCategory(String category){
        this.category = category;
    }

    public String getUrl() { return url;}
    public void setUrl(String url){
        this.url = url;
    }
}
