package com.example.lalala.http;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class UserInfoTask extends AsyncTask<String, Void, String> {
    private MessageResponse messageResponse;
    private RecUserInfo recUserInfo;

    public void setRecUserInfo(RecUserInfo recUserInfo) {
        this.recUserInfo = recUserInfo;
    }

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    @Override
    protected String doInBackground(String... strings) {
        Gson gson = new Gson();
//        Map<String, String> userData = new HashMap<>();
//        userData.put("name", strings[0]);
//        String json = gson.toJson(userData);
        return HttpHandler.doGet(HttpHandler.accountUrl + "/userInfo?userName="+strings[0], "");
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("userinfo", "onPostExecute!");
        //messageResponse.onReceived(result);
        recUserInfo.recUserInfo(result);
    }
}
