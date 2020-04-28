package com.dmitco.blogproject.data.remote;


import com.dmitco.blogproject.model.Login;
import com.dmitco.blogproject.model.User;
import com.dmitco.blogproject.model.Version;

import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockServiceData {

    public static final class MockService implements ServiceApi  {

        private final BehaviorDelegate<ServiceApi> delegate;

        public MockService(BehaviorDelegate<ServiceApi> delegate) {
            this.delegate = delegate;

        }

        @Override
        public Call<Login> login(String userName, String pass) {
            Version version = new Version();
            version.setActive(true);
            version.setImportant(false);
            version.setUrlVersion("http:test.com");
            version.setVersionId(1);
            version.setVersionCode(1);
            version.setVersionName("1.0");

            String token = "jsdsdjsjfjjkjsdkjksdksjdkskdjskd";

            User user = new User();
            user.setUserId(1);
            user.setActiveCode("aaaa");
            user.setUserName("davidBU.");
            user.setName("داود");
            user.setFamily("یونسی");
            user.setEmail("david@gmail.com");
            user.setRegisterDate("1398/10/14 20:13:08");

            Login login = new Login();
            login.setToken(token);
            login.setUser(user);
            login.setVersion(version);
            return delegate.returningResponse(login).login(userName, pass);
        }
    }


}