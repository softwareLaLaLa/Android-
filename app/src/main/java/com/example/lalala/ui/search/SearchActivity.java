package com.example.lalala.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalala.R;
import com.example.lalala.UserActivity;
import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.http.MessageResponse;
import com.example.lalala.http.RePaperTask;
import com.example.lalala.http.SearchTask;
import com.example.lalala.shared_info.SaveUser;
import com.example.lalala.ui.browse_fragment.PaperAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity implements MessageResponse {

    private EditText et_search;
    private RecyclerView resultList;
    private SearchAdapter searchAdapter;
    private List<PaperSimpleData> searchPapers = new ArrayList<>();
    private SearchTask searchTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);

        et_search = findViewById(R.id.et_search);
        et_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String text = et_search.getText().toString();
                    if(text==""){
                        Toast.makeText(SearchActivity.this, "请输入要搜索的内容！", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SearchActivity.this, "需要搜索的是" + text, Toast.LENGTH_SHORT).show();
                        if(SaveUser.Debug){
                            if(text=="text"){
                                for (int i = 0; i < 10; ++i) {
                                    List<String> tags = new ArrayList<>();
                                    tags.add("tag1");
                                    tags.add("tag2");
                                    tags.add("tag3");
                                    searchPapers.add(new PaperSimpleData(111, "title"+i, i, tags));
                                }
                                searchAdapter = new SearchAdapter(searchPapers,SearchActivity.this);
                                resultList.setAdapter(searchAdapter);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
                                resultList.setLayoutManager(linearLayoutManager);
                            }
                            else{
                                Toast.makeText(SearchActivity.this, "搜索结果为空", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            searchTask.execute(text);
                        }
                    }

                }
                return false;
            }
        });
        resultList = findViewById(R.id.searchList);
    }

    public void backToHome(View view) {
        Intent intent = new Intent(SearchActivity.this, UserActivity.class);
        startActivity(intent);
    }


    @Override
    public void onReceived(String resJson) {
        if (resJson == "") {
            Toast.makeText(SearchActivity.this, "无搜索结果" , Toast.LENGTH_SHORT).show();
        }
        Gson gson = new Gson();
        List<PaperSimpleData> resPapers = gson.fromJson(resJson, new TypeToken<ArrayList<PaperSimpleData>>() {
        }.getType());

        searchPapers.clear();
        searchPapers.addAll(resPapers);

        searchAdapter = new SearchAdapter(searchPapers,SearchActivity.this);
        resultList.setAdapter(searchAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
        resultList.setLayoutManager(linearLayoutManager);

    }
}
