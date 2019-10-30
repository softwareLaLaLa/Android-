package com.example.lalala.ui.browse_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalala.R;
import com.example.lalala.model.PaperBrowseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class BrowseFragment extends Fragment {

    private static final String RECOMMEND = "recommend";
    private static final String SUBSCRIPTION = "subscription";
    private static final String HOT = "hot";
    private static final String BROWSETYPE = "browseType";

    private RecyclerView paperViewList;
    private PaperAdapter paperAdapter;
    private List<PaperBrowseItem> paperBrowseItems = new ArrayList<>();

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
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_browse, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initData();
        paperViewList = view.findViewById(R.id.paperList);
        paperAdapter = new PaperAdapter(paperBrowseItems, getActivity());
        paperViewList.setAdapter(paperAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        paperViewList.setLayoutManager(layoutManager);
    }

    private void initData(){
        for(int i=0; i<10; ++i){
            List<String> subjects = new ArrayList<>();
            subjects.add("subject1"+i);
            subjects.add("subject2"+i);
            subjects.add("subject3"+i);
            paperBrowseItems.add(new PaperBrowseItem("title"+i, "author"+i, i*3, i*i, "DOI"+i, subjects));
        }
    }
}