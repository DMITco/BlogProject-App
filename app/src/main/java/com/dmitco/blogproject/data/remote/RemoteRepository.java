package com.dmitco.blogproject.data.remote;

import com.dmitco.blogproject.R;
import com.dmitco.blogproject.application.Constants;

import com.dmitco.blogproject.utility.Utils;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {


    private static RemoteRepository INSTANCE;
    private ServiceApi api;

    public static RemoteRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteRepository();
                }
            }
        }
        return INSTANCE;
    }

    private RemoteRepository() {

        api = new ServiceApiProvider().getService();

    }

    private <T> Callback<T> makeCallBack(CallBack<T> callBack){
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {

                if (response.isSuccessful()) {// HTTP status codes:200,201,204 ...
                    if ((response.code() == 200 || response.code() == 201) && response.body() != null) {
                        callBack.onSuccess(response.body());
                    } else if (response.code() == 204) {// status code 204 (No Content), has no Body
                        callBack.onSuccess(response.body());
                    } else {//For non-agreed-upon codes.
                        callBack.onFail(new ResponseException(Utils.getString(R.string.defaultErrorMessage), Constants.NON_AGREED_UPON_CODE));
                    }

                } else {
                    try {
                        if (response.errorBody() != null) {
                            JSONObject errorObject = new JSONObject(response.errorBody().string());
                            String errorMessage = errorObject.getString("message");
                            // Code 500 not displayed for security.
                            String message = response.code() == 500 ? Utils.getString(R.string.defaultErrorMessage) : errorMessage;
                            callBack.onFail(new ResponseException(message, response.code()));
                        } else {
                            callBack.onFail(new ResponseException(Utils.getString(R.string.defaultErrorMessage), response.code()));
                        }

                    } catch (Exception e) {
                        callBack.onFail(new ResponseException(Utils.getString(R.string.defaultErrorMessage), response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callBack.onFail(new ResponseException(Utils.getString(R.string.defaultErrorMessage),0));
            }
        };
    }


}
