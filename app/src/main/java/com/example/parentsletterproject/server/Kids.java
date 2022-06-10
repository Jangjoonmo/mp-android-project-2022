package com.example.parentsletterproject.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kids {
    @SerializedName("kidsName")
    @Expose
    private String kidsName;
    @SerializedName("classId")
    @Expose
    private String classId;

    public Kids(String kidsName, String classId) {
        this.kidsName = kidsName;
        this.classId = classId;
    }

    public String getKidsName() {
        return kidsName;
    }

    public void setKidsName(String kidsName) {
        this.kidsName = kidsName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Kids{" +
                "kidsName='" + kidsName + '\'' +
                ", classId=" + classId +
                '}';
    }
}
