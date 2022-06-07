package com.example.parentsletterproject.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Board {
    @SerializedName("postName")
    @Expose
    private String postName;
    @SerializedName("postBody")
    @Expose
    private String postBody;

    public Board(String postName, String postBody) {
        this.postName = postName;
        this.postBody = postBody;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    @Override
    public String toString() {
        return "Board{" +
                "postName='" + postName + '\'' +
                ", postBody='" + postBody + '\'' +
                '}';
    }
}
