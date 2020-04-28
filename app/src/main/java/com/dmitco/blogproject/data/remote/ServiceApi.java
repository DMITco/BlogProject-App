package com.dmitco.blogproject.data.remote;


import com.dmitco.blogproject.model.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceApi {

    @FormUrlEncoded
    @POST("Login")
    Call<Login> login(@Field("userName") String user,
                      @Field("password") String pass);


}
