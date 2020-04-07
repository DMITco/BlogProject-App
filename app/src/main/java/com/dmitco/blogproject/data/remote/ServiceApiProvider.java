package com.dmitco.blogproject.data.remote;

import android.os.Build;

import com.dmitco.blogproject.BuildConfig;
import com.dmitco.blogproject.application.BuildVariants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dmitco.blogproject.application.BuildVariants.BASE_URL;

public class ServiceApiProvider {

    private Retrofit retrofit;
    private ServiceApi remoteService;


    public ServiceApiProvider() {
        retrofit = createRetrofit();
    }
    public ServiceApi getService(){

        if (remoteService==null){
            remoteService = retrofit.create(ServiceApi.class);
        }
        return remoteService;
    }

    private Retrofit createRetrofit() {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        if (BuildVariants.DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(loggingInterceptor);
        }

        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {

                Request newRequest = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        //.addHeader("Token", from shared preference)
                        .addHeader("Platform", "Android")
                        .addHeader("AndroidVersion", String.valueOf(Build.VERSION.SDK_INT))
                        .addHeader("VersionCode", String.valueOf(BuildConfig.VERSION_CODE))
                        .addHeader("VersionName", BuildConfig.VERSION_NAME)
                        .build();
                return chain.proceed(newRequest);
            }
        });


        okHttpClientBuilder
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
