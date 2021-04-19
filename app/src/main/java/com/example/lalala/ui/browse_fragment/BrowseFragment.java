package com.example.lalala.ui.browse_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalala.R;
import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.entity.SquarePaperData;
import com.example.lalala.entity.UserInfoEntity;
import com.example.lalala.http.GetHotPaperTask;
import com.example.lalala.http.GetRecommendPaperTask;
import com.example.lalala.http.GetSquarePaperTask;
import com.example.lalala.http.MessageResponse;
import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class BrowseFragment extends Fragment implements MessageResponse{

    private static final String BROWSETYPE = "browseType";

    private PageViewModel pageViewModel;
    private RecyclerView paperViewList;
    private List<PaperSimpleData> paperRecItems = new ArrayList<>();
    private List<SquarePaperData> squarePaperData = new ArrayList<>();
    public int fragmentType;
    SquareAdapter squareAdapter;

    public static BrowseFragment newInstance(int index) {
        BrowseFragment fragment = new BrowseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BROWSETYPE, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        if (getArguments() != null) {
            fragmentType = getArguments().getInt(BROWSETYPE);
        }
        pageViewModel.setIndex(fragmentType);
    }

    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_browse, container, false);

        pageViewModel.getmIndex().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                System.out.println("界面类型：" + fragmentType);
                System.out.println("获取论文");
                getPapers();
            }
        });

        paperViewList = root.findViewById(R.id.paperList);

        if(fragmentType == 1){
            squareAdapter = new SquareAdapter(squarePaperData, getActivity());
        }else{
            squareAdapter = new SquareAdapter(paperRecItems, getActivity(), fragmentType);
        }
        paperViewList.setAdapter(squareAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        paperViewList.setLayoutManager(layoutManager);
        paperViewList.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                getPapers();
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    //刚进入与到达最后一篇论文时会运行该函数
    private void getPapers() {
        if(fragmentType == 1){
            Log.d("论文浏览界面", "获取广场论文列表");
            GetSquarePaperTask getSquarePaperTask = new GetSquarePaperTask();
            getSquarePaperTask.setMessageResponse(this);
            getSquarePaperTask.execute();
            try {
                getSquarePaperTask.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }else if(fragmentType == 2){
            Log.d("论文浏览界面", "获取推荐论文列表");
            GetRecommendPaperTask getRecommendPaperTask = new GetRecommendPaperTask();
            getRecommendPaperTask.execute();
            getRecommendPaperTask.setMessageResponse(this);
            try {
                getRecommendPaperTask.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }else if(fragmentType == 3){
            if(paperRecItems.size() > 0){
                return;
            }
            Log.d("论文浏览界面", "获取热榜论文列表");
            GetHotPaperTask getHotPaperTask = new GetHotPaperTask();
            getHotPaperTask.setMessageResponse(this);
            getHotPaperTask.execute();
            try {
                getHotPaperTask.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//获取推荐论文之后的回调函数
    @Override
    public void onReceived(String res) {
        System.out.println("fragmentType:" + fragmentType);
        System.out.println("获取论文列表:" + res);
        Gson gson = new Gson();
        if(fragmentType == 1){
            Type type = new TypeToken<List<SquarePaperData>>(){}.getType();
            List<SquarePaperData> temp = gson.fromJson(res, type);
            squarePaperData.addAll(temp);
            System.out.println("论文列表Size:"+squarePaperData.size());
        }else{
            Type type = new TypeToken<List<PaperSimpleData>>(){}.getType();
            List<PaperSimpleData> temp = gson.fromJson(res, type);
            paperRecItems.addAll(temp);
            System.out.println("论文列表Size:"+paperRecItems.size());
        }
        squareAdapter.notifyDataSetChanged();
    }
}
