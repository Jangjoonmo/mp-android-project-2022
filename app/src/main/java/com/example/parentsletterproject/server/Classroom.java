package com.example.parentsletterproject.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Classroom {

//    @SerializedName("classId")
//    @Expose
//    private String classId;
//    @SerializedName("className")
//    @Expose
//    private String className;
    @SerializedName("tName")
    @Expose
    private String tName;
    @SerializedName("tId")
    @Expose
    private String tId;

    public Classroom(String tName, String tId) {
        this.tName = tName;
        this.tId = tId;
    }

//    public String getClassId() {
//        return classId;
//    }
//
//    public void setClassId(String classId) {
//        this.classId = classId;
//    }
//
//    public String getClassName() {
//        return className;
//    }
//
//    public void setClassName(String className) {
//        this.className = className;
//    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "tName='" + tName + '\'' +
                ", tId='" + tId + '\'' +
                '}';
    }

}
