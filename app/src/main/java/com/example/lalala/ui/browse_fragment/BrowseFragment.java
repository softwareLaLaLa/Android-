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
import com.example.lalala.UserActivity;
import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.entity.UserInfor;
import com.example.lalala.http.HotPaperTask;
import com.example.lalala.http.MessageResponse;
import com.example.lalala.http.RePaperTask;
import com.example.lalala.http.RecHotPapers;
import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class BrowseFragment extends Fragment implements MessageResponse, RecHotPapers {

    private static final String BROWSETYPE = "browseType";

    private PageViewModel pageViewModel;
    private RecyclerView paperViewList;
    private PaperAdapter paperAdapter;
    private List<PaperSimpleData> paperRecItems = new ArrayList<>();
    private RePaperTask rePaperTask;
    public static UserInfor userInfor;



    public static BrowseFragment newInstance(int index) {
        BrowseFragment fragment = new BrowseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BROWSETYPE, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Log.d("BrowseFragment","createfragment1");
        super.onCreate(savedInstanceState);
        if (SaveUser.userInfor != null) {
            Log.d("Browse", "user is not null");
        } else {
            Log.d("Browse", "user is null");
        }
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(BROWSETYPE);
        }
        pageViewModel.setIndex(index);
        //Log.d("BrowseFragment","createfragment2");
    }

    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        Log.d("BrowseFragment", "onCreateView,user");
        View root = inflater.inflate(R.layout.fragment_browse, container, false);
        //Log.d("BrowseFragment","createview2");
        paperViewList = root.findViewById(R.id.paperList);

//        if(SaveUser.Debug){
//            return root;
//        }
        pageViewModel.getmIndex().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                if (integer == 1) {
                    //initData(1);
                    if(SaveUser.Debug){
                        initData(1);
                        Collections.shuffle(paperRecItems);
                        paperAdapter = new PaperAdapter(paperRecItems, getActivity());

                        paperViewList.setAdapter(paperAdapter);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        paperViewList.setLayoutManager(layoutManager);
                    }
                    else{
                        //兴趣列表可能为空
                        int reSize = SaveUser.userInfor.getGroupID().size();
                        int reSizeC = SaveUser.userInfor.getCandidateGroupID().size();
                        Log.d("groupSize", "onChanged: " + reSize + " " + reSizeC);
                        //如果论文列表不足，就发送请求
                        if ((reSize > 0 && SaveUser.rePapers.size() < 5) || (reSizeC > 0 && SaveUser.rePapersC.size() < 2)) {
                            getPapers();
                            try {
                                rePaperTask.get();
                            } catch (ExecutionException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            onReceived("");
                        }
                    }
                } else {
                    //initData(2);
                    if(SaveUser.Debug){
                        initData(2);
                        Collections.shuffle(paperRecItems);
                        paperAdapter = new PaperAdapter(paperRecItems, getActivity());
                        paperViewList.setAdapter(paperAdapter);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        paperViewList.setLayoutManager(layoutManager);
                    }
                    else{
                        if (SaveUser.hotPapers.size() == 0) {
                            //初始化热榜列表，只需要一次
                            HotPaperTask hotPaperTask = new HotPaperTask();
                            hotPaperTask.setRecHotPapers(BrowseFragment.this);
                            hotPaperTask.execute();
                        }else{
                            recHotPapers();
                        }
                    }
                }
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    private void initData(int type) {
        paperRecItems.clear();
        for (int i = 0; i < 10; ++i) {
            List<String> tags = new ArrayList<>();
            tags.add("tag1");
            tags.add("tag2");
            tags.add("tag3");
            if (type == 1) {
                SaveUser.rePapers.add(new PaperSimpleData(111, "title"+i, i, tags));
            } else {
                SaveUser.hotPapers.add(new PaperSimpleData(222, "title"+i, i+1, tags));
            }
        }
        onReceived(null);
    }

    //当论文列表不足时获取论文
    private void getPapers() {
        Log.d("Browse", "getPapers: before get papers");
        rePaperTask = new RePaperTask();
        rePaperTask.setMessageResponse(BrowseFragment.this);
        Map<Integer, Integer> groupID = new HashMap<>();

        List<Integer> group = SaveUser.userInfor.getGroupID();
        List<Integer> groupC = SaveUser.userInfor.getCandidateGroupID();

        System.out.println("感兴趣的groupID");
        for (Integer i : group) {
            System.out.println(i);
            Integer page = SaveUser.groupPage.get(i);
            groupID.put(i, page);
            SaveUser.groupPage.put(i, page + 1);                   //更新分组页数
        }
        System.out.println("可能感兴趣的groupID");
        for (Integer i : groupC) {
            System.out.println(i);
            Integer page = SaveUser.groupPage.get(i);
            groupID.put(i, page);
            SaveUser.groupPage.put(i, page + 1);                   //更新分组页数
        }

        rePaperTask.execute(groupID);
    }


    @Override
    public void onReceived(String resJson) {
        if (SaveUser.rePapers.size() >= 6) {
            for (int i = 0; i < 6; i++) {
                paperRecItems.add(SaveUser.rePapers.get(0));
            }
        } else if(SaveUser.rePapers.size()!=0){
            int size = SaveUser.rePapers.size();
            for (int i =0;i<size;i++) {
                paperRecItems.add(SaveUser.rePapers.get(0));
            }
        }else{
            getPapers();
            return;
        }
        if (SaveUser.rePapersC.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                paperRecItems.add(SaveUser.rePapersC.get(0));
            }
        }else {
            for (PaperSimpleData paper : SaveUser.rePapersC) {
                paperRecItems.add(SaveUser.rePapersC.get(0));
            }
        }
        Collections.shuffle(paperRecItems);

        paperAdapter = new PaperAdapter(paperRecItems, getActivity());

        paperViewList.setAdapter(paperAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        paperViewList.setLayoutManager(layoutManager);
    }

    @Override
    public void recHotPapers() {
        paperRecItems.addAll(SaveUser.hotPapers);

        paperAdapter = new PaperAdapter(paperRecItems, getActivity());

        paperViewList.setAdapter(paperAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        paperViewList.setLayoutManager(layoutManager);
    }

}
