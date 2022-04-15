package com.example.parentsletterproject.server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonApi {

    // 조회
    @GET("/class/all")
    Call<List<ClassModel>> getClassroomList();

    @GET("/class/{classId}")
    Call<ClassModel> getClassroom(@Path("classId") String classId);



}
