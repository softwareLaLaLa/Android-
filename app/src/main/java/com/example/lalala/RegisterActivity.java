package com.example.lalala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lalala.entity.UserInfoEntity;
import com.example.lalala.http.GenerateUserSimilarityDataTask;
import com.example.lalala.http.InitTask;
import com.example.lalala.http.MessageResponse;
import com.example.lalala.http.ReTopicTask;
import com.example.lalala.http.RecInit;
import com.example.lalala.http.RecUserInfo;
import com.example.lalala.http.RegisterTask;
import com.example.lalala.http.ResponseRecTags;
import com.example.lalala.http.UserInfoTask;
import com.example.lalala.shared_info.SaveUser;
import com.example.lalala.tool.PixelTool;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity implements MessageResponse, ResponseRecTags, RecUserInfo, RecInit {
    Button btn_register;
    Button btn_back;
    EditText etUsername;
    EditText etPassword;
    EditText etPasswordC;
    FlexboxLayout flexboxLayout;

    String username;

    private Set<String> selectedTag = new HashSet<>();              //选中的标签
    private Map<String, Boolean> tagSelectData = new HashMap<>();              //标签是否选中
    //private Set<String> tags = new HashSet<>();
    private List<String> tags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etPasswordC = findViewById(R.id.et_password_1);

        btn_register = findViewById(R.id.btn_register);
        btn_back = findViewById(R.id.btn_back);
        flexboxLayout = findViewById(R.id.fl_tags);

        //初始化tagMap和tagSelect，并创建标签
        ////////////////////////////
        if (tags.size() == 0) {
            ReTopicTask reTopicTask = new ReTopicTask();
            reTopicTask.setResponseRecTags(this);
            reTopicTask.execute();
            try {
                reTopicTask.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            receiveRecTags(null);
        }

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String passwordC = etPasswordC.getText().toString();

                if (username.equals("") || password.equals(("")) || passwordC.equals("")) {
                    Toast.makeText(RegisterActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();
                } else if (selectedTag.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "请至少选择一个标签！", Toast.LENGTH_SHORT).show();
                } else if (password.equals(passwordC)) {
                    Log.d("registerActivity", "开始注册");
                    RegisterTask registerTask = new RegisterTask();
                    registerTask.setMessageResponse(RegisterActivity.this);
                    registerTask.execute(username, password);
                    try {
                        registerTask.get();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onReceived(String resJson) {
        Gson gson = new Gson();
        boolean res = gson.fromJson(resJson, Boolean.class);

        if (res) {
            //获取用户信息
            Log.d("registerActivity", "注册成功，获取用户信息");
            UserInfoTask userInfoTask = new UserInfoTask();
            userInfoTask.setRecUserInfo(this);
            userInfoTask.execute(username);

        } else {
            Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
        }
    }


    private void newButton(String tagName) {
        Button btn_tag = new Button(this);
        btn_tag.setText(tagName);
        btn_tag.setBackgroundResource(R.drawable.onclick_btn);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, PixelTool.dpToPx(this, 30));
        lp.setMargins(PixelTool.dpToPx(this, 5),
                PixelTool.dpToPx(this, 8),
                PixelTool.dpToPx(this, 5),
                PixelTool.dpToPx(this, 8));

        btn_tag.setPadding(PixelTool.dpToPx(this, 10),
                PixelTool.dpToPx(this, 0),
                PixelTool.dpToPx(this, 10),
                PixelTool.dpToPx(this, 0));

        btn_tag.setTextSize(18);
        btn_tag.setLayoutParams(lp);

        btn_tag.setOnClickListener(new onClickTag());
        btn_tag.setActivated(false);

        flexboxLayout.addView(btn_tag);
    }

    @Override
    public void receiveRecTags(String res) {
        System.out.println("allTopics: "+ res);
        Gson gson = new Gson();
        tags = gson.fromJson(res, tags.getClass());
        for (String tag : tags) {
            tagSelectData.put(tag, false);
            newButton(tag);
        }
    }

    @Override
    public void recUserInfo(String res) {
        Gson gson = new Gson();
        SaveUser.userInfoEntity = gson.fromJson(res, UserInfoEntity.class);
        Log.d("registerActivity", "获取用户信息完成，初始化用户喜好数据");
        //初始化用户信息
        InitTask initTask = new InitTask();
        initTask.setRecInit(this);
        initTask.execute(selectedTag);
        Log.d("RegisterActivity", "onDestroy: after initial!");

        //SaveUser.saveUsername(RegisterActivity.this, etUsername.getText().toString());
    }

    @Override
    public void recInit() {
        Log.d("registerActivity", "准备完成，生成用户相关性数据");
        generateUserSimilarityData();
        Log.d("registerActivity", "准备完成，进入论文浏览界面");
        Intent intent = new Intent(RegisterActivity.this, UserActivity.class);
        startActivity(intent);
        finish();
    }

    //向服务器请求生成用户喜好数据
    private void generateUserSimilarityData(){
        GenerateUserSimilarityDataTask generateUserSimilarityDataTask = new GenerateUserSimilarityDataTask();
        generateUserSimilarityDataTask.execute();
        try {
            generateUserSimilarityDataTask.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //点击变色，并在选中列表  添加/移除
    class onClickTag implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String tagName = ((Button) v).getText().toString();
            Boolean flag = tagSelectData.get(tagName);
            if (flag) {
                tagSelectData.put(tagName, false);
                selectedTag.remove(tagName);
                v.setActivated(false);
            } else {
                tagSelectData.put(tagName, true);
                selectedTag.add(tagName);
                v.setActivated(true);
            }
        }
    }


}
