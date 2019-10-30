package com.example.lalala.Sort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.lalala.R;

public class SortActivity extends AppCompatActivity {

    private GridView gridSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        gridSort=findViewById(R.id.sort_grid);
        gridSort.setAdapter(new SortGridAdapter(SortActivity.this));

        gridSort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent=new Intent(SortActivity.this,);
                //startActivity(intent);
            }
        });

    }
}
