package com.example.parentsletterproject.server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    // 조회
    @GET("class/all")
    Call<List<ClassroomList>> getClassroomList();

    @GET("class/teacherincharge")
    Call<List<String>> getTeacherInCharge();

}
