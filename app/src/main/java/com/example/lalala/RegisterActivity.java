package com.example.lalala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lalala.entity.InitialUserTagData;
import com.example.lalala.entity.TagSimpleData;
import com.example.lalala.entity.UserInfor;
import com.example.lalala.http.HttpHandler;
import com.example.lalala.http.InitTask;
import com.example.lalala.http.MessageResponse;
import com.example.lalala.http.ReTagTask;
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
    private Set<Integer> tagId = new HashSet<>();              //选中的标签
    private Map<String, Integer> tagMap = new HashMap<>();          //标签名对应id
    private Map<String, Boolean> tagSelect = new HashMap<>();              //标签是否选中
    private Set<String> testTags = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SaveUser.initial = true;
        setContentView(R.layout.activity_register);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etPasswordC = findViewById(R.id.et_password_1);

        btn_register = findViewById(R.id.btn_register);
        btn_back = findViewById(R.id.btn_back);
        flexboxLayout = findViewById(R.id.fl_tags);

        //初始化tagMap和tagSelect，并创建标签
        ////////////////////////////
        if (SaveUser.Debug) {
            for (int i = 1; i <= 9; i++) {
                String tagName = new String("tag") + i;
                TagSimpleData testTag = new TagSimpleData(i, tagName);
                SaveUser.reTags.add(testTag);
            }
        }
        if (SaveUser.reTags.size() == 0) {
            ReTagTask reTagTask = new ReTagTask();
            reTagTask.setResponseRecTags(this);
            reTagTask.execute();
        } else {
            receiveRecTags();
        }

//        int i = 1;
//        for (String tagName : testTags) {
//            tagMap.put(tagName, i);
//            tagSelect.put(tagName, false);
//            newButton(tagName);
//            i++;
//        }

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String passwordC = etPasswordC.getText().toString();

                if (username.equals("") || password.equals(("")) || passwordC.equals("")) {
                    Toast.makeText(RegisterActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();
                } else if (tagId.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "请至少选择一个标签！", Toast.LENGTH_SHORT).show();
                } else if (password.equals(passwordC)) {
                    if (SaveUser.Debug) {
                        String toast = new String("注册成功！你选择的标签是：");
                        for (int id : tagId) {
                            toast = toast + "tag" + id + "  ";
                        }
                        Toast.makeText(RegisterActivity.this, toast, Toast.LENGTH_LONG).show();
                    } else {
                        RegisterTask registerTask = new RegisterTask();
                        registerTask.setMessageResponse(RegisterActivity.this);
                        registerTask.execute(username, password);
                        try {
                            registerTask.get();
                        } catch (ExecutionException | InterruptedException e) {
                            e.printStackTrace();
                        }
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
            Log.d("RegisterActivity", "register success!");
            SaveUser.username = username;

            //获取用户信息
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
    public void receiveRecTags() {
        for (TagSimpleData tag : SaveUser.reTags) {
            testTags.add(tag.getName());
            tagMap.put(tag.getName(), tag.getId());
            tagSelect.put(tag.getName(), false);
            newButton(tag.getName());
        }
    }

    @Override
    public void recUserInfo(String res) {
        Gson gson = new Gson();
        SaveUser.userInfor = gson.fromJson(res, UserInfor.class);

        //初始化用户tag
        Map<Integer, Float> tagRela = new HashMap<>();
        for (Integer id : tagId) {
            tagRela.put(id, 2f);
        }
        System.out.println("初始化tagId: ");
        for (Integer id : tagId) {
            System.out.println(id);
        }
        //记录用户的初始化信息
        Log.d("RegisterActivity", "onDestroy: before initial!");
        SaveUser.initialUserTagData = new InitialUserTagData(SaveUser.userInfor.getUsr_id(), tagRela);

        //初始化用户信息
        InitTask initTask = new InitTask();
        initTask.setRecInit(this);
        initTask.execute();
        Log.d("RegisterActivity", "onDestroy: after initial!");

        //SaveUser.saveUsername(RegisterActivity.this, etUsername.getText().toString());
    }

    @Override
    public void recInit() {
        //初始化分组页数map
        for (Integer i : SaveUser.userInfor.getGroupID()) {
            SaveUser.groupPage.put(i, 0);
        }
        Intent intent = new Intent(RegisterActivity.this, UserActivity.class);
        startActivity(intent);
    }

    //点击变色，并在选中列表  添加/移除
    class onClickTag implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String tagName = ((Button) v).getText().toString();
            Boolean flag = tagSelect.get(tagName);
            if (flag) {
                tagSelect.put(tagName, false);
                tagId.remove(tagMap.get(tagName));
                v.setActivated(false);
            } else {
                tagSelect.put(tagName, true);
                tagId.add(tagMap.get(tagName));
                v.setActivated(true);
            }
        }
    }


}
