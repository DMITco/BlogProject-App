package com.dmitco.blogproject.data.remote;

import java.io.Serializable;


public class ResponseException implements Serializable {

    int responseCode;
    String message=null;

     public ResponseException(String message, int responseCode){
         this.message=message;
         this.responseCode=responseCode;
     }

    public String getMessage() {
        return message;
    }

    public ResponseException(){
     super();
    }


    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
