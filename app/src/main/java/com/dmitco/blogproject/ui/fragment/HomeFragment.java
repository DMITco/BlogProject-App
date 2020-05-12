package com.dmitco.blogproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dmitco.blogproject.R;
import com.dmitco.blogproject.adapter.PostAdapter;
import com.dmitco.blogproject.application.Constants;
import com.dmitco.blogproject.application.G;
import com.dmitco.blogproject.interfaces.ListSelectorListener;
import com.dmitco.blogproject.model.Post;
import com.dmitco.blogproject.utility.EndlessRecyclerOnScrollListener;
import com.dmitco.blogproject.utility.Event;
import com.dmitco.blogproject.utility.Utils;
import com.dmitco.blogproject.viewModel.HomeViewModel;
import com.dmitco.blogproject.viewModel.ViewModelProviderFactory;

import java.util.List;

import static com.dmitco.blogproject.application.Constants.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private HomeViewModel viewModel;
    private PostAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //UI
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        //ViewModel
        initViewModel();
        initRecyclerView();
        setListeners(view);
        viewModel.getPosts(1);

    }

    private void setListeners(View rootView) {

        Observer<Event<Boolean>> showProgress = new Observer<Event<Boolean>>() {
            @Override
            public void onChanged(Event<Boolean> event) {
                if(event.getContentIfNotHandled()!=null){
                    float alpha = event.peekContent() ? 100: 0;
                    progressBar.setAlpha(alpha);
                }
            }
        };

        Observer<Event<String>> showMessage = new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> event) {
                if (event.getContentIfNotHandled()!=null){
                    Utils.showSnackBar(event.peekContent(),rootView.getRootView(), null);
                }
            }
        };



        Observer<List<Post>> posts = new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                adapter.clearAndPut(posts);
            }
        };

        viewModel.getShowMessage().observe(this,showMessage);
        viewModel.getShowProgress().observe(this,showProgress);
        viewModel.getPostsData().observe(this,posts);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PostAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager, 4) {
            @Override
            public void onLoadMore(int currentPage) {
                G.log(TAG, "onLoadMore() called with: currentPage = [" + currentPage + "]");
                viewModel.getPosts(currentPage);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.d(TAG, "onScrolled()  dx = [" + dx + "], dy = [" + dy + "]");
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                Log.d(TAG, "onScrollStateChanged()  = [" + getScrollStateString(newState) + "]");
            }
        });
//        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
//            @Override
//            public void onLoadMore(int currentPage) {
//
//            }
//        });
        adapter.setListener(new ListSelectorListener<Post>() {
            @Override
            public void onSelected(Post item) {

            }
        });
    }

    private void initViewModel(){

        ViewModelProviderFactory factory = ViewModelProviderFactory.getInstance(G.getInstance());
        viewModel = new ViewModelProvider(this, factory).get(HomeViewModel.class);
    }

    private  String getScrollStateString(int stateCode){
        switch (stateCode){
            case 0:
                return "SCROLL_STATE_IDLE";
            case 1:
                return "SCROLL_STATE_DRAGGING";
            case 2:
                return "SCROLL_STATE_SETTLING";
        }
        return "NOtImplement";
    }




}
