package com.example.lalala.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.lalala.R;
import com.example.lalala.sort.SortActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button btnSort;
    private GridView gridView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = root.findViewById(R.id.grid_follow_field);
        gridView.setAdapter(new FollowFieldAdapter(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent =new Intent(getActivity(),);
                //startActivity(intent);
                Toast toast=Toast.makeText(getActivity(),"点击了"+position,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return root;
    }
}