package com.example.parentsletterproject.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassName {
    @SerializedName("className")
    @Expose
    private String className;

    public ClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ClassName{" +
                "className='" + className + '\'' +
                '}';
    }
}
