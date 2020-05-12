package com.dmitco.blogproject.data;


import com.dmitco.blogproject.data.remote.CallBack;
import com.dmitco.blogproject.data.remote.RemoteRepository;
import com.dmitco.blogproject.model.Login;
import com.dmitco.blogproject.model.Post;

import java.util.List;

public class Repository {
    private static Repository INSTANCE ;
    private  RemoteRepository remoteRepository;


    public static Repository getInstance(RemoteRepository remoteRepository) {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository(remoteRepository);
                }
            }
        }
        return INSTANCE;
    }

    private Repository(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public void login(String userName, String password, CallBack<Login> callBack){
        remoteRepository.login(userName, password, callBack);
    }

    public void getPosts(int page,int pageSize, CallBack<List<Post>> callBack){
        remoteRepository.getPosts(page,pageSize,callBack);
    }
}
