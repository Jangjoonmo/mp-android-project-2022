package com.example.parentsletterproject.server;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    // 조회
    @GET("class/all")
    Call<List<ClassroomList>> getClassroomList();

    @PUT("class/{className}")
    Call<Classroom> putClassroom(@Path("className") String className, @Query("tName") String tName, @Query("tId") String tId);



}
