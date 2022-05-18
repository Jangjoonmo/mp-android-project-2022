package com.example.parentsletterproject.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassroomList {

    // @SerializedName : Json으로 Serialize 될 때 매칭되는 이름을 명시하는 목적
    // @Expose : Object 중 해당 값이 NULL일 경우 Json으로 만들 필드를 자동 생략
    @SerializedName("classId")
    @Expose
    private String classId;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("tname")
    @Expose
    private String tName;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTName() {
        return tName;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    @Override
    public String toString() {
        return "Class Model{" +
                "id='" + classId + '\'' +
                ", name='" + className + '\'' +
                ", tName='" + tName + '\'' +
                '}';
    }

}
