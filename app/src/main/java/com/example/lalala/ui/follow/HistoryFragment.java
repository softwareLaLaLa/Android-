package com.example.lalala.ui.follow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lalala.R;
import com.example.lalala.shared_info.SaveUser;

public class HistoryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_follow, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        ListView listView = root.findViewById(R.id.list_follow);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent=new Intent(getActivity(),);
                //startActivity(intent);
                Toast toast=Toast.makeText(getActivity(),"点击了"+position,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return root;
    }
}