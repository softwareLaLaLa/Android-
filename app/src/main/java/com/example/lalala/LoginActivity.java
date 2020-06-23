package com.example.lalala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.lalala.entity.UserInfor;
import com.example.lalala.http.LoginTask;
import com.example.lalala.http.MessageResponse;
import com.example.lalala.http.RecUserInfo;
import com.example.lalala.http.UserInfoTask;
import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.lalala.http.HttpHandler;

public class LoginActivity extends AppCompatActivity implements MessageResponse, RecUserInfo {

    EditText etUsername;
    EditText etPassword;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (username.equals("") || password.equals((""))) {
                    Toast.makeText(LoginActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    if (SaveUser.Debug) {
                        if (username != "username") {
                            Toast.makeText(LoginActivity.this, "用户名不存在！", Toast.LENGTH_SHORT).show();
                        } else if (password != "password") {
                            Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        LoginTask loginTask = new LoginTask();
                        loginTask.setMessageResponse(LoginActivity.this);
                        loginTask.execute(username, password);
                    }
//                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
//                    startActivity(intent);
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onReceived(String resJson) {
        if (resJson != null && resJson.contains("authority")) {
            //SaveUser.saveUsername(LoginActivity.this, etUsername.getText().toString());
            SaveUser.username = username;
            UserInfoTask userInfoTask = new UserInfoTask();
            userInfoTask.setRecUserInfo(this);
            userInfoTask.execute(username);
            try {
                userInfoTask.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(LoginActivity.this, "用户名密码错误或不存在！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void recUserInfo(String res) {
        Gson gson = new Gson();
        SaveUser.userInfor = gson.fromJson(res, UserInfor.class);
        for (Integer i : SaveUser.userInfor.getGroupID()) {
            SaveUser.groupPage.put(i, 0);
        }
        for (Integer i : SaveUser.userInfor.getCandidateGroupID()) {
            SaveUser.groupPage.put(i, 0);
        }
        Intent intent = new Intent(LoginActivity.this, UserActivity.class);
        startActivity(intent);
    }
}
