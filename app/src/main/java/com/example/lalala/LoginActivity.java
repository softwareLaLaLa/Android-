package com.example.lalala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import com.example.lalala.entity.UserInfoEntity;
import com.example.lalala.http.GenerateUserSimilarityDataTask;
import com.example.lalala.http.LoginTask;
import com.example.lalala.http.MessageResponse;
import com.example.lalala.http.RecUserInfo;
import com.example.lalala.http.UserInfoTask;
import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;

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
                        Log.d("发送登入请求", "LoginTag");
                        LoginTask loginTask = new LoginTask();
                        loginTask.setMessageResponse(LoginActivity.this);
                        loginTask.execute(username, password);
                    try {
                        loginTask.get();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onReceived(String resJson) {
        if (resJson != null && resJson.contains("authority")) {
            //SaveUser.saveUsername(LoginActivity.this, etUsername.getText().toString());
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

    //获取用户信息后，向服务器请求生成用户相似性数据
    @Override
    public void recUserInfo(String res) {
        Gson gson = new Gson();
        SaveUser.userInfoEntity = gson.fromJson(res, UserInfoEntity.class);
        generateUserSimilarityData();
        Intent intent = new Intent(LoginActivity.this, UserActivity.class);
        startActivity(intent);
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
}
