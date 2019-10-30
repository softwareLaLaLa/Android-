package com.example.lalala.ui.follow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lalala.R;

public class FollowFragment extends Fragment {

    private FollowViewModel followViewModel;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        followViewModel =
                ViewModelProviders.of(this).get(FollowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_follow, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        listView=root.findViewById(R.id.list_follow);
        listView.setAdapter(new FollowListAdapter(getActivity()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent=new Intent(getActivity(),);
                //startActivity(intent);
            }
        });
        followViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}