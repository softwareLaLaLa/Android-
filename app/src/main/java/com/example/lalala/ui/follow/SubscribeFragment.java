package com.example.lalala.ui.follow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalala.R;
import com.example.lalala.entity.UserSubscribeData;
import com.example.lalala.http.GetSubscribeUserTask;
import com.example.lalala.http.MessageResponse;
import com.example.lalala.shared_info.SaveUser;
import com.example.lalala.ui.browse_fragment.PageViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SubscribeFragment extends Fragment implements MessageResponse {
    RecyclerView userSubscribeRecyclerView;
    PageViewModel pageViewModel;

    public static SubscribeFragment newInstance(int index) {
        SubscribeFragment fragment = new SubscribeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Log.d("BrowseFragment","createfragment1");
        super.onCreate(savedInstanceState);
        if (SaveUser.userInfoEntity != null) {
            Log.d("Browse", "user is not null");
        } else {
            Log.d("Browse", "user is null");
        }
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        pageViewModel.setIndex(0);
    }

    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_browse, container, false);
        pageViewModel.getmIndex().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                getSubscribeUser();
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userSubscribeRecyclerView = view.findViewById(R.id.subscribeList);
    }

    //获取订阅用户
    private void getSubscribeUser(){
        GetSubscribeUserTask getSubscribeUserTask = new GetSubscribeUserTask();
        getSubscribeUserTask.setMessageResponse(this);
        getSubscribeUserTask.execute();
        try {
            getSubscribeUserTask.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //获取推荐论文之后的回调函数
    @Override
    public void onReceived(String res) {
        Gson gson = new Gson();
        List<UserSubscribeData> userSubscribeDataList = new ArrayList<>();
        userSubscribeDataList = gson.fromJson(res, userSubscribeDataList.getClass());
        SubscribeListAdapter subscribeListAdapter = new SubscribeListAdapter(userSubscribeDataList, getActivity());
        userSubscribeRecyclerView.setAdapter(subscribeListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        userSubscribeRecyclerView.setLayoutManager(layoutManager);
    }
}
