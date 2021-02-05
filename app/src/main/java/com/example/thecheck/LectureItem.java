package com.example.thecheck;

import java.util.ArrayList;

public class LectureItem {
    private int lectureId;
    private int numClass;
    private String lectureName;
    private String category;
    private String deadline;
    private String url;
    private String lectureTime;

    public LectureItem() {
    }
    public LectureItem(String lectureName, String category, String deadline, String lectureTime, int numClass, String url){
        this.lectureName = lectureName;
        this.category = category;
        this.deadline = deadline;
        this.lectureTime = lectureTime;
        this.numClass = numClass;
        this.url = url;
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
}
