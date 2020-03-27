package com.dmitco.blogproject.data;

public class Repository {
    private static Repository INSTANCE = new Repository();

    public static Repository getInstance() {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository();
                }
            }
        }
        return INSTANCE;
    }

    private Repository() {

    }
}
