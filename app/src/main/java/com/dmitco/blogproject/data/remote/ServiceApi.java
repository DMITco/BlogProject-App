package com.dmitco.blogproject.data.remote;


import com.dmitco.blogproject.model.Login;
import com.dmitco.blogproject.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceApi {

    @FormUrlEncoded
    @POST("Login")
    Call<Login> login(@Field("userName") String user,
                      @Field("password") String pass);

    @FormUrlEncoded
    @GET("post/{PageNumber}/{PageSize}")
    Call<List<Post>> getPosts(@Path("PageNumber") int pageNumber,
                              @Path("PageSize") int pageSize);


}
