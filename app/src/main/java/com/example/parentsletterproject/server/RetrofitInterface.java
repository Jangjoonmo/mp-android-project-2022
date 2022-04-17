package com.example.parentsletterproject.server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetrofitInterface {

    // 조회
    @GET("class/all")
    Call<List<ClassroomList>> getClassroomList();

}
