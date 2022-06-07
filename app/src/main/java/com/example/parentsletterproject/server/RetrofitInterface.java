package com.example.parentsletterproject.server;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    // API for class

    @GET("class/all")
    Call<List<ClassroomList>> getClassroomList();

    @GET("class/className")
    Call<List<ClassName>> getClassName();

    @PUT("class/{className}")
    Call<Classroom> putClassroom(@Path("className") String className, @Query("tName") String tName, @Query("tId") String tId);

    @DELETE("class/{className}")
    Call<ResponseBody> deleteClassroom(@Path("className") String className);

    // API for post

    @PUT("post/create")
    Call<Board> putBoard(@Query("postName") String postName, @Query("postBody") String postBody);

    @GET("post/all")
    Call<List<Board>> getBoardList();

    @GET("post/{postNum}")
    Call<Board> getBoard();

}
