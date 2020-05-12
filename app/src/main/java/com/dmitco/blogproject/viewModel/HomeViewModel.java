package com.dmitco.blogproject.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dmitco.blogproject.R;
import com.dmitco.blogproject.application.Constants;
import com.dmitco.blogproject.application.G;
import com.dmitco.blogproject.data.Repository;
import com.dmitco.blogproject.data.remote.CallBack;
import com.dmitco.blogproject.data.remote.ResponseException;
import com.dmitco.blogproject.model.Post;
import com.dmitco.blogproject.utility.Event;
import com.dmitco.blogproject.utility.Utils;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel {

    private final int PAGE_SIZE = 10;
    private MutableLiveData<List<Post>> observablePosts = new MutableLiveData<>();
    private List<Post> posts = new ArrayList<>();

    public HomeViewModel(Application application, Repository repository) {
        super(application, repository);
    }

    public LiveData<List<Post>> getPostsData(){
        return observablePosts;
    }

    public void getPosts(int currentPage) {
        setShowProgressValue(true);
        CallBack<List<Post>> callBack = new CallBack<List<Post>>() {
            @Override
            public void onSuccess(List<Post> result) {

                setShowProgressValue(false);
                if (result==null){
                    setShowMessageValue(Utils.getString(R.string.defaultErrorMessage));
                    return;
                }
//                G.log(Constants.TAG, "onSuccess() called with: resultSize = [" + result.size() + "]");
                if (currentPage == 1){
                    posts.clear();
                }
                posts.addAll(result);
                observablePosts.setValue(posts);
                if (posts.isEmpty()){
                    setShowMessageValue(Utils.getString(R.string.noData));
                }

            }

            @Override
            public void onFail(ResponseException e) {
                setShowProgressValue(false);
                String message = e.getMessage() != null ? e.getMessage() : Utils.getString(R.string.defaultErrorMessage);
                setShowMessageValue(message);
            }
        };
        repository.getPosts(currentPage,PAGE_SIZE,callBack);
    }
}
