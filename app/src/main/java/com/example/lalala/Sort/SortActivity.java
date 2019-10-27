package com.example.lalala.Sort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    }
}
