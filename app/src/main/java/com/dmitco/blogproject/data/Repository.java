package com.dmitco.blogproject.data;

import com.dmitco.blogproject.data.remote.RemoteRepository;

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
}
