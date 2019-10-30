package com.example.lalala.sort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

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
                Toast toast=Toast.makeText(SortActivity.this,"点击了"+position,Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
